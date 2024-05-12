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
@Table(name = "admin")
public class Admin {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer adminId;
	
	private String adminFaceEntityId;
	
	private String adminDepartment;
	
	private String adminUsername;
	
	private String adminPassword;	
	
}
