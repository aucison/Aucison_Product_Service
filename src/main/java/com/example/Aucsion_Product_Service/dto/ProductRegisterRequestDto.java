package com.example.Aucsion_Product_Service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductRegisterRequestDto {

    //상품 등록시 사용하는 Dto

    private String name;
    private String category;
    private Date created_at;
    private String information;
    private String summary;
    private Long products_code;

    // 경매상품 정보
    private float start_price;
    private Date end;
    private String bids_code;

    // 비경매상품 정보
    private float price;
}
