package com.example.Aucsion_Product_Service.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ProductSearchResponseDto {

    //검색시 검색 결과 들을 반환하는 Dto


    private String name;
    private String category;
    private Date created_at;
    private String summary;

    // 경매상품 정보
    private float start_price;
    private Date end;
    private String bids_code;

    // 비경매상품 정보
    private float price;


}
