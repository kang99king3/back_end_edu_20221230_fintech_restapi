package com.hk.fintech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//추가할 인터셉터는 로그인 확인하는 인터셉터
		registry.addInterceptor(new LoginInterceptor())
		        .addPathPatterns("/banking/*");
//		        .excludePathPatterns("/banking/test",   //제외할 패턴 설정  
//		        					 "path..",
//		        					 "path..");
		
	}
}
