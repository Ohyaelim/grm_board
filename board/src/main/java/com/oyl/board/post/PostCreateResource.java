package com.oyl.board.post;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class PostCreateResource extends EntityModel<Post> {
    public PostCreateResource(Post post, Link... links) {
        super(post, links);
    }
}
