package com.example.Aucsion_Product_Service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<PostsEntity, Long> {
    List<ProductsEntity> findByCategoryAndKind(String category, String kind);
}
