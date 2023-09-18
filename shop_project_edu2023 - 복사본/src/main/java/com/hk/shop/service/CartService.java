package com.hk.shop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.shop.commands.CartItemCommand;
import com.hk.shop.dtos.CartDto;
import com.hk.shop.dtos.CartItemDto;
import com.hk.shop.dtos.ItemDto;
import com.hk.shop.dtos.MemberDto;
import com.hk.shop.repository.CartRepository;
import com.hk.shop.repository.ItemRepository;
import com.hk.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
public class CartService {

//	private ItemRepository itemRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private CartRepository cartRepository;
	
	public Long addCart(CartItemCommand cartItemCommad, String email) {
		
		//item_id 구하기 위해
//		ItemDto item = itemRepository.getItemDtl(cartItemCommad.getItemId());
		//member_id 구하기 위해
		MemberDto member = memberRepository.getMember(email);
		System.out.println("member:"+member);
		//cart정보 구하기
		CartDto cart = cartRepository.getCart(member.getMember_id());
		
		if(cart==null) {
			cart=new CartDto();
			cart.setMember_id(member.getMember_id());
			cart = cartRepository.addCart(cart);
		}
		
		CartItemDto cartItemDto= cartRepository.getCartItem(new CartItemDto(cart.getCart_id(), cartItemCommad.getItemId()));
		
		if(cartItemDto==null) {
	//		#{count}, #{cart_id}, #{item_id}, sysdate(), #{email}
			Map<String, Object>map=new HashMap<>();
			map.put("count", (long)cartItemCommad.getCount());
			map.put("cart_id", cart.getCart_id());
			map.put("item_id", cartItemCommad.getItemId());
			map.put("email", email);
			cartRepository.addCartItem(map);
			return cart.getCart_id();
		}else {
			cartItemDto.setCount(cartItemCommad.getCount());
			cartItemDto.setModified_by(email);
			cartRepository.addCount(cartItemDto);
			return cartItemDto.getCart_id();
		}
	
	}
}







