package com.project.jafet.framework.fr.util;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.jafet.framework.fr.crud.FaceEntityCRUD;
import com.project.jafet.framework.fr.model.FaceEntity;

public class FaceEntityUtil {
	
	private static FaceEntityUtil faceEntityUtil;
	
	private static FaceEntityCRUD faceEntityCRUD;
	
	private FaceEntityUtil() {
		
	}
	
	public static FaceEntityUtil getFaceEntityUtil(FaceEntityCRUD faceEntityCRUDE) {
		if(faceEntityUtil == null) {
			faceEntityUtil = new FaceEntityUtil();
		}
		faceEntityCRUD = faceEntityCRUDE;
		return faceEntityUtil;
	}
	
	public void saveFaceEntity(String image, Integer label, String name) {
		List<FaceEntity> faceEntities = new ArrayList<>();
		FaceEntity faceEntity = new FaceEntity();
		faceEntity.setLabel(label);
		faceEntity.setName(name);
		faceEntity.setEncodedImage(image);
		faceEntities.add(faceEntity);
		faceEntityCRUD.saveAll(faceEntities);
	}	
}
