package com.example.Aucsion_Product_Service.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentRequestDto {
    private String content;
}
