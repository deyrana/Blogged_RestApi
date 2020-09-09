package com.api.Blogged.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Blogged.dto.BlogsCompleteDto;
import com.api.Blogged.dto.UserCompleteDto;
import com.api.Blogged.dto.UserDto;
import com.api.Blogged.entity.CredentialsEntity;
import com.api.Blogged.entity.UserEntity;
import com.api.Blogged.mapper.UserDtoToEntity;
import com.api.Blogged.repo.CredentialsRepo;
import com.api.Blogged.repo.UserRepo;
import com.api.Blogged.service.UserService;
import com.api.Blogged.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CredentialsRepo credentialsRepo;

	@Override
	public UserEntity saveUserData(UserDto userDto) {

		try {
			UserEntity userEntity = UserDtoToEntity.mapEntity(userDto);

			UserEntity savedEntity = userRepo.saveAndFlush(userEntity);
			// Save in Credentials DB
			CredentialsEntity credentialsEntity = new CredentialsEntity();
			credentialsEntity.setUser_id(savedEntity.getUserId());
			credentialsEntity.setUsername(userDto.getUsername());
			credentialsEntity.setPassword(HashUtil.getSaltedHash(userDto.getPassword()));

			credentialsRepo.saveAndFlush(credentialsEntity);

			return savedEntity;
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}

		return null;
	}

	@Override
	public UserEntity getUser(String username) {
		try {
			UserEntity userEntity = userRepo.getUser(username);
			return userEntity;
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return null;
	}

	@Override
	public UserCompleteDto getCompeteUserData(int userId, String username) {
		try {
			UserCompleteDto userCompleteDto = userRepo.getCompleteUserData(userId, username);
			return userCompleteDto;
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<BlogsCompleteDto> findAllBlogsOfUser(String username) {
		try {
			List<BlogsCompleteDto> blogsCompleteDtos = userRepo.findAllBlogsOfUser(username);
			return blogsCompleteDtos;
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public long getBlogsCountForUser(String username) {
		try {
			return userRepo.getBlogsCountForUser(username);
		} catch (Exception e) {
			LOG.info("Error occurred while fetching blog count fot user {} - {}", username, e.getMessage());
		}
		return 0;
	}

	@Override
	public boolean editUser(UserCompleteDto userCompleteDto) {
		try {
			String username = credentialsRepo.findByUsername(userCompleteDto.getUsername()).getUsername();
			UserEntity userEntity = userRepo.getUser(username);
			userEntity.setImage(userCompleteDto.getImage());
			userEntity.setName(userCompleteDto.getName());
			userEntity.setEmail(userCompleteDto.getEmail());
			userEntity.setDateOfBirth(new Date(userCompleteDto.getDateOfBirth().getTime()) );
			userRepo.saveAndFlush(userEntity);
			return true;
		} catch (Exception e) {
			LOG.error("Error occurred while editing user details- {}", e.getMessage());
		}
		return false;
	}

}
