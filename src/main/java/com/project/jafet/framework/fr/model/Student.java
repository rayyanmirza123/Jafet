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
@Table(name = "student")
public class Student {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Integer id;
	  
	  private Integer label;
	  
	  private String firstName;
	  
	  private String lastName;
	  
	  private String emailId;
	  
	  private String phoneNumber;
	  
	  private String faceEntityId;
	  
	  private String semester;
	  
	  private String course;
	  
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Integer rollNumber;
	  
}
