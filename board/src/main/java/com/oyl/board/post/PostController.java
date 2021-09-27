package com.oyl.board.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value="/post")
public class PostController {

    private final PostService postService;

    // 보드 유형 별로 글 작성
    @PostMapping("/{boardId}")
    public Post create(@RequestBody PostRequest request, @PathVariable Long boardId) {
        return postService.create(request, boardId);
    }

    // 글 번호로 글 업뎃
    @PutMapping("/{postId}")
    public ResponseEntity update(@RequestBody PostRequest request, @PathVariable Long postId) {
        if (postService.hasRole(postId)) {
            postService.update(request, postId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    // 글 번호로 글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity delete(@PathVariable Long postId){
        if (postService.hasRole(postId)) {
            postService.delete(postId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // 글 번호로 글 찾기
    @GetMapping("/{postId}")
    public PostDetailResponse findById(@PathVariable Long postId){
        return postService.findById(postId);
    }

    // 보드 유형별로 보여주기 + 페이징
    @GetMapping("/list/{boardId}")
    public Page<Post> findPostList(@PathVariable Long boardId,
                                   @RequestParam(value="keyword", required = false) String keyword, Pageable pageable){

        return postService.findPostList(boardId,keyword,pageable);
    }
}