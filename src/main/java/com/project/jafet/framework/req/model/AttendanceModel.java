package com.project.jafet.framework.req.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AttendanceModel {
	
	public String classRoom;
	
	public String teacherId;
	
	public String subject;
	
	public List<String> images;

}
