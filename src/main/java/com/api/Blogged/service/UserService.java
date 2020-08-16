package com.api.Blogged.service;

import org.springframework.web.multipart.MultipartFile;

import com.api.Blogged.dto.UserDto;
import com.api.Blogged.entity.UserEntity;

public interface UserService {

	
	public UserEntity saveUserData(MultipartFile file, UserDto userDto);
	
	public UserEntity getUser(String username);
}
