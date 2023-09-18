package com.hk.shop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;

@Transactional
@Service
@Log
public class FileService {

	 public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
	        UUID uuid = UUID.randomUUID(); //렌덤 문자 생성하기
	        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));//파일확장자명 추출
	        String savedFileName = uuid.toString() + extension;// 랜던파일명+확장자명 생성
	        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
	        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
	        fos.write(fileData);
	        fos.close();
	        return savedFileName;
	    }

	    public void deleteFile(String filePath) throws Exception{
	        File deleteFile = new File(filePath);
	        if(deleteFile.exists()) {
	            deleteFile.delete();
	            log.info("파일을 삭제하였습니다.");
	        } else {
	            log.info("파일이 존재하지 않습니다.");
	        }
	    }
}
