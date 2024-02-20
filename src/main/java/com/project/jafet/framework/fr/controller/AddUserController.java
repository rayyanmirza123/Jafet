package com.project.jafet.framework.fr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.jafet.framework.Constants;
import com.project.jafet.framework.fr.FaceRecognizer;
import com.project.jafet.framework.fr.crud.FaceEntityCRUD;
import com.project.jafet.framework.fr.crud.SequenceCRUD;
import com.project.jafet.framework.fr.util.SequenceUtil;
import com.project.jafet.framework.req.model.AddUserRequest;
import com.project.jafet.framework.res.model.CommonResponse;

@RestController
public class AddUserController {
	
	public static final String MODULE = AddUserController.class.getName();
	
	@Autowired
	FaceEntityCRUD faceEntityCrud;
	
	@Autowired
	SequenceCRUD sequenceCrud;
	
	@PostMapping("add_user")
	@CrossOrigin(origins = "http://localhost:3000")
	CommonResponse addUser(@RequestBody AddUserRequest addUserReq) {
		int seq = SequenceUtil.getNextSeq(sequenceCrud, Constants.FACE_SEQ);
		FaceRecognizer.updateModelForFace(addUserReq, seq);
		return new CommonResponse();
	}
	

}