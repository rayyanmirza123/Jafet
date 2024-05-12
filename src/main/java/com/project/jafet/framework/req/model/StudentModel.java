package com.project.jafet.framework.req.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudentModel extends com.project.jafet.framework.fr.model.Student {
	
	public List<String> images;

}
