package com.api.Blogged.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.Blogged.entity.ImagesEntity;
import com.api.Blogged.service.ImagesService;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImagesControlller {
	
	private static final Logger LOG = LoggerFactory.getLogger(ImagesControlller.class);
	@Autowired
	private ImagesService imagesService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<ImagesEntity>> getAllImages() {
		try {
			return ResponseEntity.ok().body(imagesService.fetchAllImages());
		} catch (Exception e) {
			LOG.error("Error occurred due to - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}

}
