package com.example.Aucsion_Product_Service.jpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_id")
    private Long products_id;       //PK


    @Column(name = "name", nullable = false)
    private String name;            //상품명

    @Column(name = "category", nullable = false)
    private String category;        //상품 분류

    @Column(name = "created_at", nullable = false)
    private Date created_at;        //등록 시간

    @Column(name = "information", nullable = true)
    private String information;     //상품 정보

    @Column(name = "summary", nullable = true)
    private String summary;         //상품 한줄 설명

    @Column(name = "products_code", nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY) -> 기본키 전용으로 이렇게 하지 말라고 함 -> 데이터베이스 트리거 방식 권장
    private Long products_code;     //상품 고유 식별 코드

}
