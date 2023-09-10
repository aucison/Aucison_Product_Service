package com.example.Aucsion_Product_Service.dto.auc_nor;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class AucsProductResponseDto {
    //경매상품들 검색 결과 반환시 사용하는 Dto

    private String name;
    private LocalDateTime createdTime;  // Date 타입에서 LocalDateTime으로 변경
    private String information;
    private String summary;
    private boolean is_wish;


    private float start_price;
    private Date end;
    private String bids_code;
}
