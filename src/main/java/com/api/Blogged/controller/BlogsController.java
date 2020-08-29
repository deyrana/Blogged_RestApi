package com.api.Blogged.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.Blogged.dto.BlogsCompleteDto;
import com.api.Blogged.dto.BlogsDto;
import com.api.Blogged.entity.CommentsEntity;
import com.api.Blogged.entity.FavouriteBlogsEntity;
import com.api.Blogged.exceptions.CustomNotFoundException;
import com.api.Blogged.service.BlogsService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/blogs")
public class BlogsController {

	private static final Logger LOG = LoggerFactory.getLogger(BlogsController.class);

	@Autowired
	private BlogsService blogsService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<BlogsCompleteDto>> getAllBlogs() {
		try {
			return ResponseEntity.ok().body(blogsService.getAllBlogs());
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Boolean> saveBlog(@RequestBody String formData) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			BlogsDto blogsDto = mapper.readValue(formData, new TypeReference<BlogsDto>() {
			});
			blogsService.saveBlog(blogsDto);
			return ResponseEntity.ok().body(true);
		} catch (JsonMappingException e) {
			LOG.error("Error occurred - {}", e.getMessage());
		} catch (JsonProcessingException e) {
			LOG.error("Error occurred - {}", e.getMessage());
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().body(false);
	}

	@GetMapping(path = "/blog")
	@ResponseBody
	@Cacheable(cacheNames = "blogs")
	public ResponseEntity<BlogsCompleteDto> getBlog(@RequestParam("blogid") Integer blogid) {
		try {
			BlogsCompleteDto blogsCompleteDto = blogsService.getBlog(blogid);
			if (blogsCompleteDto != null) {
				return ResponseEntity.ok().body(blogsCompleteDto);
			} else {
				throw new CustomNotFoundException("Blog Not found");
			}
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping(path = "/{blogid}")
	@ResponseBody
	public ResponseEntity<Boolean> deleteBlog(@PathVariable("blogid") int blogid) {
		return ResponseEntity.ok().body(blogsService.deleteBlog(blogid) != null);
	}

	@PostMapping(path = "/favourite")
	@JsonIgnoreProperties(ignoreUnknown = true)
	public void setBlogToFav(@RequestBody String formData) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			FavouriteBlogsEntity favouriteBlogsEntity = mapper.readValue(formData,
					new TypeReference<FavouriteBlogsEntity>() {
					});

			LOG.info("FavouriteBlogsEntity - {}", favouriteBlogsEntity);
			blogsService.setBlogToFav(favouriteBlogsEntity.getUsername(), favouriteBlogsEntity.getBlog_id());
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
	}

	@GetMapping(path = "/getfav")
	@ResponseBody
	public ResponseEntity<Boolean> getFavBlog(@RequestParam("username") String username,
			@RequestParam("blogid") int blogid) {
		return ResponseEntity.ok().body(blogsService.getfavBlog(username, blogid));
	}

	@DeleteMapping(path = "/getfav")
	public void removeFavBlog(@RequestParam("username") String username, @RequestParam("blogid") int blogid) {
		blogsService.removeFavBlog(username, blogid);
	}
	
	@GetMapping(path = "/getFavCount")
	@ResponseBody
	public ResponseEntity<Long> getFavBlogCount(@RequestParam("username") String username){
		try {
			return ResponseEntity.ok().body(blogsService.getFavBlogCount(username));
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "/comments/{blogid}")
	@ResponseBody
	public ResponseEntity<List<CommentsEntity>> getAllCommentsForBlog(@PathVariable("blogid") int blogId){
		try {
			return ResponseEntity.ok().body(blogsService.getAllCommentsForBlog(blogId));
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping(path = "/comments")
	@JsonIgnoreProperties(ignoreUnknown = true)
	public void saveComment(@RequestBody String formData) {
		LOG.info("{}", formData);
		ObjectMapper mapper = new ObjectMapper();

		try {
			CommentsEntity commentsEntity = mapper.readValue(formData, new TypeReference<CommentsEntity>() {});
			blogsService.saveComment(commentsEntity);
		} catch (JsonProcessingException e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
	}

}
