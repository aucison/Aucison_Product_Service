package com.example.Aucsion_Product_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@EnableJpaAuditing	//auditing 어노테이션 활성화
@EnableFeignClients    //feign클라이언트 활성화
public class AucsionProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AucsionProductServiceApplication.class, args);
	}




}
