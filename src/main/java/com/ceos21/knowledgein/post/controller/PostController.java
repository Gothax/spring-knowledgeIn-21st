package com.ceos21.knowledgein.post.controller;

import com.ceos21.knowledgein.global.dto.CommonResponse;
import com.ceos21.knowledgein.post.dto.PostDto;
import com.ceos21.knowledgein.post.dto.request.RequestCreatePost;
import com.ceos21.knowledgein.post.dto.request.RequestUpdatePost;
import com.ceos21.knowledgein.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/post/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping
    public ResponseEntity<PostDto> createPost(@ModelAttribute @Valid RequestCreatePost requestCreatePost,
                                                // TODO: spring security 구현 후 수정
//                                              @AuthenticationPrincipal PrincipalUserDetails currentUser,
                                              @RequestHeader("userId") Long userId){

        PostDto result = postService.createPost(requestCreatePost, userId);
        return ResponseEntity.status(CREATED).body(result);
    }


    @GetMapping
    public ResponseEntity<List<PostDto>> findAllPost(){
        List<PostDto> result = postService.findAllPost();
        return ResponseEntity.status(OK).body(result);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> findOnePost(@PathVariable Long postId){
        PostDto result = postService.findOnePost(postId);
        return ResponseEntity.status(OK).body(result);
    }


    @PatchMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long postId,
                                              @ModelAttribute @Valid RequestUpdatePost requestUpdatePost){
        PostDto result = postService.updatePost(postId, requestUpdatePost);
        return ResponseEntity.status(OK).body(result);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponse<Void>> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        CommonResponse<Void> response = new CommonResponse<>(200, "성공적으로 삭제되었습니다.", null);
        return ResponseEntity.status(OK).body(response);
    }


}
