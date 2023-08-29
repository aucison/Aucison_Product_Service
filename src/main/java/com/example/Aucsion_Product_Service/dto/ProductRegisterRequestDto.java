package com.example.Aucsion_Product_Service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class ProductRegisterRequestDto {


    //판매자 이메일 해줘야함


    //상품 등록시 사용하는 Dto
    private String name;
    private String kind;
    private String category;
    private LocalDateTime createdTime;
    private String information;
    private String summary;



    // 경매상품 정보
    private float start_price;
    private Date end;
    private String bids_code;

    // 비경매상품 정보
    private float price;
}
