package com.api.Blogged.service;

import java.util.List;

import com.api.Blogged.dto.BlogsCompleteDto;
import com.api.Blogged.dto.UserCompleteDto;
import com.api.Blogged.dto.UserDto;
import com.api.Blogged.entity.UserEntity;

public interface UserService {

	
	public UserEntity saveUserData(UserDto userDto);
	
	public UserEntity getUser(String username);
	
	public UserCompleteDto getCompeteUserData(int userId, String username);
	
	public List<BlogsCompleteDto> findAllBlogsOfUser(String username);
	
	public long getBlogsCountForUser(String username);
	
	public boolean editUser(UserCompleteDto userCompleteDto);
}
