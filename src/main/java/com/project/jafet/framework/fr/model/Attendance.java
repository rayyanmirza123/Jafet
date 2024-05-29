package com.project.jafet.framework.fr.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer attendanceId;
	
	private String rollNumber;
	
	private String classRoom;
	
	private String teacherId;
	
	private String studentId;
	
	private String semester;
	
	private String subject;
	
	@Column(name="takenAt", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp  takenAt;

}
