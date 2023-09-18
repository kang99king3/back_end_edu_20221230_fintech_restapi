package com.hk.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.shop.commands.MemberCommand;
import com.hk.shop.dtos.MemberDto;
import com.hk.shop.service.MemberService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/new")
    public String memberForm(Model model){
		System.out.println("회원가입폼이동");
        model.addAttribute("memberCommand", new MemberCommand());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberCommand memberCommand, BindingResult bindingResult, Model model){
    	System.out.println("회원가입하기");
        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

        try {
            MemberDto memberDto = MemberDto.createMember(memberCommand, passwordEncoder);
            memberService.insertMember(memberDto);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
    	System.out.println("로그인 오류");
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }
}
