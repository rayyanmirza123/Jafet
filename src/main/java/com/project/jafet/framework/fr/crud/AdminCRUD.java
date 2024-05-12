package com.project.jafet.framework.fr.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jafet.framework.fr.model.Admin;


public interface AdminCRUD  extends JpaRepository<Admin, Integer>{

}
