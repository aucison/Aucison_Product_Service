package com.example.Aucsion_Product_Service.dto.board;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class CommentListResponseDto {
    private Long comments_id;
    private String content;
    private LocalDateTime createdTime;

    //user-service에서 닉네임 가져오기
    private String members_code;
}
