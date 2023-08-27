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
    private Date c_created_at;            //댓글 등록시간

    @Column(name = "title", nullable = false)
    private String members_code;        //답변 작성자 식별 코드



    //생각해 보니 코멘트를 여러개 달 수도 있다고 생각됨 -> 1대 1(or zero 에서) 말고 1대 다(or zero or 1)
    @ManyToOne
    @JoinColumn(name = "posts_id")
    private PostsEntity postsEntity;


}

