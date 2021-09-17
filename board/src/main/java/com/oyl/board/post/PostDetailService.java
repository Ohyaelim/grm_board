package com.oyl.board.post;

import com.oyl.board.board.Board;
import com.oyl.board.board.BoardRepository;
import com.oyl.board.exception.BoardNotFoundException;
import com.oyl.board.exception.MemberNotFoundException;
import com.oyl.board.exception.PostDeletedException;
import com.oyl.board.exception.PostNotFoundException;
import com.oyl.board.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostDetailService {
    private final PostRepository postRepository;

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public PostDetailDto getPost(Long post_id) {
        Post post = this.postRepository.findById(post_id).orElseThrow(PostNotFoundException::new);
        this.postRepository.findByPostIdAndIsDeleted(post_id, false).orElseThrow(() -> new PostDeletedException("삭제된 게시글입니다."));
        PostDetailDto postDetailDto = this.modelMapper.map(post, PostDetailDto.class);
        return postDetailDto;
    }

    @Transactional
    public Post storePost(PostDetailDto postDetailDto, String boardType) {
        Board board = this.boardRepository.findByBoardType(boardType).orElseThrow(BoardNotFoundException::new);
        Post post = new Post();
        postDetailDto.toEntity(post);
        post.setMember(this.memberRepository.findMemberByEmail(postDetailDto.getEmail()).orElseThrow(MemberNotFoundException::new));
        post.setBoard(board);
        post.setCommentsLength((long) 0);
        post.setIsDeleted(Boolean.FALSE);
        Post savePost = this.postRepository.save(post);
        return savePost;
    }

    @Transactional
    public PostDetailDto updatePost(Long post_id, PostDetailDto postDetailDto) {
        Post post = this.postRepository.findById(post_id).orElseThrow(PostNotFoundException::new);
        postDetailDto.toEntity(post);
        this.postRepository.save(post);

        PostDetailDto returnValue = this.modelMapper.map(post, PostDetailDto.class);

        return returnValue;
    }

    @Transactional
    public Post deletePost(Long post_id, String visitorId) {
        Post post = this.postRepository.findByPostIdAndMember_Email(post_id, visitorId).orElseThrow(PostNotFoundException::new);
        post.setIsDeleted(Boolean.TRUE);
        return this.postRepository.save(post);
    }
}
