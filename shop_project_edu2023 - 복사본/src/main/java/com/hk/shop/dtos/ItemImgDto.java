package com.hk.shop.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class ItemImgDto {

	private Long item_img_id;
	private String img_name; //이미지파일명
	private String ori_img_name;//원본파일명
	private String img_url;//이미지 조회 경로
	private String repimg_yn;//대표 이미지 여부
	
	private Long item_id;
	private ItemDto itemDto;
	private String created_by;//등록자
	private String modified_by;//수정자
	private LocalDateTime reg_time; // 등록시간
	private LocalDateTime update_time; //수정시간
//	updateItemImg(oriImgName, imgName, imgUrl);
}
