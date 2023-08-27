package com.example.Aucsion_Product_Service.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class PostListReponsetDto {





    private Long posts_id;
    private String title;
    private String content;
    private Date created_at;
    //user-service에서 닉네임 가져오기
    private String members_code;

    private List<CommentListResponseDto> comments;
}
