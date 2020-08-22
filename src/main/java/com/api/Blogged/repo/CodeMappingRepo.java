package com.api.Blogged.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Blogged.entity.CodeMappingEntity;
import com.api.Blogged.entity.pk.CodeMappingEntityPk;

@Repository
public interface CodeMappingRepo extends JpaRepository<CodeMappingEntity, CodeMappingEntityPk> {

	@Query("SELECT cme.Value FROM CodeMappingEntity cme WHERE cme.Category = :cat")
	public List<String> getCodeForCat(@Param("cat") String cat);
}
