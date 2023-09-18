package com.hk.fintech.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {
 
	private String username;       
	private String useremail;       
	private String userpassword;  
	
	private String useraccesstoken; //접근을 위한 토큰 
	private String userrefreshtoken;//갱신을 위한 토큰
	private int userseqno; // 사용자 일련번호
}
