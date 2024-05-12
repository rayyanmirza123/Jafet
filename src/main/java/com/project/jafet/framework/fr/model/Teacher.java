package com.project.jafet.framework.fr.model;

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
@Table(name = "teacher")
public class Teacher {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  teacherId;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private String faceEntityId;
}
