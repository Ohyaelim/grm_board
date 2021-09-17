package com.oyl.board.post;

import com.oyl.board.comment.CommentsRepository;
import com.oyl.board.post.Post;
import com.oyl.board.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostListService {
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;


    public Page<Post> getPostsList(String board_type, String filter, Pageable pageable) {
        Page<Post> page;
        if (filter == null) {
            page = this.postRepository.findAllByBoard_BoardType(board_type, pageable);
        }
        else {
//            page = this.postRepository.findAllByBoard_BoardTypeAndEmail(board_type, filter, pageable);
            page = this.postRepository.findAllByEmailAndContent(filter, pageable);
            System.out.println("page ==> " + page);
        }
        return page;
    }

    public Page<Post> getMemberPostsList(String email, Pageable pageable) {
        Page<Post> page = this.postRepository.findAllByEmail(email, pageable);
        return page;
    }
}
