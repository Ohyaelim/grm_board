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

    @PostMapping("/{postId}")
    public ResponseEntity storeComments(@PathVariable Long postId, @RequestBody @Valid CommentsDto commentsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        String visitorId = authentication.getName();
        Comments comment = this.commentsService.storeComments(visitorId, postId, commentsDto);
        return ResponseEntity.ok().body(comment);
    }

    @GetMapping("/{postId}")
    public ResponseEntity getComments(@PathVariable Long postId) {
        List<CommentsDto> commentsDto = this.commentsService.getComments(postId);
        return ResponseEntity.ok().body(commentsDto);
    }

    @GetMapping("/reply/{commentsId}")
    public ResponseEntity getCommentReplies(@PathVariable Long commentsId) {
        List<CommentsDto> commentsDto = this.commentsService.findCommentReplies(commentsId);
        return ResponseEntity.ok().body(commentsDto);
    }

    @DeleteMapping("/{commentsId}")
    public ResponseEntity deletePost(@PathVariable Long commentsId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        String email = authentication.getName();
        try {
            Comments comments = this.commentsService.deleteComment(commentsId);
            return ResponseEntity.ok(comments);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
