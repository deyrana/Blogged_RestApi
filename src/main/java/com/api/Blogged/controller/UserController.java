package com.api.Blogged.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.Blogged.dto.CredentialDto;
import com.api.Blogged.dto.UserDto;
import com.api.Blogged.entity.UserEntity;
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

	@PostMapping(consumes = "multipart/form-data")
	@ResponseBody
	public ResponseEntity<UserEntity> saveUser(@RequestPart(value = "imageFile", required = false) MultipartFile file,
			@RequestPart(value = "info") UserDto userDto) {
		try {
			UserEntity entity = userService.saveUserData(file, userDto);
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
			CredentialDto credentialDto = null;
			credentialDto = mapper.readValue(formData, new TypeReference<CredentialDto>() {
			});
			return ResponseEntity.ok().body(credentialsService.validateUser(credentialDto));
		} catch (JsonMappingException e) {
			LOG.error("Error occurred due to - {}", e.getMessage());
		} catch (JsonProcessingException e) {
			LOG.error("Error occurred due to - {}", e.getMessage());
		}
		return null;
	}

	@GetMapping("/user")
	@ResponseBody
	public ResponseEntity<UserEntity> getUser(@RequestParam("username") String username) {
		try {
			UserEntity userEntity = userService.getUser(username);
			if (userEntity == null)
				throw new Exception("User not found");
			return ResponseEntity.ok().body(userEntity);
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.notFound().build();
	}

}
