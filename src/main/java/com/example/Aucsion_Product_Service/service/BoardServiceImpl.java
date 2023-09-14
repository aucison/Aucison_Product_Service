package com.example.Aucsion_Product_Service.service;

import com.example.Aucsion_Product_Service.dto.board.*;
import com.example.Aucsion_Product_Service.jpa.*;
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
                            .createdTime(postEntity.getCreatedTime())
                            .email(postEntity.getEmail())
                            .build();

                    List<CommentsEntity> commentsEntities = commentsRepository.findByPostsId(postEntity.getPosts_id());

                    List<CommentListResponseDto> commentListResponseDtos = postEntity.getCommentsEntities()
                            .stream()
                            .map(commentEntity -> CommentListResponseDto.builder()
                                    .comments_id(commentEntity.getComments_id())
                                    .content(commentEntity.getContent())
                                    .createdTime(commentEntity.getCreatedTime())
                                    .email(commentEntity.getEmail())
                                    .build())
                            .collect(Collectors.toList());

                    postListResponseDto.setComments(commentListResponseDtos);

                    return postListResponseDto;     //여기에 글-댓글 모두 모이게 됨
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<PostListResponseDto> getBoardByProductId(Long productId) {
        List<PostsEntity> postsEntities = postsRepository.findByProductId(productId);

        return postsEntities.stream()
                .map(postEntity -> {
                    PostListResponseDto postListResponseDto = PostListResponseDto.builder()
                            .posts_id(postEntity.getPosts_id())
                            .title(postEntity.getTitle())
                            .content(postEntity.getContent())
                            .createdTime(postEntity.getCreatedTime())
                            .email(postEntity.getEmail())
                            .build();

                    List<CommentsEntity> commentsEntities = commentsRepository.findByPostsId(postEntity.getPosts_id());

                    List<CommentListResponseDto> commentListResponseDtos = commentsEntities.stream()
                            .map(commentEntity -> CommentListResponseDto.builder()
                                    .comments_id(commentEntity.getComments_id())
                                    .content(commentEntity.getContent())
                                    .createdTime(commentEntity.getCreatedTime())
                                    .email(commentEntity.getEmail())
                                    .build())
                            .collect(Collectors.toList());

                    postListResponseDto.setComments(commentListResponseDtos);

                    return postListResponseDto;
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

    @Override
    public PostRegistResponseDto registPost(PostRegistRequestDto dto){
        PostsEntity post = PostsEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .email(dto.getEmail())
                .build();

        // 'createdTime'이 자동으로 설정될 것이므로 필요 x


        PostsEntity savedPost = postsRepository.save(post);

        // savedPost에는 이제 데이터베이스에서 자동 생성된 ID가 포함되어 있음
        PostRegistResponseDto responseDto = PostRegistResponseDto.builder()
                .posts_id(savedPost.getPosts_id())
                .build();

        return responseDto;
    }

    @Override
    public CommentRegistResponseDto registComment(CommentRegistRequestDto dto){
        CommentsEntity comment = CommentsEntity.builder()
                .content(dto.getContent())
                .email(dto.getEmail())
                .build();

        // 'createdTime'이 자동으로 설정될 것이므로 필요 x

        CommentsEntity savedComment = commentsRepository.save(comment);

        //이제 생성된 ID 있으므로
        CommentRegistResponseDto responseDto = CommentRegistResponseDto.builder()
                .comment_id(savedComment.getComments_id())
                .build();

        return responseDto;
    }


}