package com.project.jafet.framework.fr.model.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sub_geo")
public class SubGeo {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String subGeoId;
	
	private String subGeoName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="geoId")
	private Geo geo;
    
	@OneToMany(mappedBy="subGeo")
	private List<Region> regions = new ArrayList<>();

}
