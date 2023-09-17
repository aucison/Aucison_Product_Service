package com.example.Aucsion_Product_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "member-service")
public interface MemberServiceClient {

    @GetMapping("/member-service/info")
    String getEmail();  //이메일을 가져온다

}
