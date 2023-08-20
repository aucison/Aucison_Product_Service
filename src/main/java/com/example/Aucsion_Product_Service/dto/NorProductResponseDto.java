package com.example.Aucsion_Product_Service.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class NorProductResponseDto {

    //비 경매상품(일반)들 검색 결과 반환시 사용하는 Dto

    private String name;
    private String category;
    private Date created_at;
    private String information;
    private String summary;
    private Long products_code;


    private float price;


}
