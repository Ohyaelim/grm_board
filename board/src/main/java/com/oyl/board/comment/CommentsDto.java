package com.oyl.board.comment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CommentsDto {
    private Long commentsId;
    private String comments;
    private String email;
    private Long postId;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private Long parentsId;

}
