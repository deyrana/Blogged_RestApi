package com.api.Blogged.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.Blogged.entity.BlogsEntity;

@Repository
public interface BlogsRepo extends JpaRepository<BlogsEntity, Integer> {
	
	@Query("SELECT be FROM BlogsEntity be ORDER BY be.createdTs DESC")
	public List<BlogsEntity> findAllBlogs();
}
