package com.example.Aucsion_Product_Service.dto.auc_nor;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SaleProductResponseDto {

    //비 경매상품(일반)들 검색 결과 반환시 사용하는 Dto

    private String name;
    private LocalDateTime createdTime;  // Date 타입에서 LocalDateTime으로 변경
    private String information;
    private String summary;
    private String brand;
    private boolean is_wish;



    private float price;


}
