package com.example.Aucsion_Product_Service.service;

import com.example.Aucsion_Product_Service.dto.*;
import com.example.Aucsion_Product_Service.dto.auc_nor.AucsProductResponseDto;
import com.example.Aucsion_Product_Service.dto.auc_nor.SaleProductResponseDto;
import com.example.Aucsion_Product_Service.dto.search.ProductSearchResponseDto;

import java.util.List;

public interface ProductService {

    List<AucsProductResponseDto> getAllAucsHandProducts();
    List<AucsProductResponseDto> getAllAucsNormProducts();
    List<SaleProductResponseDto> getAllSaleHandProducts();
    List<SaleProductResponseDto> getAllSaleNormProducts();

    void registerProduct(ProductRegisterRequestDto dto);

    ProductSearchResponseDto searchProductByName(String name);

    //상품 상세 정보 조회하기
    ProductDetailResponseDto getProductDetail(String product_code);



}
