package com.example.Aucsion_Product_Service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException {
    ErrorCode errorCode;
}
