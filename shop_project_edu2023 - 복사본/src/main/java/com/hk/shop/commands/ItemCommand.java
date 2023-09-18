package com.hk.shop.commands;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.hk.shop.constant.ItemSellStatus;
import com.hk.shop.dtos.ItemDto;
import com.hk.shop.dtos.ItemImgDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemCommand {
	
	private Long item_id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String item_nm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String item_detail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stock_number;

    private String created_by;
    
    private ItemSellStatus item_sell_status;

    private List<ItemImgDto> itemImgDtoList;
//
    private List<Long> itemImgIds;//상품수정할때 같이 수정할 이미지 ids
//
    private static ModelMapper modelMapper = new ModelMapper();
    
    //command-->dto 로 자동맵핑해서 값들을 넣어준다
    public ItemDto mappingItem(){
        return modelMapper.map(this, ItemDto.class);
    }
//
    public static ItemCommand of(ItemDto itemDto){
        return modelMapper.map(itemDto,ItemCommand.class);
    }
	
}
