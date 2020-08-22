package com.api.Blogged.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Blogged.repo.CodeMappingRepo;
import com.api.Blogged.service.CodeMappingService;

@Service
public class CodeMappingServiceImpl implements CodeMappingService {

	@Autowired
	private CodeMappingRepo codeMappingRepo;
	
	@Override
	public List<String> getCodeForCat(String category) {
		return codeMappingRepo.getCodeForCat(category);
	}

}
