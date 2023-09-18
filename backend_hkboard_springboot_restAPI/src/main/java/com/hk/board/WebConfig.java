package com.hk.board;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		 		.allowedOrigins("http://localhost:8086")
		 		.allowedMethods("GET","POST","PUT","DELETE")
		 		.allowedHeaders("*")   // 요청 헤더를 허용 여부
		 		.allowCredentials(false)  // 쿠기 허용 여부
		 		.maxAge(3600); //preflight 요청 결과를 캐싱할 시간을 설정
	}
	
	
}
