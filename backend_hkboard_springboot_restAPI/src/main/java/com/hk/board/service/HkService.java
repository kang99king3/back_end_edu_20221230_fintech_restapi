package com.hk.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.board.daos.IHkDao;
import com.hk.board.dtos.HkDto;

@Service
public class HkService implements IHkService{

	@Autowired
	private IHkDao hkDao;
	
	//페이징처리X
//	@Override
//	public List<HkDto> getAllList() {
//		return hkDao.getAllList();
//	}

	@Override
	public List<HkDto> getAllList(Map<String, String> map) {
		return hkDao.getAllList(map);
	}

	@Override
	public int getAllListCount() {
		return hkDao.getAllListCount();
	}
	@Override
	public HkDto getBoard(int seq) {
		return hkDao.getBoard(seq);
	}

	@Override
	public int insertBoard(HkDto dto) {
		return hkDao.insertBoard(dto);
	}

	@Override
	public int updateBoard(HkDto dto) {
		return hkDao.updateBoard(dto);
	}

	@Override
	public int deleteBoard(int seq) {
		return hkDao.deleteBoard(seq);
	}

	@Override
	public int muldel(Map<String, String[]> map) {
		return hkDao.muldel(map);
	}



}
