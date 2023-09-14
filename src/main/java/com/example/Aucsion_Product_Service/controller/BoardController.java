package com.example.Aucsion_Product_Service.controller;


import com.example.Aucsion_Product_Service.dto.ApiResponse;
import com.example.Aucsion_Product_Service.dto.board.CommentRegistRequestDto;
import com.example.Aucsion_Product_Service.dto.board.PostListResponseDto;
import com.example.Aucsion_Product_Service.dto.board.PostRegistRequestDto;
import com.example.Aucsion_Product_Service.dto.board.PostRegistResponseDto;
import com.example.Aucsion_Product_Service.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product-service")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService=boardService;
    }

    @GetMapping("/detail/board/{products_id}")
    public ResponseEntity<Map<String, Object>> getBoardByProductId(@PathVariable Long products_id) {
        List<PostListResponseDto> posts = boardService.getBoardByProductId(products_id);

        Map<String, Object> response = new HashMap<>();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // 상품 개별 조회에서 게시물 작성
    // post도 마치 get처럼 짠다 이방식은
    @PostMapping("/detail/{products_id}/board")
    public ApiResponse<PostRegistResponseDto> createPost(@PathVariable("products_id") Long productId,
                                                         @RequestBody PostRegistRequestDto postRegistRequestDto) {

        return ApiResponse.createSuccess(boardService.registPost(postRegistRequestDto));
    }

    // 상품 개별 조회에서 게시물에 댓글 작성
    @PostMapping("/detail/{products_id}/board/{posts_id}")
    public ApiResponse<?> createComment(@PathVariable("products_id") Long productId,
                                        @PathVariable("posts_id") Long postId,
                                        @RequestBody CommentRegistRequestDto commentRegistRequestDto) {

        return ApiResponse.createSuccess(boardService.registComment(commentRegistRequestDto));
    }

}
