package com.api.Blogged.mapper;

import java.sql.Date;

import com.api.Blogged.dto.UserDto;
import com.api.Blogged.entity.UserEntity;

public class UserDtoToEntity {

	public static UserEntity mapEntity(UserDto userDto, byte[] image) {
		UserEntity entity = new UserEntity();
		entity.setName(userDto.getName());
		entity.setEmail(userDto.getEmail());
		entity.setGenres(userDto.getGenres());
		entity.setDateOfBirth(userDto.getDob() != null ? Date.valueOf(userDto.getDob()) : null);
		entity.setImage(image);
		return entity;
	}

}
