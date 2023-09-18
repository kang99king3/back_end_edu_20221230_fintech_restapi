package com.hk.shop.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.hk.shop.constant.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	private Long order_id;
	private Long member_id;
	
	private LocalDateTime order_date;
	private OrderStatus order_status;
	private LocalDateTime regTime;
	private LocalDateTime updateTime;
	
	private MemberDto memberDto;
	private List<OrderItemDto> orderItemList;
	
	private List<OrderHistDto> orderHistDtoList;
}
