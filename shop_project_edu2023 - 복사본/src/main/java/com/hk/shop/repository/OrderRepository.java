package com.hk.shop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.shop.dtos.ItemDto;
import com.hk.shop.dtos.MemberDto;
import com.hk.shop.dtos.OrderDto;
import com.hk.shop.dtos.OrderItemDto;

@Mapper
public interface OrderRepository {

	//주문추가
	public int addOrder(OrderDto orderDto);
	//주문상품추가
	public int addOrderItem(OrderItemDto orderItemDto);
	//재고량 변경
	public int stockUpdate(ItemDto itemDto);
	//주문리스트
	public List<OrderDto> getOrderList(String email);
}
