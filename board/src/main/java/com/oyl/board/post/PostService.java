package com.oyl.board.post;

import com.oyl.board.board.Board;
import com.oyl.board.board.BoardRepository;
import com.oyl.board.exception.PostNotFoundException;
import com.oyl.board.member.Member;
import com.oyl.board.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Iterator;


@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Post create(PostRequestDto request, Long boardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        Board board = boardRepository.findByBoardId(boardId);
        return postRepository.save(Post.builder()
                .board(board)
                .content(request.getContent())
                .nickname(member.getNickname())
                .title(request.getTitle())
                .regDate(LocalDate.now())
                .member(member)
                .isDeleted(false)
                .email(member.getEmail())
//                .member(member)
//                .member(memberRepository.findById(member)
                .build());
    }

    @Transactional
    public Post update(PostRequestDto request, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        post.update(request.toPostEntity());
        return post;
    }

    @Transactional
    public Post delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        post.deletePost();
        return post;
    }

    @Transactional
    public PostDetailResponseDto findById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        postRepository.updateViewCount(postId);

        return new PostDetailResponseDto(post);
    }


    public Page<Post> findPostList(Long boardId, String keyword, @PageableDefault(size = 5) Pageable pageable) {
        if (keyword == null) {
            return postRepository.findPostsByBoardBoardId(boardId, pageable);
        } else
            return postRepository.findByContentContaining(keyword, pageable);
    }

    public Member getMember(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return post.getMember();
    }

    public boolean hasRole(Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        Iterator iter = authentication.getAuthorities().iterator();
        while (iter.hasNext()) {
            String role = ((GrantedAuthority) iter.next()).getAuthority();
            if (getMember(postId).getEmail().equals(member.getEmail()))
                return true;
        }
        return false;
    }

    public Page<Post> findPostUserList(Long memberId, String keyword, Pageable pageable) {
        if (keyword == null) {
            return postRepository.findPostsByMemberMemberId(memberId, pageable);
        } else
            return postRepository.findByContentContaining(keyword, pageable);
    }
}