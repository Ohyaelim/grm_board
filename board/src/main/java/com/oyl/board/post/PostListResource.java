package com.oyl.board.post;

import com.oyl.board.post.Post;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.Optional;

public class PostListResource extends EntityModel<Post> {

    public PostListResource(Post post, Link... links) {
        super(post, links);
        add(new Link("/post/"+post.getPostId()).withSelfRel());
    }
}
