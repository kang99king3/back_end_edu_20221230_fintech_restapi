package com.hk.shop.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.shop.commands.ItemCommand;
import com.hk.shop.commands.ItemSearchCommand;
import com.hk.shop.dtos.ItemDto;

@Mapper
public interface ItemRepository {

	//상품등록
	public int insertItem(ItemDto dto);
	//상품목록
	public List<ItemDto> getItemList(String item_nm);
	//메인 상품목록
	public List<ItemDto> getMainItem(Map<String, ItemSearchCommand> map);
	public List<ItemDto> getAdminItem(Map<String, ItemSearchCommand> map);
	//조회된 상품개수
	public int pageCount(Map<String, ItemSearchCommand> map);
	//상품 상세정보
	public ItemDto getItemDtl(Long item_id);
	//상품 정보수정
	public int updateItem(ItemDto itemDto);
}
