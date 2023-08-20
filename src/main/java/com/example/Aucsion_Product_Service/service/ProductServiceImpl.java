package com.example.Aucsion_Product_Service.service;


import com.example.Aucsion_Product_Service.dto.AucProductResponseDto;
import com.example.Aucsion_Product_Service.dto.NorProductResponseDto;
import com.example.Aucsion_Product_Service.dto.ProductRegisterRequestDto;
import com.example.Aucsion_Product_Service.dto.ProductSearchResponseDto;
import com.example.Aucsion_Product_Service.jpa.Auc_infosRepository;
import com.example.Aucsion_Product_Service.jpa.Nor_infosRepository;
import com.example.Aucsion_Product_Service.jpa.ProductsEntity;
import com.example.Aucsion_Product_Service.jpa.ProductsRepository;
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
        return null;
    }

    @Override
    public void registerProduct(ProductRegisterRequestDto dto) {
        //상품 등록
    }

    @Override
    public ProductSearchResponseDto searchProductByName(String name) {
        //상품 검색 결과 반환
        //현재 기술적 한개로 검색시 정확히 일치하는 단 한개만을 보여줄 수 있음
        return null;
    }
}
