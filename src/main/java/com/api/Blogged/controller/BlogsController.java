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

import com.api.Blogged.dto.BlogsDto;
import com.api.Blogged.service.BlogsService;

@RestController
@CrossOrigin
@RequestMapping("/blogs")
public class BlogsController {

	private static final Logger LOG = LoggerFactory.getLogger(BlogsController.class);

	@Autowired
	private BlogsService blogsService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<BlogsDto>> getAllBlogs() {
		try {
			return ResponseEntity.ok().body(blogsService.getAllBlogs());
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}

}
