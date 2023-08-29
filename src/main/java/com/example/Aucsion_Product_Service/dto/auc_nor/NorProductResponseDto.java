package com.example.Aucsion_Product_Service.dto.auc_nor;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class NorProductResponseDto {

    //비 경매상품(일반)들 검색 결과 반환시 사용하는 Dto

    private String name;
    private LocalDateTime createdTime;  // Date 타입에서 LocalDateTime으로 변경
    private String information;
    private String summary;
    private boolean is_wish;



    private float price;


}
