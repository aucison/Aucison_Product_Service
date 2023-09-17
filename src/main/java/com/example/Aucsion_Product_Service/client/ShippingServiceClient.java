package com.example.Aucsion_Product_Service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Aucison_Shipping_Service")
public interface ShippingServiceClient {
}
