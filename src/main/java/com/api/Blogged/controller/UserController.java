package com.api.Blogged.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.Blogged.dto.BlogsCompleteDto;
import com.api.Blogged.dto.CredentialDto;
import com.api.Blogged.dto.UserCompleteDto;
import com.api.Blogged.dto.UserDto;
import com.api.Blogged.entity.UserEntity;
import com.api.Blogged.exceptions.CustomNotFoundException;
import com.api.Blogged.service.CredentialsService;
import com.api.Blogged.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CredentialsService credentialsService;

	@PostMapping
	@ResponseBody
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserDto userDto) {
		try {
			UserEntity entity = null;
			if (credentialsService.checkUsernameExits(userDto.getUsername())) {
				return ResponseEntity.badRequest().body(entity);
			}
			entity = userService.saveUserData(userDto);
			LOG.info("User Entity - {}", entity);
			return ResponseEntity.ok().body(entity);
		} catch (Exception e) {
			LOG.error("Error occurred due to - {}", e.getMessage());
		}
		return null;
	}

	@PostMapping("/validate")
	@ResponseBody
	public ResponseEntity<Boolean> validateUser(@RequestBody String formData) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CredentialDto credentialDto = mapper.readValue(formData, new TypeReference<CredentialDto>() {
			});
			if (credentialsService.checkUsernameExits(credentialDto.getUsername())) {
				return ResponseEntity.ok().body(credentialsService.validateUser(credentialDto));
			} else {
				return ResponseEntity.badRequest().body(false);
			}
		} catch (JsonMappingException e) {
			LOG.error("Error occurred due to - {}", e.getMessage());
		} catch (JsonProcessingException e) {
			LOG.error("Error occurred due to - {}", e.getMessage());
		}
		return null;
	}

	@GetMapping("/user")
	@ResponseBody
//	@Cacheable(cacheNames = "users")
	public ResponseEntity<UserEntity> getUser(@RequestParam("username") String username) {
		try {
			UserEntity userEntity = userService.getUser(username);
			if (userEntity == null)
				throw new CustomNotFoundException("User not found");
			return ResponseEntity.ok().body(userEntity);
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/userDetail")
	@ResponseBody
//	@Cacheable(cacheNames="userComplete")
	public ResponseEntity<UserCompleteDto> getCompleteUserData(@RequestParam("userId") String userId,
			@RequestParam("username") String username) {
		try {
			UserCompleteDto userCompleteDto = userService.getCompeteUserData(Integer.parseInt(userId), username);
			if (userCompleteDto.getUserId() == null) {
				throw new CustomNotFoundException("User Does Not Exist");
			}
			return ResponseEntity.ok().body(userCompleteDto);
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/blogs")
	@ResponseBody
	public ResponseEntity<List<BlogsCompleteDto>> findAllBlogsOfUser(@RequestParam("username") String username) {
		try {
			return ResponseEntity.ok().body(userService.findAllBlogsOfUser(username));
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/blogsCount")
	@ResponseBody
	public ResponseEntity<Long> getBlogsCountForUser(@RequestParam("username") String username) {
		try {
			return ResponseEntity.ok().body(userService.getBlogsCountForUser(username));
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}

}
