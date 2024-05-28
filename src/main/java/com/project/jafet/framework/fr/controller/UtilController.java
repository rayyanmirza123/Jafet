package com.project.jafet.framework.fr.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.jafet.framework.fr.FaceRecognizer;
import com.project.jafet.framework.fr.crud.AttendanceCRUD;
import com.project.jafet.framework.fr.crud.StudentCRUD;
import com.project.jafet.framework.fr.crud.utils.GeoCRUD;
import com.project.jafet.framework.fr.crud.utils.RegionCRUD;
import com.project.jafet.framework.fr.crud.utils.SubGeoCRUD;
import com.project.jafet.framework.fr.model.utils.Geo;
import com.project.jafet.framework.req.model.ReportModel;
import com.project.jafet.framework.req.model.ReportModel.AttendanceData;
import com.project.jafet.framework.req.model.ReportRequest;
import com.project.jafet.framework.fr.model.Attendance;
import com.project.jafet.framework.fr.model.Student;

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
	
	@Autowired
	AttendanceCRUD attendanceCrud;
	
	@Autowired
	StudentCRUD studentCrud;
	
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
	
	@GetMapping(path="reports", produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:3000")
	public ReportModel reports(@RequestBody ReportRequest reportRequest){
		Attendance attendance = new Attendance();
		if(reportRequest.getDate() != null) {
			attendance.setOn(reportRequest.getDate());
		}
		if(reportRequest.getLass() != null) {
			attendance.setClassRoom(reportRequest.getLass());
		}
		if(reportRequest.getSemester() != null) {
			attendance.setSemester(reportRequest.getSemester());
		}
		List<Attendance>  attendances = attendanceCrud.findAll(Example.of(attendance));
		ReportModel reportModel = new ReportModel();
		List<AttendanceData> attDatas = new ArrayList<>();
		for(Attendance attn : attendances) {
			Student std = new Student();
			std.setId(Integer.valueOf(attn.getStudentId()));
			Student fStd = studentCrud.findBy(Example.of(std), null);
			if(fStd != null) {
				AttendanceData attData = new AttendanceData();
				attData.rollNo = String.valueOf(fStd.getRollNumber());
				attData.name = fStd.getFirstName() + fStd.getLastName();
				attData.lass = fStd.getCourse();
				attData.date = attn.getOn().toString();
				attDatas.add(attData);
			}
		}
		reportModel.setReportData(attDatas);
		return reportModel;
	}
	

}