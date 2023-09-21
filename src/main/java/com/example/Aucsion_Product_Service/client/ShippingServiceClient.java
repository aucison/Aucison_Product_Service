package com.example.Aucsion_Product_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "shipping-service")
public interface ShippingServiceClient {

    @GetMapping("/shipping-service/{productsId}")
    List<Long> getNowPricesByProductId(@PathVariable Long productsId);
}
