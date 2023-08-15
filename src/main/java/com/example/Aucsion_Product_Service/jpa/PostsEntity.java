package com.example.Aucsion_Product_Service.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class PostsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_id")
    private Long posts_id;       //PK


    @Column(name = "title", nullable = false)
    private String title;               // 제목

    @Column(name = "content", nullable = false)
    private String content;             //내용

    @Column(name = "title", nullable = false)
    private Date created_at;          //등록시간

    @Column(name = "title", nullable = false)
    private String members_code;        //질문 작성자 식별 코드
}
