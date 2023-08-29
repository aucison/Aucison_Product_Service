package com.example.Aucsion_Product_Service.service;

import com.example.Aucsion_Product_Service.dto.board.CommentListResponseDto;
import com.example.Aucsion_Product_Service.dto.board.CommentRequestDto;
import com.example.Aucsion_Product_Service.dto.board.PostListResponseDto;
import com.example.Aucsion_Product_Service.dto.board.PostRequestDto;
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

    @Override
    public void updatePost(Long postId, PostRequestDto postRequestDto) {
        PostsEntity post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));
        post.update(postRequestDto.getTitle(), postRequestDto.getContent());
        postsRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        PostsEntity post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));
        postsRepository.delete(post);
    }

    @Override
    public void updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        CommentsEntity comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
        comment.update(commentRequestDto.getContent());
        commentsRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        CommentsEntity comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
        commentsRepository.delete(comment);
    }


}