package com.project.jafet.framework.fr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.jafet.framework.Constants;
import com.project.jafet.framework.exception.FaceRecognizerException;
import com.project.jafet.framework.fr.FaceRecognizer;
import com.project.jafet.framework.fr.crud.AttendanceCRUD;
import com.project.jafet.framework.fr.crud.StudentCRUD;
import com.project.jafet.framework.fr.model.Student;
import com.project.jafet.framework.fr.model.utils.FaceRecognizerResponse;
import com.project.jafet.framework.req.model.AttendanceModel;
import com.project.jafet.framework.res.model.CommonResponse;
import com.project.jafet.framework.fr.model.Attendance;

@RestController
public class AttendanceController {

	@Autowired
	StudentCRUD studentCrud;
	
	@Autowired
	AttendanceCRUD attendanceCrud;
	
	@PostMapping("/fr")
	@CrossOrigin(origins = "http://localhost:3000")
	CommonResponse recognize(@RequestBody AttendanceModel attendanceModel) {
		FaceRecognizerResponse fResponse = null;
		try {
			fResponse = FaceRecognizer.recognize(attendanceModel);
			Student tempStudent = new Student();
			tempStudent.setFaceEntityId(String.valueOf(fResponse.getLabelId()));
			Student student = studentCrud.findBy(Example.of(tempStudent), null);
			if(student == null) {
				return new CommonResponse("Student Not found", Constants.STUDENT_NOT_FOUND);
			}else {
				Attendance attendance = new Attendance();
				attendance.setClassRoom(attendanceModel.getClassRoom());
				attendance.setRollNumber(String.valueOf(student.getRollNumber()));
				attendance.setStudentId(String.valueOf(student.getId()));
				attendance.setTeacherId(attendanceModel.getTeacherId());
				attendance.setSubject(attendanceModel.getSubject());
				attendanceCrud.save(attendance);
			}
		} catch (FaceRecognizerException e) {
			e.printStackTrace();
			return new CommonResponse(e.getMessage(), "500");
		}
		return new CommonResponse(fResponse.toString(),"200");
	}
	
	
}
