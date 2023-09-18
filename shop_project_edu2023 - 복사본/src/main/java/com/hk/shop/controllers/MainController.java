package com.hk.shop.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.shop.commands.ItemSearchCommand;
import com.hk.shop.constant.PageConfig;
import com.hk.shop.dtos.ItemDto;
import com.hk.shop.service.ItemService;
import com.hk.shop.service.PagingService;

@Controller
public class MainController {

	@Autowired
	private  ItemService itemService;
	@Autowired
	private PagingService pagingService;
	
	@GetMapping(value = "/")
    public String main(
//    		Optional<Integer> pnum, 
    		ItemSearchCommand itemSearchCommand,
    		Model model){

		if(itemSearchCommand.getPnum()==0) {
			itemSearchCommand.setPnum(1);
		}
		
		//상품목록 ROW 개수 설정: 12개
		int rowRange=PageConfig.ROWRANGEMAIN.label();
		itemSearchCommand.setRowRange(rowRange);
		//pageCount와 현재 페이지들의 마지막 페이지가 같으면 다음 페이지는 없다
		//12345 < 678910 > 11 12 13 14 15
		//                             15와 총페이지수15가 같으면 다음 페이지 버튼은 비활성화
		int pageCount=itemService.pageCount(itemSearchCommand);
		Map<String, Integer>map=pagingService.pagingValue(pageCount , itemSearchCommand.getPnum(), 10);
		model.addAttribute("page", map);	
		model.addAttribute("pageCount", pageCount);
		
//		System.out.println(itemSearchCommand.getSearchQuery()==null);
//		if(itemSearchCommand.getSearchQuery()!=null) {
//			
//			System.out.println(itemSearchCommand.getSearchQuery().getClass().getName());
//		}
		List<ItemDto> list=itemService.getMainItem(itemSearchCommand);
		model.addAttribute("items", list);
		model.addAttribute("itemSearchCommand", itemSearchCommand);
        return "main";
    }
}
