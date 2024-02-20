package com.project.jafet.framework.req.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

	// list of png images to be trained
	public List<String> images;
	// name of the user
	public String name;
	
}
