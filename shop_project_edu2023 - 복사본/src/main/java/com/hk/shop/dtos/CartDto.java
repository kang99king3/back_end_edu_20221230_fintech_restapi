package com.hk.shop.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

	private Long cart_id;
	private Long member_id;
	
	private MemberDto memberDto;
	
	private LocalDateTime reg_time; // 등록시간
	private LocalDateTime update_time; //수정시간
	private String created_by; // 등록자
	private String modified_by; //수정자
}
