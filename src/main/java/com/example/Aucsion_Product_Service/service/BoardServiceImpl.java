package com.example.Aucsion_Product_Service.service;

import com.example.Aucsion_Product_Service.client.MemberServiceClient;
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

    ProductsRepository productsRepository;

    MemberServiceClient memberServiceClient;

    @Autowired
    public BoardServiceImpl(PostsRepository postsRepository, CommentsRepository commentsRepository, ProductsRepository productsRepository, MemberServiceClient memberServiceClient) {
        this.productsRepository=productsRepository;
        this.commentsRepository=commentsRepository;
        this.postsRepository=postsRepository;
        this.memberServiceClient = memberServiceClient;
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

    //게시물과 댓글에 대한 CRUD 서비스 코드
    //최대한 간결하고 직관성있고 통일성있게...
    @Override
    public PostCRUDResponseDto registPost(Long productId,PostRegistRequestDto dto){
        ProductsEntity productsEntity = productsRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + productId));

        // member-service로부터 이메일을 가져옴
        String emailFromMemberService = memberServiceClient.getEmail();

        PostsEntity post = PostsEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .email(emailFromMemberService)
                .productsEntity(productsEntity)
                .build();

        // 'createdTime'이 자동으로 설정될 것이므로 필요 x


        PostsEntity savedPost = postsRepository.save(post);

        // savedPost에는 이제 데이터베이스에서 자동 생성된 ID가 포함되어 있음
        PostCRUDResponseDto responseDto = PostCRUDResponseDto.builder()
                .posts_id(savedPost.getPosts_id())
                .build();

        return responseDto;
    }

    @Override
    public CommentCRUDResponseDto registComment(Long postId, CommentRegistRequestDto dto){

        PostsEntity postEntity = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));

        // member-service로부터 이메일을 가져옴
        String emailFromMemberService = memberServiceClient.getEmail();

        CommentsEntity comment = CommentsEntity.builder()
                .content(dto.getContent())
                .email(emailFromMemberService)
                .postsEntity(postEntity)
                .build();

        // 'createdTime'이 자동으로 설정될 것이므로 필요 x

        CommentsEntity savedComment = commentsRepository.save(comment);

        //이제 생성된 ID 있으므로
        CommentCRUDResponseDto responseDto = CommentCRUDResponseDto.builder()
                .comment_id(savedComment.getComments_id())
                .build();

        return responseDto;
    }

    @Override
    public PostCRUDResponseDto updatePost(Long postId, PostUpdateRequestDto postRequestDto) {
        PostsEntity post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));
        post.update(postRequestDto.getTitle(), postRequestDto.getContent());    //실제 수정 로직

        /*
        postsRepository.save(post);

        return PostCRUDResponseDto.builder().posts_id(postId).build();

         */

        PostsEntity updatedPost = postsRepository.save(post);

        PostCRUDResponseDto responseDto = PostCRUDResponseDto.builder()
                .posts_id(updatedPost.getPosts_id())
                .build();

        return responseDto;

        //위 식과 아래식은 큰 차이는 없지만 아래식이 좀 더 명시적이고 동기화 상태 보장함
        //내 생각에 간편한 식은 delete에 어울릴 듯
    }

    @Override
    public PostCRUDResponseDto deletePost(Long postId) {
        postsRepository.deleteById(postId);
        return PostCRUDResponseDto.builder().posts_id(postId).build();
    }

    @Override
    public CommentCRUDResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentRequestDto) {
        CommentsEntity comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
        comment.update(commentRequestDto.getContent()); //실제 수정 로직

        CommentsEntity updatedComment = commentsRepository.save(comment);

        CommentCRUDResponseDto responseDto = CommentCRUDResponseDto.builder()
                .comment_id(updatedComment.getComments_id())
                .build();

        return responseDto;
    }

    @Override
    public CommentCRUDResponseDto deleteComment(Long commentId) {
        commentsRepository.deleteById(commentId);
        return CommentCRUDResponseDto.builder().comment_id(commentId).build();
    }




}