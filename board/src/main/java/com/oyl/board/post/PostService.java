package com.oyl.board.post;

import com.oyl.board.board.Board;
import com.oyl.board.board.BoardRepository;
import com.oyl.board.member.Member;
import com.oyl.board.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Iterator;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Post create(PostRequest request, Long boardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        Board board = boardRepository.findByBoardId(boardId);
        return postRepository.save(Post.builder()
                .board(board)
                .content(request.getContent())
                .nickname(member.getName())
                .title(request.getTitle())
                .regDate(LocalDate.now())
                .member(member)
                .build());
    }

    @Transactional
    public Post update(PostRequest request, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        post.update(request.toPostEntity());
        postRepository.save(post);

        return post;
    }

    @Transactional
    public Post delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        postRepository.delete(post);

        return post;
    }

    public PostDetailResponse findById(Long postId) {
        postRepository.updateViewCount(postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostDetailResponse(post);
    }

    public Page<Post> findPostList(Long categoryId, String keyword, Pageable pageable) {
        if (keyword == null) {
            return postRepository.findPostsByBoard_BoardId(categoryId, pageable);
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
            if (role.equals("ROLE_ADMIN") || getMember(postId).getEmail().equals(member.getEmail()))
                return true;
        }
        return false;
    }
}