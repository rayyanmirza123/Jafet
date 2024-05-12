package com.project.jafet.framework.fr.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jafet.framework.fr.model.Teacher;

public interface TeacherCRUD  extends JpaRepository<Teacher, Integer>{

}
