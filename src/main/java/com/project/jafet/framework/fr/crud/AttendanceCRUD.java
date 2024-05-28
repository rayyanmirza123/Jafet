package com.project.jafet.framework.fr.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jafet.framework.fr.model.Attendance;

public interface AttendanceCRUD extends JpaRepository<Attendance, Integer> {

}
