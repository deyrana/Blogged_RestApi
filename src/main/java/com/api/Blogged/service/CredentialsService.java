package com.api.Blogged.service;

import com.api.Blogged.dto.CredentialDto;

public interface CredentialsService {

	public boolean validateUser(CredentialDto credentialDto);
}
