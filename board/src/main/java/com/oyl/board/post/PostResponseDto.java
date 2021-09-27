package com.oyl.board.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oyl.board.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;
    private String nickname;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate regDate;
    private long viewCount;

    public PostResponseDto(Post post){
        this.postId = post.getPostId();
        this.nickname = post.getNickname();
        this.title = post.getTitle();
        this.regDate = post.getRegDate();
        this.viewCount = post.getViewCount();
    }

}

