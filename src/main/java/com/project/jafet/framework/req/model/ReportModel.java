package com.project.jafet.framework.req.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReportModel {

	private List<AttendanceData> reportData;
	
	public static class AttendanceData{
		public String date;
		@JsonProperty("class")
		public String lass;
		public String rollNo;
		public String name;
	}
	
}
