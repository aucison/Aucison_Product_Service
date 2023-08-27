package com.example.Aucsion_Product_Service.service;

import com.example.Aucsion_Product_Service.dto.CommentListResponseDto;
import com.example.Aucsion_Product_Service.dto.PostListResponseDto;
import com.example.Aucsion_Product_Service.jpa.CommentsEntity;
import com.example.Aucsion_Product_Service.jpa.CommentsRepository;
import com.example.Aucsion_Product_Service.jpa.PostsEntity;
import com.example.Aucsion_Product_Service.jpa.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements  BoardService{

    PostsRepository postsRepository;
    CommentsRepository commentsRepository;

    @Autowired
    public BoardServiceImpl(PostsRepository postsRepository, CommentsRepository commentsRepository) {
        this.postsRepository= postsRepository;
        this.commentsRepository=commentsRepository;
    }

    @Override
    public List<PostListResponseDto> getAllBoard() {
        //게시글, 댓글 전부 보여주는 서비스

        //모든 게시글 다 가져오고
        List<PostsEntity> postsEntities = postsRepository.findAll();

        return postsEntities.stream()
                .map(postEntity -> {
                    PostListResponseDto postListResponseDto = PostListResponseDto.builder()
                            .posts_id(postEntity.getPosts_id())
                            .title(postEntity.getTitle())
                            .content(postEntity.getContent())
                            .created_at(postEntity.getCreated_at())
                            .members_code(postEntity.getMembers_code())
                            .build();

                    List<CommentsEntity> commentsEntities = commentsRepository.findByPostsId(postEntity.getPosts_id());

                    List<CommentListResponseDto> commentListResponseDtos = postEntity.getCommentsEntities()
                            .stream()
                            .map(commentEntity -> CommentListResponseDto.builder()
                                    .comments_id(commentEntity.getComments_id())
                                    .content(commentEntity.getContent())
                                    .c_created_at(commentEntity.getC_created_at())
                                    .members_code(commentEntity.getMembers_code())
                                    .build())
                            .collect(Collectors.toList());

                    postListResponseDto.setComments(commentListResponseDtos);

                    return postListResponseDto;     //여기에 글-댓글 모두 모이게 됨
                })
                .collect(Collectors.toList());
    }




}
