package com.example.Aucsion_Product_Service.service;

import com.example.Aucsion_Product_Service.dto.AucProductResponseDto;
import com.example.Aucsion_Product_Service.dto.NorProductResponseDto;
import com.example.Aucsion_Product_Service.dto.ProductRegisterRequestDto;
import com.example.Aucsion_Product_Service.dto.ProductSearchResponseDto;

import java.util.List;

public interface ProductService {
    List<AucProductResponseDto> getAllAucProducts();

    List<NorProductResponseDto> getAllNorProducts();

    void registerProduct(ProductRegisterRequestDto dto);

    ProductSearchResponseDto searchProductByName(String name);
}
