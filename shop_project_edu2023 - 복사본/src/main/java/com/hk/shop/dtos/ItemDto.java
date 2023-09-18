package com.hk.shop.dtos;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.hk.shop.commands.ItemCommand;
import com.hk.shop.constant.ItemSellStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

	private Long item_id; //상품 코드
	
	private String item_nm; // 상품명
	
	private int price; // 가격
	
	private int stock_number; //재고수량
	
	private String item_detail;//상품 상세 설명
	
	private ItemSellStatus item_sell_status; //상품 판매 상태
	
	private LocalDateTime reg_time; // 등록시간
	
	private LocalDateTime update_time; //수정시간

	private String created_by; // 등록자
	
	private String modified_by; //수정자
	
	private List<ItemImgDto> itemImgDtoList;//1대n 조인시 저장될 itemImg리스트 
	
	private static ModelMapper modelMapper = new ModelMapper();
    
    //dto-->command 로 자동맵핑해서 값들을 넣어준다
    public ItemCommand mappingItem(){
        return modelMapper.map(this, ItemCommand.class);
    }
    
    public static ItemDto of(ItemCommand itemCommand){
        return modelMapper.map(itemCommand,ItemDto.class);
    }
}
