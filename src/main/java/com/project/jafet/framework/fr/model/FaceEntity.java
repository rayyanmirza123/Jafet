package com.project.jafet.framework.fr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "face_entity")
public class FaceEntity {
	
	  @Id
			@SequenceGenerator(name = "face_entity_seq", sequenceName = "face_entity_seq", allocationSize = 1)
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Integer id;
	  
	  private Integer label;
	  
	  private String name;
	  
	  private String encodedImage;
	  
	  
}