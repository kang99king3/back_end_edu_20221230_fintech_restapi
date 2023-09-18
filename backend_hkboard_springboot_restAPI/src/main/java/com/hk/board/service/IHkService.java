package com.hk.board.service;

import java.util.List;
import java.util.Map;

import com.hk.board.dtos.HkDto;

public interface IHkService {

//	public List<HkDto> getAllList();//페이징처리X
	public List<HkDto> getAllList(Map<String, String> map);
	
	public int getAllListCount();
	
	public HkDto getBoard(int seq);
	public int insertBoard(HkDto dto);
	public int updateBoard(HkDto dto);
	public int deleteBoard(int seq);
	public int muldel(Map<String, String[]> map);
}
