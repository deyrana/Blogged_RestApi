package com.api.Blogged.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.Blogged.entity.ImagesEntity;

@Repository
public interface ImagesRepo extends JpaRepository<ImagesEntity, Integer> {

}
