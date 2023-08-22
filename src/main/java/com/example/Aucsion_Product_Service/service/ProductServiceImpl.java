package com.example.Aucsion_Product_Service.service;


import com.example.Aucsion_Product_Service.dto.*;
import com.example.Aucsion_Product_Service.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    //분류별 상품 전체를 가져오기 -> getProductBy

    //상품 등록하기 -> createProduct

    //상품 검색 -> searchProduct

    ProductsRepository productsRepository;
    Nor_infosRepository nor_infosRepository;
    Auc_infosRepository auc_infosRepository;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository, Nor_infosRepository nor_infosRepository, Auc_infosRepository auc_infosRepository){
        this.productsRepository=productsRepository;
        this.auc_infosRepository=auc_infosRepository;
        this.nor_infosRepository=nor_infosRepository;
    }


    @Override
    public List<AucProductResponseDto> getAllAucProducts() {
        //모든 경매상품들 반환
        List<ProductsEntity> products = productsRepository.findByCategory("AUC");

        //가져온 리스트 [products]를 스트림형으로 변환 후 각각의 상품 정보를 AucProductResponseDto로 맵핑 -> 이를 반환
        return products.stream().map(product ->
                AucProductResponseDto.builder()
                        .name(product.getName())
                        .category(product.getCategory())
                        .created_at(product.getCreated_at())
                        .information(product.getInformation())
                        .summary(product.getSummary())
                        .products_code(product.getProducts_code())
                        .start_price(product.getAuc_infosEntity().getStart_price())
                        .end(product.getAuc_infosEntity().getEnd())
                        .bids_code(product.getAuc_infosEntity().getBids_code())
                        .build()
        ).collect(Collectors.toList());

    }

    @Override
    public List<NorProductResponseDto> getAllNorProducts() {
        //모든 비경매상품(일반)들 반환

        List<ProductsEntity> products = productsRepository.findByCategory("NOR");

        return products.stream().map(product ->
                NorProductResponseDto.builder()
                        .name(product.getName())
                        .category(product.getCategory())
                        .created_at(product.getCreated_at())
                        .information(product.getInformation())
                        .summary(product.getSummary())
                        .products_code(product.getProducts_code())
                        .price(product.getNor_infosEntity().getPrice())
                        .build()
        ).collect(Collectors.toList());

    }

    @Override
    public void registerProduct(ProductRegisterRequestDto dto) {
        //상품 등록


        //ProductsEntity를 먼저 저장을 한다.
        ProductsEntity product = ProductsEntity.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .created_at(dto.getCreated_at())
                .information(dto.getInformation())
                .summary(dto.getSummary())
                .products_code(dto.getProducts_code())
                .build();

        productsRepository.save(product);

        //이후 경매인지 비경매인지 체크를 한 후 추가적으로 저장한다.
        if ("auc".equals(dto.getCategory())) {
            Auc_infosEntity aucInfo = Auc_infosEntity.builder()
                    .start_price(dto.getStart_price())
                    .end(dto.getEnd())
                    .bids_code(dto.getBids_code())
                    .productsEntity(product)
                    .build();

            auc_infosRepository.save(aucInfo);

        } else if ("nor".equals(dto.getCategory())) {
            Nor_infosEntity norInfo = Nor_infosEntity.builder()
                    .price(dto.getPrice())
                    .productsEntity(product)
                    .build();

            nor_infosRepository.save(norInfo);
        }

        //흐음 1대 1관계를 유지하려면 이렇게 해야한다네...
    }

    @Override
    public ProductSearchResponseDto searchProductByName(String name) {
        //상품 검색 결과 반환
        //현재 기술적 한개로 검색시 정확히 일치하는 단 한개만을 보여줄 수 있음


        ProductsEntity product = productsRepository.findByName(name);

        if (product != null) {
            return ProductSearchResponseDto.builder()
                    .name(product.getName())
                    .category(product.getCategory())
                    .created_at(product.getCreated_at())
                    .summary(product.getSummary())
                    .build();
        } else {
            return null;  // 없을 경우 로직 생각
        }

    }

    @Override
    public ProductDetailResponseDto getProductDetail(String product_code) {

        //단일 상품에 대한 상품의 상세 정보를 제공

        //추후 posts, comments도 여기서 제공하게 할 수도 있음

        ProductsEntity product = productsRepository.findByProductsCode(product_code);

        ProductDetailResponseDto.ProductDetailResponseDtoBuilder builder = ProductDetailResponseDto.builder()
                .name(product.getName())
                .category(product.getCategory())
                .created_at(product.getCreated_at())
                .information(product.getInformation())
                .summary(product.getSummary())
                .producst_code(String.valueOf(product.getProducts_code()));

        // 경매 상품 추가정보
        if ("auc".equals(product.getCategory()) && product.getAuc_infosEntity() != null) {
            builder.start_price(product.getAuc_infosEntity().getStart_price())
                    .end(product.getAuc_infosEntity().getEnd())
                    .bids_code(product.getAuc_infosEntity().getBids_code());
        }

        // 비경매 상품 추가정보
        if ("nor".equals(product.getCategory()) && product.getNor_infosEntity() != null) {
            builder.price(product.getNor_infosEntity().getPrice());
        }

        return builder.build();

    }
}
