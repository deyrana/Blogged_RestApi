package com.api.Blogged.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.Blogged.service.CodeMappingService;

@RestController
@CrossOrigin
@RequestMapping("/code")
public class CodeMappingController {
	private static final Logger LOG = LoggerFactory.getLogger(CodeMappingController.class);
	@Autowired
	private CodeMappingService codeMappingService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<String>> getCodeForCat(@RequestParam("category") String category) {
		try {
			return ResponseEntity.ok().body(codeMappingService.getCodeForCat(category));
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}

}
