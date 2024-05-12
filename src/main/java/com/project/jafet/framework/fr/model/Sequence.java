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
@Table(name = "sequence")
public class Sequence {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Integer id;
	  
	  private Integer value;
	  
	  private String sequenceName;
	
}