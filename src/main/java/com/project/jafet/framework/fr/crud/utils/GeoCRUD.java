package com.project.jafet.framework.fr.crud.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jafet.framework.fr.model.utils.Geo;

public interface GeoCRUD extends JpaRepository<Geo, String>{

}