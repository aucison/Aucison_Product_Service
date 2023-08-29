package com.example.Aucsion_Product_Service.dto.board;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostRequestDto {

    private String title;
    private String content;
}
