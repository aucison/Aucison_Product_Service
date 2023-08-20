package com.example.Aucsion_Product_Service.jpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "nor_infos")
public class Nor_infosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nor_infos_id")
    private Long nor_infos_id;       //PK


    @Column(name = "price", nullable = false)
    private float price;     //등록 가격
}
