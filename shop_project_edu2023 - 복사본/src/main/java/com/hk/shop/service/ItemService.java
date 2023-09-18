package com.hk.shop.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hk.shop.commands.ItemCommand;
import com.hk.shop.commands.ItemSearchCommand;
import com.hk.shop.constant.PageConfig;
import com.hk.shop.dtos.ItemDto;
import com.hk.shop.dtos.ItemImgDto;
import com.hk.shop.repository.ItemRepository;

@Transactional
@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemImgService itemImgService;
	
	public boolean insertItem(ItemCommand itemCommand,List<MultipartFile> itemImgFileList) throws Exception {
		//상품등록
		ItemDto itemDto = itemCommand.mappingItem();
		System.out.println(itemDto);
		int count=itemRepository.insertItem(itemDto);
		//이미지등록
		for (int i = 0; i < itemImgFileList.size(); i++) {
			ItemImgDto itemImgDto=new ItemImgDto();
//			itemImgDto.setItemDto(itemDto);
			itemImgDto.setItem_id(itemDto.getItem_id());
			itemImgDto.setCreated_by(itemDto.getCreated_by());
			if(i==0) {
				itemImgDto.setRepimg_yn("Y"); //대표이미지여부
			}else {
				itemImgDto.setRepimg_yn("N");
			}
			itemImgService.insertItemImg(itemImgDto,itemImgFileList.get(i));
		}
		
		return count>0?true:false;
	}
	
	//상품 목록(Main용)
	public List<ItemDto> getMainItem(ItemSearchCommand itemSearchCommand){
		Map<String, ItemSearchCommand>map=new HashMap<>();
//		map.put("searchQuery", itemSearchCommand.getSearchQuery());
		
		map.put("itemSearchCommand", itemSearchCommand);
//		return itemRepository.getMainItemMain(map);
		return itemRepository.getMainItem(map);
	}
	//상품목록(관리자용)
	public List<ItemDto> getAdminItem(ItemSearchCommand itemSearchCommand){
		Map<String, ItemSearchCommand>map=new HashMap<>();
//		map.put("searchQuery", itemSearchCommand.getSearchQuery());
	
		map.put("itemSearchCommand", itemSearchCommand);
		System.out.println(itemSearchCommand);
		return itemRepository.getMainItem(map);
	}

	//페이지 개수
	public int pageCount(ItemSearchCommand itemSearchCommand) {
		Map<String, ItemSearchCommand>map=new HashMap<>();
		map.put("itemSearchCommand", itemSearchCommand);
		return itemRepository.pageCount(map);
	}
	
	public ItemCommand getItemDtl(Long itemId) {
		//item_Mapper.xml에 itemDto로 resultMap 작업을 했기 때문에 
		//itemDto객체로 결과값이 반환된다. 근데 itemForm.html에서는 itemCommand로 받아서 화면처리를 
		//하기 때문에 ItemDto --> ItemCommand 로 변환해야 한다.
		ItemDto itemDto= itemRepository.getItemDtl(itemId);
		System.out.println("itemImgDtoList1:"+itemDto.getItemImgDtoList().size());
		if(itemDto.getItemImgDtoList().size()<5) {
			int count=5-itemDto.getItemImgDtoList().size();
			for (int i = 0; i < count; i++) {
				itemDto.getItemImgDtoList().add(new ItemImgDto());				
			}
		}
		System.out.println("itemImgDtoList2:"+itemDto.getItemImgDtoList().size());
		return itemDto.mappingItem();
	}
	
	public int itemUpdate(ItemCommand itemCommand, List<MultipartFile> itemImgFileList) throws Exception {
		ItemDto itemDto=itemCommand.mappingItem();
		//상품정보수정
		itemRepository.updateItem(itemDto);
		
		System.out.println("수정이미지개수:"+itemCommand.getItemImgIds().size());
		
		//수정폼에서 전달된 이미지 아이디값을 구함
		List<Long> itemImgIds = itemCommand.getItemImgIds();
		for (int i = 0; i < itemImgIds.size(); i++) {
			
			if(itemImgIds.get(i)==null) { //이미지 아이디값이 없는경우는 추가대상임
				//추가할 이미지 코드
				ItemImgDto itemImgDto=new ItemImgDto();
//				itemImgDto.setItemDto(itemDto);
				itemImgDto.setItem_id(itemDto.getItem_id());
				itemImgDto.setCreated_by(itemDto.getCreated_by());
				itemImgDto.setRepimg_yn("N");
				itemImgService.insertItemImg(itemImgDto,itemImgFileList.get(i));
			}else if(itemImgIds.get(i)!=null) { //아이디값이 0이 아니면 수정할 이미지임
				itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
			}
			
			
		}
		
		return itemRepository.updateItem(itemDto);
	}
	
}





