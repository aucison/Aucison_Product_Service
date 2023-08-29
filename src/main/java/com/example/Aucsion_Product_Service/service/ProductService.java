package com.example.Aucsion_Product_Service.service;

import com.example.Aucsion_Product_Service.dto.*;
import com.example.Aucsion_Product_Service.dto.auc_nor.AucProductResponseDto;
import com.example.Aucsion_Product_Service.dto.auc_nor.NorProductResponseDto;
import com.example.Aucsion_Product_Service.dto.search.ProductSearchResponseDto;

import java.util.List;

public interface ProductService {
//    List<AucProductResponseDto> getAllAucProducts();
//
//    List<NorProductResponseDto> getAllNorProducts();


    List<AucProductResponseDto> getAllAucHandProducts();
    List<AucProductResponseDto> getAllAucNothandProducts();
    List<NorProductResponseDto> getAllNorHandProducts();
    List<NorProductResponseDto> getAllNorNothandProducts();

    void registerProduct(ProductRegisterRequestDto dto);

    ProductSearchResponseDto searchProductByName(String name);

    //상품 상세 정보 조회하기
    ProductDetailResponseDto getProductDetail(String product_code);



}
