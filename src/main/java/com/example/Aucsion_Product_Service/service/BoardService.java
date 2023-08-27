package com.example.Aucsion_Product_Service.service;

import com.example.Aucsion_Product_Service.dto.CommentRequestDto;
import com.example.Aucsion_Product_Service.dto.PostListResponseDto;
import com.example.Aucsion_Product_Service.dto.PostRequestDto;

import java.util.List;

public interface BoardService {
    List<PostListResponseDto> getAllBoard();
    void updatePost(Long postId, PostRequestDto postRequestDto);
    void deletePost(Long postId);
    void updateComment(Long commentId, CommentRequestDto commentRequestDto);
    void deleteComment(Long commentId);


}
