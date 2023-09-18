package com.hk.shop.repository;

import org.apache.ibatis.annotations.Mapper;

import com.hk.shop.dtos.ItemImgDto;

@Mapper
public interface ItemImgRepository {
	           
	public int insertItemImg(ItemImgDto itemImgDto);
	
	public ItemImgDto getItemImg(Long itemImgId);
	
	public int updateItemImg(ItemImgDto itemImgDto);
}
