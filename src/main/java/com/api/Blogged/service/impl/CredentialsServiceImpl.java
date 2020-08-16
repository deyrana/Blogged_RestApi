package com.api.Blogged.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Blogged.dto.CredentialDto;
import com.api.Blogged.entity.CredentialsEntity;
import com.api.Blogged.repo.CredentialsRepo;
import com.api.Blogged.service.CredentialsService;
import com.api.Blogged.util.HashUtil;

@Service
public class CredentialsServiceImpl implements CredentialsService {

	private static final Logger LOG = LoggerFactory.getLogger(CredentialsServiceImpl.class);

	@Autowired
	private CredentialsRepo credentialsRepo;

	public boolean validateUser(CredentialDto credentialDto) {
		try {
			CredentialsEntity credentialsEntity = credentialsRepo.findByUsername(credentialDto.getUsername());
			if (credentialsEntity == null)
				return false;
			return HashUtil.check(credentialDto.getPassword(), credentialsEntity.getPassword());
		} catch (Exception e) {
			LOG.error("Error occured due to - {}", e.getMessage());
		}
		return false;
	}

}
