package com.project.jafet.framework.fr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.jafet.framework.Constants;
import com.project.jafet.framework.fr.FaceRecognizer;
import com.project.jafet.framework.fr.crud.SequenceCRUD;
import com.project.jafet.framework.fr.crud.StudentCRUD;
import com.project.jafet.framework.fr.util.FaceEntityUtil;
import com.project.jafet.framework.fr.util.SequenceUtil;
import com.project.jafet.framework.req.model.StudentModel;
import com.project.jafet.framework.res.model.CommonResponse;

@RestController
public class StudentController {

	public static final String MODULE = StudentController.class.getName();
	
	@Autowired
	StudentCRUD studentCrud;
	
	@Autowired
	SequenceCRUD sequenceCrud;
	
	@PostMapping("/add_student")
	@CrossOrigin(origins = "http://localhost:3000")
	CommonResponse addStudent(@RequestBody StudentModel student) {
		Integer studentId = SequenceUtil.getSequencer(sequenceCrud).getNextSeq(Constants.STUDENT_SEQ);
		try {
			boolean isSuccess = FaceRecognizer.updateModelAddUserWithLabel(student.images, studentId);
			if(isSuccess) {
				FaceEntityUtil.getFaceEntityUtil().saveFaceEntity(student.getImages(), studentId, student.getFirstName());
				student.setFaceEntityId(String.valueOf(studentId));
				studentCrud.save(student);
				return new CommonResponse("Student added successfully",Constants.COMMON_RESP_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponse(e.getMessage(), Constants.COMMON_RESP_FAILURE);
		}
		return new CommonResponse("Unable to add student", Constants.COMMON_RESP_ERROR);	
	}
	
	@PostMapping("/update_student")
	@CrossOrigin(origins = "http://localhost:3000")
	CommonResponse updateStudent(@RequestBody StudentModel student) {
		studentCrud.save(student);
		return new CommonResponse();
	}
	
	@PostMapping("/delete_student")
	@CrossOrigin(origins = "http://localhost:3000")
	CommonResponse deleteStudent(@RequestBody StudentModel student) {
		studentCrud.delete(student);
		return new CommonResponse();
	}
	
}
