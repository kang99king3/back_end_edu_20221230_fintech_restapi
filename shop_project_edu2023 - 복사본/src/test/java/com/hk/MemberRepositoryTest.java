package com.hk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.shop.constant.Role;
import com.hk.shop.dtos.MemberDto;
import com.hk.shop.repository.MemberRepository;
import com.hk.shop.service.MemberService;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberService memberService;
	
	@Test
	@DisplayName("회원가입하기")
	void test() {
//		boolean isS=memberService.insertMember(
//				new MemberDto(null, "한경이", "itcampus2@hankyung.com", "1234", "양평동", Role.USER)
//				);
//		if(isS) {
//			System.out.println("회원가입성공");
//		}else {
//			fail("Not yet implemented");			
//		}
	}

}
