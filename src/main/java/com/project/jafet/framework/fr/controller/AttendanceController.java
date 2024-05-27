package com.project.jafet.framework.fr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.jafet.framework.exception.FaceRecognizerException;
import com.project.jafet.framework.fr.FaceRecognizer;
import com.project.jafet.framework.fr.model.utils.FaceRecognizerResponse;
import com.project.jafet.framework.req.model.StudentModel;
import com.project.jafet.framework.res.model.CommonResponse;

@RestController
public class AttendanceController {

	
	@PostMapping("/fr")
	@CrossOrigin(origins = "http://localhost:3000")
	CommonResponse recognize(@RequestBody StudentModel studentModel) {
		FaceRecognizerResponse fResponse = null;
		try {
			fResponse = FaceRecognizer.recognize(studentModel);
		} catch (FaceRecognizerException e) {
			e.printStackTrace();
			return new CommonResponse(e.getMessage(), "500");
		}
		return new CommonResponse(fResponse.toString(),"200");
	}
	
	
}
