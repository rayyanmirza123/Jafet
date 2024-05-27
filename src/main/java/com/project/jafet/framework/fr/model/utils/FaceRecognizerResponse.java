package com.project.jafet.framework.fr.model.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FaceRecognizerResponse {

	private Integer labelId;
	
	private Double confidenceValue; 
	
	private String haarDetectedImg;
	
	
	@Override
	public String toString() {
		return String.valueOf(confidenceValue);
	}
	
}
