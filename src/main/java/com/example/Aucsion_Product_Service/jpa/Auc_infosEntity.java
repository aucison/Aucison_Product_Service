package com.example.Aucsion_Product_Service.jpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "auc_infos")
public class Auc_infosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auc_infos_id")
    private Long auc_infos_id;       //PK


    @Column(name = "start_price", nullable = false)
    private float start_price;     //경매 시작가


    @Column(name = "end", nullable = false)
    private Date end;             //경매 종료일


    @Column(name = "bids_code", nullable = false)
    private String bids_code;       //실시간 가격 식별 코드

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "auc_infosEntity")
    private ProductsEntity productsEntity;

}
