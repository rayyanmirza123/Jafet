package com.project.jafet.framework.fr.crud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jafet.framework.fr.model.FaceEntity;

public interface FaceEntityCRUD extends JpaRepository<FaceEntity, Integer> {

	List<FaceEntity> findByLabel(Integer label);
	
}