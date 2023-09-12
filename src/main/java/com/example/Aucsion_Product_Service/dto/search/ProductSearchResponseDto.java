package com.example.Aucsion_Product_Service.dto.search;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class ProductSearchResponseDto {

    //검색시 검색 결과 들을 반환하는 Dto


    private String name;
    private LocalDateTime createdTime;
    private String summary;
    private String brand;

    // 경매상품 정보
    private float start_price;
    private Date end;   //end의 경우 사용자나 시스템 로직에 의해 명시적으로 설정해야 하므로 Date형
    private String bids_code;

    // 비경매상품 정보
    private float price;


}
