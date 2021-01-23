package com.api.Blogged.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Blogged.entity.CommentsEntity;

@Repository
public interface CommentsRepo extends JpaRepository<CommentsEntity, Integer> {
	
	@Query("SELECT cme FROM CommentsEntity cme WHERE cme.blogId = :blogId ORDER BY cme.createdTs DESC")
	public List<CommentsEntity> getAllCommentsForBlog(@Param("blogId") int blogId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CommentsEntity ce WHERE ce.blogId = :blog_id")
	public void removeCommentsByBlogId(@Param("blog_id") int blogId);

}
