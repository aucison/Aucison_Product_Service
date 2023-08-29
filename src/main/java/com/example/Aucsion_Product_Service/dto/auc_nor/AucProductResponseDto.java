package com.example.Aucsion_Product_Service.dto.auc_nor;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class AucProductResponseDto {
    //경매상품들 검색 결과 반환시 사용하는 Dto

    private String name;
    private Date created_at;
    private String information;
    private String summary;
    private boolean is_wish;


    private float start_price;
    private Date end;
    private String bids_code;
}