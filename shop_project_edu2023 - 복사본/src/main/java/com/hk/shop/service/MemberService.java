package com.hk.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hk.shop.dtos.MemberDto;
import com.hk.shop.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	//회원가입
	public boolean insertMember(MemberDto dto) {
		validateDuplicateMember(dto);
		int count=memberRepository.insertMember(dto);
		return count>0?true:false;
	}
	
	//중복확인하여 가입된 회원이면 예외발생
	private void validateDuplicateMember(MemberDto memberDto){
        MemberDto findMember = memberRepository.getMember(memberDto.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("로그인하기");
		System.out.println("email:"+email);
		MemberDto memberDto = memberRepository.getMember(email);
		System.out.println(memberDto);
		if(memberDto == null) {
			throw new UsernameNotFoundException(email);
		}
		return User.builder()
				   .username(memberDto.getEmail())
				   .password(memberDto.getPassword())
				   .roles(memberDto.getRole().toString())
				   .build();
				   
	}
}
