package com.oyl.board.post;

import com.oyl.board.post.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostDetailDto {
    private String title;
    private String content;
    private String email;
    private Long commentsLength;
    private Boolean isDeleted;

    public Post toEntity(Post post){
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setEmail(this.email);
        post.setCommentsLength(this.commentsLength);
        post.setIsDeleted(this.isDeleted);
        return post;
    }
}
