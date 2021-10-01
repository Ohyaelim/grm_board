package com.oyl.board.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailResponseDto {


    private String nickname;
    private String title;
    private String content;
    private long viewCount;
    private Long postId;
    private Boolean isDeleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate regDate;
    private String email;

    public PostDetailResponseDto(Post post){
        this.nickname = post.getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.viewCount = post.getViewCount();
        this.postId = post.getPostId();
        this.postId = post.getPostId();
        this.isDeleted = post.getIsDeleted();
        this.regDate = post.getRegDate();
        this.email = post.getEmail();
    }

}