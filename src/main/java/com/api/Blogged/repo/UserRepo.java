package com.api.Blogged.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Blogged.dto.BlogsCompleteDto;
import com.api.Blogged.dto.UserCompleteDto;
import com.api.Blogged.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	@Query("SELECT ue FROM UserEntity ue JOIN CredentialsEntity ce ON ue.userId = ce.user_id WHERE ce.username = :username")
	public UserEntity getUser(@Param("username") String username);
	
	@Query("SELECT new com.api.Blogged.dto.UserCompleteDto(ue.userId, ue.name, ce.username, "
			+ "ue.email, ue.dateOfBirth, ue.genres, ue.image) "
			+ "FROM UserEntity ue INNER JOIN CredentialsEntity ce ON ue.userId = ce.user_id "
			+ "WHERE ue.userId = :userId AND ce.username = :username")
	public UserCompleteDto getCompleteUserData(@Param("userId") int userId, @Param("username") String username);
	
	@Query("SELECT NEW com.api.Blogged.dto.BlogsCompleteDto(be.blogId, be.header, be.content, be.createdBy, "
			+ "be.genres, be.createdTs, be.lastUpdated) "
			+ "FROM BlogsEntity be JOIN CredentialsEntity ce ON be.createdBy = ce.username "
			+ "JOIN UserEntity ue ON ue.userId = ce.user_id "
			+ "WHERE be.createdBy = :username "
			+ "ORDER BY be.createdTs DESC")
	public List<BlogsCompleteDto> findAllBlogsOfUser(@Param("username") String username);
	
	@Query("SELECT COUNT(be) FROM BlogsEntity be WHERE be.createdBy = :username")
	public long getBlogsCountForUser(@Param("username") String username);
}
