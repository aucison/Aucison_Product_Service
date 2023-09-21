package com.example.Aucsion_Product_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "member-service")
public interface MemberServiceClient {


    //사용처 -> registerProduct / registPost / registComment
    //기능 -> 사용자의 이메일 정보를 가져옴
    @GetMapping("/member-service/info/email")
    String getEmail();  //이메일을 가져온다


    //사용처 ->searchProductByName
    //기능 -> 사용자 이메일 정보를 이용하여 사용자의 모든 찜 목록의 product_id를 받아옴
    @GetMapping("/member-service/wishes/{email}")
    List<Long> getWishesProductIdsByEmail(@PathVariable String email);

}
