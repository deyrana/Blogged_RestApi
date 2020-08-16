package com.api.Blogged.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Blogged.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	@Query("SELECT ue FROM UserEntity ue JOIN CredentialsEntity ce ON ue.userId = ce.user_id WHERE ce.username = :username")
	public UserEntity getUser(@Param("username") String username);
}
