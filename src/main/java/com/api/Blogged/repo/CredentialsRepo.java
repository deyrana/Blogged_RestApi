package com.api.Blogged.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Blogged.entity.CredentialsEntity;

@Repository
public interface CredentialsRepo extends JpaRepository<CredentialsEntity, Integer> {

	@Query("Select ce FROM CredentialsEntity ce WHERE ce.username = :username")
	public CredentialsEntity findByUsername(@Param("username") String username);
}
