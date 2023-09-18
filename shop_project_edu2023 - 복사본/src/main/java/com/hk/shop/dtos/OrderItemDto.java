package com.hk.shop.dtos;

import java.time.LocalDateTime;

import com.hk.shop.constant.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

	private Long order_item_id;
	private Long item_id;
	private Long order_id;
	private int order_price; //주문가격
	private int count;//주문 수량
	private LocalDateTime regTime; //등록날짜
	private LocalDateTime updateTime;//수정날짜
	
	private OrderDto orderDto;
	private ItemDto itemDto;
	
	public OrderItemDto(int count, OrderDto orderDto, ItemDto itemDto) {
		super();
		this.count = count;
		this.order_id = orderDto.getOrder_id();
		this.item_id = itemDto.getItem_id();
		this.order_price = itemDto.getPrice();
		this.orderDto=orderDto;
	}
	
	
}
