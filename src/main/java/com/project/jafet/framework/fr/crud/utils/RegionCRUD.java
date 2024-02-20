package com.project.jafet.framework.fr.crud.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jafet.framework.fr.model.utils.Region;

public interface RegionCRUD	extends JpaRepository<Region, String> {

}