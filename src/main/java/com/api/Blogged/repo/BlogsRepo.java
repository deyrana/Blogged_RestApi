package com.api.Blogged.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Blogged.dto.BlogsCompleteDto;
import com.api.Blogged.entity.BlogsEntity;

@Repository
public interface BlogsRepo extends JpaRepository<BlogsEntity, Integer> {

//	@Query("SELECT be FROM BlogsEntity be ORDER BY be.createdTs DESC")
//	public List<BlogsEntity> findAllBlogs();

	@Query("SELECT NEW com.api.Blogged.dto.BlogsCompleteDto(be.blogId, be.header, be.content, be.createdBy, "
			+ "be.genres, be.createdTs, be.lastUpdated, ue.image) "
			+ "FROM BlogsEntity be JOIN CredentialsEntity ce ON be.createdBy = ce.username "
			+ "JOIN UserEntity ue ON ue.userId = ce.user_id "
			+ "WHERE be.blogId = :blogId")
	public BlogsCompleteDto findBlogDetail(@Param("blogId") int blogId);
	
	@Query("SELECT NEW com.api.Blogged.dto.BlogsCompleteDto(be.blogId, be.header, be.content, be.createdBy, "
			+ "be.genres, be.createdTs, be.lastUpdated, ue.image) "
			+ "FROM BlogsEntity be JOIN CredentialsEntity ce ON be.createdBy = ce.username "
			+ "JOIN UserEntity ue ON ue.userId = ce.user_id "
			+ "ORDER BY be.createdTs DESC")
	public List<BlogsCompleteDto> findAllBlogs();
}
