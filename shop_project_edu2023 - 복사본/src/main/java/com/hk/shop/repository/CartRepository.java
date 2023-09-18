package com.hk.shop.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.shop.dtos.CartDto;
import com.hk.shop.dtos.CartItemDto;
import com.hk.shop.dtos.MemberDto;

@Mapper
public interface CartRepository {

	//장바구니(cart 추가)
	public CartDto addCart(CartDto cartDto);
	//장바구니(cart 상품 추가)
	public int addCartItem(Map<String, Object> map);
	//cart정보
	public CartDto getCart(long member_id);
	//cart item 정보
	public CartItemDto getCartItem(CartItemDto cartItemDto);
	//cart 수량 수정
	public int addCount(CartItemDto cartItemDto);
}
