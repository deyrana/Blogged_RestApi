package com.api.Blogged.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Blogged.entity.ImagesEntity;
import com.api.Blogged.repo.ImagesRepo;
import com.api.Blogged.service.ImagesService;

@Service
public class ImagesServiceImpl implements ImagesService{

	@Autowired
	private ImagesRepo imagesRepo;
	
	@Override
	public List<ImagesEntity> fetchAllImages() {
		return imagesRepo.findAll();
	}

}
