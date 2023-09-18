package com.hk.shop.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.hk.shop.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderHistDto {

	private Long order_id;
	private LocalDateTime order_date;
	private OrderStatus order_status;
	private int order_price;
	private int count;
	private String item_nm;
	private String img_url;
	
	
}
