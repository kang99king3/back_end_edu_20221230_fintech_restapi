package com.hk.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
//		http.csrf().disable().cors().disable()
//			.authorizeHttpRequests(request -> request 
//						.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()  // 인증필요없음
//						.anyRequest().authenticated() // 인증이 필요
//						
//			)
//			.formLogin(login -> login 
//					  .loginPage("/members/login")
//					  .loginProcessingUrl("/login-process")
//					  .usernameParameter("email")
//					  .passwordParameter("pw")
//					  .defaultSuccessUrl("/")
//					  .failureUrl("/members/login/error")
//					  .permitAll()
//			)
//			.logout() 
//			.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
//			.logoutSuccessUrl("/");
		http.cors().disable().csrf().disable()
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()  // 인증필요없음
					.requestMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
					.requestMatchers("/css/**", "/js/**", "/img/**","/error").permitAll()
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated()
					)
		.formLogin(login -> login
		         .loginPage("/members/login")
		         .defaultSuccessUrl("/",true)  // true를 설정해야 이동함. -> 성공하면 무조건 설정한 URL로 감
		         .usernameParameter("email")
		         .failureUrl("/members/login/error")
		         .permitAll()
		 )
		 .logout()
		 .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))  //로그아웃 URL 설정하기
		 .logoutSuccessUrl("/");
		
//		 http.exceptionHandling()
//		         .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//		 ;
		 return http.build();
	}
	
	
	
}
