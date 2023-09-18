package com.hk.board.daos;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.board.dtos.HkDto;

@Mapper
public interface IHkDao {

//	public List<HkDto> getAllList();//페이징처리X
	public List<HkDto> getAllList(Map<String, String> map);
	public int getAllListCount();
	
	public HkDto getBoard(int seq);
	public int insertBoard(HkDto dto);
	public int updateBoard(HkDto dto);
	public int deleteBoard(int seq);
	public int muldel(Map<String, String[]> map);
}
