package com.project.jafet.framework.res.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {

	public CommonResponse() {
		
	}
	
	
	public CommonResponse(String message, String code) {
		responseMessage = message;
		responseCode = code;
	}
	
	public String responseMessage;
	public String responseCode;
	
}