package com.example.Aucsion_Product_Service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

@AllArgsConstructor
@Getter
public class ImageException extends IOException {
    ErrorCode errorCode;
}