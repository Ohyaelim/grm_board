package com.oyl.board.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/{post_id}")
    public ResponseEntity storeComments(@PathVariable Long post_id, @RequestBody @Valid CommentsDto commentsDto) {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        String visitorId = authentication.getName();
        Comments comment = this.commentsService.storeComments(visitorId, post_id, commentsDto);
        return ResponseEntity.ok().body(comment);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity getComments(@PathVariable Long post_id) {
        List<CommentsDto> commentsDto = this.commentsService.getComments(post_id);
        return ResponseEntity.ok().body(commentsDto);
    }

    @GetMapping("/reply/{comments_id}")
    public ResponseEntity getCommentReplies(@PathVariable Long comments_id) {
        List<CommentsDto> commentsDto = this.commentsService.findCommentReplies(comments_id);
        return ResponseEntity.ok().body(commentsDto);
    }

    @DeleteMapping("/{comments_id}")
    public ResponseEntity deletePost(@PathVariable Long comments_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        String visitorId = authentication.getName();
        try {
            Comments comments = this.commentsService.deleteComment(comments_id, visitorId);
            return ResponseEntity.ok(comments);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
