package com.project.jafet.framework.req.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReportRequest {

	@JsonProperty("class")
	private String lass;
	private String semester;
	private Timestamp date;
	
}
