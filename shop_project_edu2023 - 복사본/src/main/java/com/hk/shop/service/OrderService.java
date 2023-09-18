package com.hk.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.shop.commands.OrderCommand;
import com.hk.shop.constant.OrderStatus;
import com.hk.shop.dtos.ItemDto;
import com.hk.shop.dtos.MemberDto;
import com.hk.shop.dtos.OrderDto;
import com.hk.shop.dtos.OrderItemDto;
import com.hk.shop.exception.OutOfStockException;
import com.hk.shop.repository.ItemRepository;
import com.hk.shop.repository.MemberRepository;
import com.hk.shop.repository.OrderRepository;


@Service
@Transactional
public class OrderService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	public Long order(OrderCommand orderCommand, String email) {
		//member_id, order_status, email
		//order_id, member_id, email, count, order_price, item_id
		
		ItemDto itemDto = itemRepository.getItemDtl(orderCommand.getItemId());
		
		//주문할때 재고가 있는지 확인하기 : 재고에서 주문수량을 빼준다. 
		int stockNum = itemDto.getStock_number()-orderCommand.getCount();
		//빼준 값이 0보다 작으면 재고가 부족함
		if(stockNum<0) {
			throw new OutOfStockException("상품의 재고가 부족합니다(현재 재고 수량:"
											+itemDto.getStock_number()+")");
		}
		
		MemberDto memberDto = memberRepository.getMember(email);
		
		OrderDto orderDto=new OrderDto();
		orderDto.setMemberDto(memberDto);
		orderDto.setOrder_status(OrderStatus.ORDER);
		
		//주문추가
		orderRepository.addOrder(orderDto);
		//주문에 대한 상품 추가
		System.out.println("member_id:"+orderDto.getMemberDto().getMember_id());
		orderRepository.addOrderItem(new OrderItemDto(
													orderCommand.getCount()
													,orderDto,itemDto));
		itemDto.setStock_number(stockNum);//주문 후 재고수량
		orderRepository.stockUpdate(itemDto);//재고량 변경
		return orderDto.getOrder_id();
	}

	public List<OrderDto> getOrderList(String email) {
		return orderRepository.getOrderList(email);
	}
}
