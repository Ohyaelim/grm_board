package com.oyl.board.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findPostsByBoardBoardId(Long boardId, @PageableDefault(size = 5) Pageable pageable);
    Page<Post> findPostsByMemberMemberId(Long memberId, @PageableDefault(size = 5) Pageable pageable);
    Page<Post> findByContentContaining(String keyword,@PageableDefault(size = 5) Pageable pageable);
    // Page<Post> findAllByEmail(String email, Pageable pageable);
//    Optional<Post> findByPostIdAndIsDeleted(Long post_id, Boolean deleted);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.viewCount = p.viewCount + 1 WHERE p.postId = :postId")
    int updateViewCount(@Param("postId") Long postId);
}
