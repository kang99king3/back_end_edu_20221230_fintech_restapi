package com.hk.shop.dtos;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.hk.shop.commands.MemberCommand;
import com.hk.shop.constant.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

	private Long member_id;
	private String name;
	private String email;
	private String password;
	private String address;
	private Role role;
	private List<OrderHistDto> orderHistDtoList;
	public static MemberDto createMember(MemberCommand memberCommand, PasswordEncoder passwordEncoder){
        MemberDto member = new MemberDto();
        member.setName(memberCommand.getName());
        member.setEmail(memberCommand.getEmail());
        member.setAddress(memberCommand.getAddress());
        String password = passwordEncoder.encode(memberCommand.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}
