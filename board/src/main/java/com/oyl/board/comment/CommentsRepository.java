package com.oyl.board.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.stream.events.Comment;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findCommentsByParentAndPost_PostId(Long parentId, Long id);

    Long countAllByPost_PostId(Long postId);

    Optional<Comments> findByCommentsIdAndMember_Email(Long comments_id, String visitorId);

    @Query(value = "SELECT c FROM Comments as c WHERE c.parent is NULL and c.post.postId = ?1")
    List<Comments> findComments(Long postId);

    @Query(value = "SELECT * FROM t_comment c WHERE c.parents_id = ?1", nativeQuery = true)
    Collection<Comments> getCommentReplies(Long parentId);

    List<Comments> findCommentsByParent_CommentsId(Long parentId);
}
