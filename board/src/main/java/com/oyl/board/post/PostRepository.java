package com.oyl.board.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByBoard_BoardTypeAndEmail(String board_type, String email, Pageable pageable);
    Page<Post> findAllByEmailContaining(String email, Pageable pageable);
    Page<Post> findAllByEmail(String email, Pageable pageable);
    Page<Post> findAllByContentContaining(String content, Pageable pageable);
    Page<Post> findAllByBoard_BoardType(String board_type, Pageable pageable);
    Optional<Post> findByPostIdAndMember_Email(Long id, String email);
    Post findPostByPostId(Long postId);
    @Query(value = "SELECT p FROM Post p WHERE p.email LIKE %?1% OR p.content like %?1% OR p.title LIKE %?1%")
    Page<Post> findAllByEmailAndContent(String email, Pageable pageable);
    Optional<Post> findByPostIdAndIsDeleted(Long post_id, Boolean deleted);
}
