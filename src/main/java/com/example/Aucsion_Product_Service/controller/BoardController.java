package com.example.Aucsion_Product_Service.controller;


import com.example.Aucsion_Product_Service.dto.board.PostListResponseDto;
import com.example.Aucsion_Product_Service.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
