package com.oyl.board.post;

import com.oyl.board.common.BaseTimeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Post create(@RequestBody PostRequestDto request, @PathVariable Long boardId) {
        return postService.create(request, boardId);
    }

    // 글 번호로 글 업뎃
    @PutMapping("/{postId}")
    public ResponseEntity update(@RequestBody PostRequestDto request, @PathVariable Long postId) {
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
    public PostDetailResponseDto findById(@PathVariable Long postId){
        return postService.findById(postId);
    }

    // 보드 유형별로 보여주기 + 페이징
    @GetMapping("/list/{boardId}")
    public Page<Post> findPostList(@PathVariable Long boardId,
                                   @RequestParam(value="keyword", required = false) String keyword, @PageableDefault(size = 7, sort = "postId" , direction = Sort.Direction.DESC) Pageable pageable){

        return postService.findPostList(boardId,keyword,pageable);
    }

    // 보드 유저별로 + 페이징
    @GetMapping("/list/mypage/{memberId}")
    public Page<Post> findPostUserList(@PathVariable Long memberId,
                                   @RequestParam(value="keyword", required = false) String keyword, @PageableDefault( sort = "regDate" , direction = Sort.Direction.DESC) Pageable pageable){

        return postService.findPostUserList(memberId,keyword,pageable);
    }

}