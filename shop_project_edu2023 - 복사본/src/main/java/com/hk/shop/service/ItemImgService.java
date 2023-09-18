package com.hk.shop.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.hk.shop.dtos.ItemImgDto;
import com.hk.shop.repository.ItemImgRepository;

@Transactional
@Service
public class ItemImgService {
	
    @Value("${itemImgLocation}")
    private String itemImgLocation;
    @Autowired
    private FileService fileService;
    @Autowired
    private ItemImgRepository itemImgRepository;
    
	public void insertItemImg(ItemImgDto itemImgDto, MultipartFile itemImgFile) throws Exception, Exception {
		String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";
        
        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName,
                    itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        

	        //상품 이미지 정보 저장
	//        itemImgDto.updateItemImg(oriImgName, imgName, imgUrl);
	        itemImgDto.setOri_img_name(oriImgName);
	        itemImgDto.setImg_name(imgName);
	        itemImgDto.setImg_url(imgUrl);
	
	        try {
				System.out.println(itemImgDto);
				int count=itemImgRepository.insertItemImg(itemImgDto);
				System.out.println("이미지정보저장:"+count);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
		if(!itemImgFile.isEmpty()) {  //기존에 업로드한 상품이미지를 수정했는지를 확인하여 수정 실행
			//기존 상품이미지 정보가져오기
			ItemImgDto itemImgDto = itemImgRepository.getItemImg(itemImgId);
			//상품이미지 파일이 있을 경우 삭제한다.
			if(!StringUtils.isEmpty(itemImgDto.getImg_name())) {
				fileService.deleteFile(itemImgLocation+"/"+itemImgDto.getImg_name());
			}
			//변경할 내용: 원본파일명, 저장파일명, 파일위치
			String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            itemImgDto.setOri_img_name(oriImgName);
            itemImgDto.setImg_name(imgName);
            itemImgDto.setImg_url(imgUrl);
            itemImgRepository.updateItemImg(itemImgDto);
		}
		
	}
	
}
