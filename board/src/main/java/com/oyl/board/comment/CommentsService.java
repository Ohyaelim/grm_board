package com.oyl.board.comment;

import com.oyl.board.exception.CommentsNotFoundException;
import com.oyl.board.exception.MemberNotFoundException;
import com.oyl.board.exception.PostNotFoundException;
import com.oyl.board.member.Member;
import com.oyl.board.member.MemberRepository;
import com.oyl.board.post.Post;
import com.oyl.board.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Comments storeComments(String email, Long postId, CommentsDto commentsDto) {
        Long parentId = commentsDto.getParentsId();
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        Comments comments = Comments.builder()
                .writer(member)
                .post(post)
                .email(member.getEmail())
                .content(commentsDto.getComments())
                .parent(null)
                .build();

        if (parentId != null) {
            Optional<Comments> parent = commentsRepository.findById(parentId);
            parent.get().setParent(comments);
        }

        Comments comment = commentsRepository.save(comments);
        return comment;
    }

    @Transactional
    public List<CommentsDto> getComments(Long postId) {
        List<Comments> comments = commentsRepository.findCommentsByParentAndPostPostId(null, postId);
        List<CommentsDto> commentsDtos = new ArrayList<>();
        for (Comments dto : comments) {
            commentsDtos.add(modelMapper.map(dto, CommentsDto.class));
        }
        return commentsDtos;
//        return commentsRepository.findCommentsByParentAndPostPostId(null, postId).stream()
//                .map(CommentsDto::new)
//                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentsDto> findCommentReplies(Long commentsId) {
        Collection<Comments> comments = commentsRepository.findCommentsByParentCommentsId(commentsId);
        List<CommentsDto> commentsDtos = new ArrayList<>();
        for (Object dto : comments) {
            commentsDtos.add(modelMapper.map(dto, CommentsDto.class));
        }
        return commentsDtos;
    }

    @Transactional
    public Comments deleteComment(Long commentsId) {
        Comments comments = commentsRepository.findByCommentsId(commentsId).orElseThrow(CommentsNotFoundException::new);
        comments.setIsDeleted();
        return commentsRepository.save(comments);
    }
}
