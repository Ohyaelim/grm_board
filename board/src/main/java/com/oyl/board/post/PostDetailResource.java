package com.oyl.board.post;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class PostDetailResource extends EntityModel<PostDetailDto> {
    public PostDetailResource(PostDetailDto post, Long post_id, Link... links) {
        super(post, links);
        add(new Link("/post/"+post_id).withSelfRel());
    }
}
