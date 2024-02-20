package com.project.jafet.framework.fr.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jafet.framework.fr.model.Sequence;

public interface SequenceCRUD extends JpaRepository<Sequence, Integer>{
	
	Sequence findBySequenceName(String sequenceName);

}
