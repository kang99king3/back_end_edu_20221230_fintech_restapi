package com.hk.fintech.service;

import com.hk.fintech.dtos.UserDto;

public interface IUserService {

	public boolean addUser(UserDto dto);
	public UserDto login(UserDto dto);
}
