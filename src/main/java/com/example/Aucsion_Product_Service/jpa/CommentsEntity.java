package com.example.Aucsion_Product_Service.jpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long comments_id;       //PK


    @Column(name = "content", nullable = false)
    private String content;             //답변

    @Column(name = "title", nullable = false)
    private Date created_at;            //등록시간

    @Column(name = "title", nullable = false)
    private String members_code;        //답변 작성자 식별 코드
}

