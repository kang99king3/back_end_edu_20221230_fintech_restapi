package com.hk;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.shop.constant.ItemSellStatus;
import com.hk.shop.dtos.ItemDto;
import com.hk.shop.repository.ItemRepository;

@SpringBootTest
class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	@DisplayName("상품이 등록되어야 한다.")
	void test() {
//		itemRepository.insertItem(
//		new ItemDto(null, 
//				    "테스트상품명", 
//				    25000, 
//				    100, 
//				    "테스트할 상품의 상세설명",
//				    ItemSellStatus.SELL,
//				    LocalDateTime.now(),
//				    LocalDateTime.now()));
	}

	@Test
	@DisplayName("상품명으로 상품목록 조회하기")
	void item_nm_test() {
		List<ItemDto>list=itemRepository.getItemList("테스트상품명");
		assertNotNull(list, "테스트 상품목록");
		for(ItemDto dto:list) {
			System.out.println(dto);
		}
	}
}
