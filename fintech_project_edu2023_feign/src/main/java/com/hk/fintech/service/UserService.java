package com.hk.fintech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.fintech.dtos.UserDto;
import com.hk.fintech.mapper.UserMapper;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean addUser(UserDto dto) {
		int count=userMapper.addUser(dto);
		return count>0?true:false;
	}

	@Override
	public UserDto login(UserDto dto) {
		return userMapper.login(dto);
	}

}
