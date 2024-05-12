package com.project.jafet.framework.fr.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jafet.framework.fr.model.Student;

public interface StudentCRUD extends JpaRepository<Student, Integer> {

}
