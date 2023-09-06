package com.example.Aucsion_Product_Service.service;

import com.example.Aucsion_Product_Service.dto.board.CommentRequestDto;
import com.example.Aucsion_Product_Service.dto.board.PostListResponseDto;
import com.example.Aucsion_Product_Service.dto.board.PostRequestDto;

import java.util.List;

public interface BoardService {
    List<PostListResponseDto> getAllBoard();

    List<PostListResponseDto> getBoardByProductId(Long productId);
    void updatePost(Long postId, PostRequestDto postRequestDto);
    void deletePost(Long postId);
    void updateComment(Long commentId, CommentRequestDto commentRequestDto);
    void deleteComment(Long commentId);


}
