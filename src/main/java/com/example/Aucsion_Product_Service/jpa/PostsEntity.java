package com.example.Aucsion_Product_Service.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "postsEntity")
    List<CommentsEntity> commentsEntities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="products_id")
    private ProductsEntity productsEntity;


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
