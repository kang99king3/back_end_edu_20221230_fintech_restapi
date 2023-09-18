package com.hk.shop.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hk.shop.commands.ItemCommand;
import com.hk.shop.commands.ItemSearchCommand;
import com.hk.shop.constant.PageConfig;
import com.hk.shop.dtos.ItemDto;
import com.hk.shop.repository.ItemRepository;
import com.hk.shop.service.ItemService;
import com.hk.shop.service.PagingService;

import jakarta.validation.Valid;

@Transactional
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private PagingService pagingService;
	
	//상품등록폼이동
	@GetMapping("/admin/item/new")
	public String itemForm(Model model) {
		model.addAttribute("itemCommand", new ItemCommand());
		return "item/itemForm";
	}
	//상품등록
	@PostMapping(value = "/admin/item/new")
    public String itemNew(@Valid ItemCommand itemCommand, BindingResult bindingResult,
                          Model model, 
                          @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,
                          @AuthenticationPrincipal User user ){

        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }

        //id값이 없는 경우는 새로운 상품 추가를 의미하며, 이때 첫번째 상품이미지 입력은 필수 
        if(itemImgFileList.get(0).isEmpty() && itemCommand.getItem_id() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        try {
        	itemCommand.setCreated_by(user.getUsername());
        	itemService.insertItem(itemCommand,itemImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }
	
	//상품관리[상품목록조회]
	@GetMapping(value = {"/admin/items","/admin/items/{pnum}"})
    public String itemManage(
//    		@PathVariable("pnum") Optional<Integer> pnum,
    		ItemSearchCommand itemSearchCommand,
    		Model model){

		//pageCount와 현재 페이지들의 마지막 페이지가 같으면 다음 페이지는 없다
		//12345 < 678910 > 11 12 13 14 15
		//                             15와 총페이지수15가 같으면 다음 페이지 버튼은 비활성화
		if(itemSearchCommand.getPnum()==0) {
			itemSearchCommand.setPnum(1);
		}
		//상품목록 ROW 개수 설정: 12개
		int rowRange=PageConfig.ROWRANGEMNG.label();
		itemSearchCommand.setRowRange(rowRange);
		int pageCount=itemService.pageCount(itemSearchCommand);
		Map<String, Integer>map=pagingService.pagingValue(pageCount , itemSearchCommand.getPnum(), 10);
		model.addAttribute("page", map);	
		model.addAttribute("pageCount", pageCount);

		
		List<ItemDto> list=itemService.getAdminItem(itemSearchCommand);
		model.addAttribute("items", list);
		model.addAttribute("itemSearchCommand", itemSearchCommand);
		
		
        return "item/itemMng";
    }
	
	//상품정보
	@GetMapping(value = "/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){
		System.out.println(itemId);
        try {
            ItemCommand itemCommand = itemService.getItemDtl(itemId);
            System.out.println("ItemImgList:"+itemCommand.getItemImgDtoList().size());
            model.addAttribute("itemCommand", itemCommand);
        } catch(Exception e){
        	e.printStackTrace();
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("itemCommand", new ItemCommand());
            return "item/itemForm";
        }

        return "item/itemForm";
	}
	
	//상품수정
	@PostMapping(value = "/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemCommand itemCommand, BindingResult bindingResult,
    		@RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,Model model,
    		@AuthenticationPrincipal User user) throws Exception{
		
		if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
		if(itemImgFileList.get(0).isEmpty() && itemCommand.getItem_id() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }
		itemCommand.setCreated_by(user.getUsername());
		itemService.itemUpdate(itemCommand,itemImgFileList );
        return "item/itemForm";
    }
	
	//상품상세조회
	 @GetMapping(value = "/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemCommand itemCommand = itemService.getItemDtl(itemId);
        model.addAttribute("item", itemCommand);
        System.out.println(itemCommand);
        return "item/itemDtl";
    }

}
