package com.project.jafet.framework.fr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.jafet.framework.fr.FaceRecognizer;
import com.project.jafet.framework.fr.crud.utils.GeoCRUD;
import com.project.jafet.framework.fr.crud.utils.RegionCRUD;
import com.project.jafet.framework.fr.crud.utils.SubGeoCRUD;
import com.project.jafet.framework.fr.model.utils.Geo;

@RestController()
@RequestMapping("/utils")
public class UtilController {
	
	public static final String MODULE = AddUserController.class.getName();
	
	@Autowired
	GeoCRUD geoCrud;
	
	@Autowired
	SubGeoCRUD subGeoCrud;
	
	@Autowired
	RegionCRUD regionCrud;
	
	@GetMapping(path="fetch_geos", produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> fetchGeos() {
		List<Geo> geos = geoCrud.findAll();
		return new ResponseEntity<Object>(geos, HttpStatus.OK);
	}
	
	@GetMapping(path="train_model", produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> trainModel(){
		FaceRecognizer.train();
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	

}