package com.example.Aucsion_Product_Service.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductSearchRequestDto {
    //상품 검색시 사용하는 Dto


    private String name;
}
