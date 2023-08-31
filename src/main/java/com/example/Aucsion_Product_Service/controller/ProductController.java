package com.example.Aucsion_Product_Service.controller;


import com.example.Aucsion_Product_Service.dto.auc_nor.AucProductResponseDto;
import com.example.Aucsion_Product_Service.dto.auc_nor.NorProductResponseDto;
import com.example.Aucsion_Product_Service.dto.search.ProductSearchResponseDto;
import com.example.Aucsion_Product_Service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-service")
public class ProductController {


    //공부용 주석
    //@Autowired란?
    //@Autowired가 붙은 필드, 생성자, 또는 메소드에 대해서 스프링은 해당 타입의 빈(Bean)을 찾아 자동으로 주입

    //아래에서 보면 생성자를 통한 의존성 주입을 하고있음
    //왜 이렇게 할까?
    //유연성 - 의존성을 직접 생성하지 않고 외부에서 주입받기 때문에 코드 변경 없이 다른 구현을 사용할 수 있음
    //테스트 용이
    //결합도 감소 - 클래스간의 결합도가 낮아져서 유지보수가 쉬움
    //자동 설정과 설정의 중앙화 : 예를 들어 Environment 객체를 통해 애플리케이션의 환경 설정에 쉽게 접근


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }



    @GetMapping("/auc/nothand/list")
    public ResponseEntity<List<AucProductResponseDto>> getAllAucNothandProducts() {
        List<AucProductResponseDto> products = productService.getAllAucNothandProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/auc/hand/list")
    public ResponseEntity<List<AucProductResponseDto>> getAllAucHandProducts() {
        List<AucProductResponseDto> products = productService.getAllAucHandProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/nor/nothand/list")
    public ResponseEntity<List<NorProductResponseDto>> getAllNorNothandProducts() {
        List<NorProductResponseDto> products = productService.getAllNorNothandProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/nor/hand/list")
    public ResponseEntity<List<NorProductResponseDto>> getAllNorHandProducts() {
        List<NorProductResponseDto> products = productService.getAllNorHandProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    //
    @GetMapping("/search")
    public ResponseEntity<ProductSearchResponseDto> searchReturn(@RequestParam String name){

        ProductSearchResponseDto search = productService.searchProductByName(name);
        if (search != null) {
            return se;
        } else {
            // 상품이 없을 경우의 로직. 여기서는 간단하게 null을 반환하게 했습니다.
            // 실제 서비스에서는 적절한 에러 메시지나 상태 코드를 반환하도록 처리할 수 있습니다.
            return null;
        };
    }

}
