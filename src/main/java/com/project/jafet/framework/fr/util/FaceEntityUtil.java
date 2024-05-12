package com.project.jafet.framework.fr.util;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.jafet.framework.fr.crud.FaceEntityCRUD;
import com.project.jafet.framework.fr.model.FaceEntity;

public class FaceEntityUtil {

	@Autowired
	FaceEntityCRUD faceEntityCrud;
	
	private static FaceEntityUtil faceEntityUtil;
	
	private FaceEntityUtil() {
		
	}
	
	public static FaceEntityUtil getFaceEntityUtil() {
		if(faceEntityUtil == null) {
			faceEntityUtil = new FaceEntityUtil();
		}
		
		return faceEntityUtil;
	}
	
	public void saveFaceEntity(List<String> images, Integer label, String name) {
		List<FaceEntity> faceEntities = new ArrayList<>();
		for(String image : images) {
			FaceEntity faceEntity = new FaceEntity();
			faceEntity.setLabel(label);
			faceEntity.setName(name);
			faceEntity.setEncodedImage(image);
			faceEntities.add(faceEntity);
		}
		faceEntityCrud.saveAll(faceEntities);
	}
	
	
	
}
