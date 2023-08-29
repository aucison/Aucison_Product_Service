package com.example.Aucsion_Product_Service.jpa;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "kind", nullable = false)
    private String kind;            // 상품분류(일반(hand) / 핸드메이드(nonhand)

    @Column(name = "category", nullable = false)
    private String category;        // 경매여부(경매(auc) / 비경매(nor))

    @Column(name = "created_at", nullable = false)
    private Date created_at;        //등록 시간

    @Column(name = "information", nullable = true)
    private String information;     //상품 정보

    @Column(name = "summary", nullable = true)
    private String summary;         //상품 한줄 설명


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auc_infos_id")
    private Auc_infosEntity auc_infosEntity;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nor_infos_id")
    private Nor_infosEntity nor_infosEntity;

    @OneToMany(mappedBy = "productsEntity")
    List<PostsEntity> postsEntities = new ArrayList<>();

    @Builder
    public ProductsEntity(String name, String kind, String category, Date created_at,
                          String information, String summary, Long products_code
                          /*Auc_infosEntity auc_infosEntity, Nor_infosEntity nor_infosEntity*/) {
        this.name = name;
        this.kind = kind;
        this.category = category;
        this.created_at = created_at;
        this.information = information;
        this.summary = summary;
        //this.auc_infosEntity = auc_infosEntity;
        //this.nor_infosEntity = nor_infosEntity;
    }

}
