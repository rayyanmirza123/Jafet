package com.project.jafet.framework.fr.model.utils;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "geo")
public class Geo {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String geoId;
	
	private String geoName;
	
	@OneToMany(mappedBy="geo")
	private List<SubGeo> subGeos = new ArrayList<>();
	
	

}