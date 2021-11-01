package com.oyl.board.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

// 어노테이션 필요 없다. Jparepo extends 하면 알아서 가져와줘서 bean 생성가능
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findCommentsByParentAndPostPostId(Long parentId, Long id);

    Long countAllByPostPostId(Long postId);

    Optional<Comments> findByCommentsId(Long comments_id);

    @Query(value = "SELECT c FROM Comments as c WHERE c.parent is NULL and c.post.postId = ?1")
    List<Comments> findComments(Long postId);

    @Query(value = "SELECT * FROM t_comment c WHERE c.parents_id = ?1", nativeQuery = true)
    Collection<Comments> getCommentReplies(Long parentId);

    List<Comments> findCommentsByParentCommentsId(Long parentId);
}
