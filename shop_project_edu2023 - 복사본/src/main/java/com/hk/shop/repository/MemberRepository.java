package com.hk.shop.repository;

import org.apache.ibatis.annotations.Mapper;

import com.hk.shop.dtos.MemberDto;

@Mapper
public interface MemberRepository {

	//회원가입
	public int insertMember(MemberDto dto);
	//회원가입시 중복 이메일 체크
	public MemberDto getMember(String email);
}
