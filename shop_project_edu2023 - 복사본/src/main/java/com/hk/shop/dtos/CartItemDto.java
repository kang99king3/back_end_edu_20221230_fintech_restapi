package com.hk.shop.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

	private Long cart_item_id;
	private Long cart_id;
	private Long item_id;
	private int count;
	
	private ItemDto itemDto;
	private CartDto cardDto;
	
	private LocalDateTime reg_time; // 등록시간
	private LocalDateTime update_time; //수정시간
	private String created_by; // 등록자
	private String modified_by; //수정자
	
	public CartItemDto(Long cart_id, Long item_id) {
		super();
		this.cart_id = cart_id;
		this.item_id = item_id;
	}
	
	
}
