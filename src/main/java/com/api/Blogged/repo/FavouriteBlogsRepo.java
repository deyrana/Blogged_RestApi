package com.api.Blogged.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Blogged.dto.BlogsCompleteDto;
import com.api.Blogged.entity.FavouriteBlogsEntity;

@Repository
public interface FavouriteBlogsRepo extends JpaRepository<FavouriteBlogsEntity, Integer> {

	@Query("SELECT fbe FROM FavouriteBlogsEntity fbe WHERE fbe.username = :username AND fbe.blog_id = :blog_id")
	public FavouriteBlogsEntity findFavBlog(@Param("username") String username, @Param("blog_id") int blogId);

	@Transactional
	@Modifying
	@Query("DELETE FROM FavouriteBlogsEntity fbe WHERE fbe.username = :username AND fbe.blog_id = :blog_id")
	public void removeFavBlog(@Param("username") String username, @Param("blog_id") int blogId);
	
	@Query("SELECT COUNT(fbe) FROM FavouriteBlogsEntity fbe WHERE fbe.username = :username")
	public long getFavBlogCount(@Param("username") String username);
	
	@Query("SELECT NEW com.api.Blogged.dto.BlogsCompleteDto(be.blogId, be.header, be.content, be.createdBy, "
			+ "be.genres, be.createdTs, be.lastUpdated) "
			+ "FROM BlogsEntity be JOIN FavouriteBlogsEntity fbe ON be.blogId = fbe.blog_id "
			+ "WHERE fbe.username = :username "
			+ "ORDER BY be.lastUpdated DESC")
	public List<BlogsCompleteDto> getFavBlogs(@Param("username") String username);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM FavouriteBlogsEntity fbe WHERE fbe.blog_id = :blog_id")
	public void removeFavBlogByBlogId(@Param("blog_id") int blogId);
}
