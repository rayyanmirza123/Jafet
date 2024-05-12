package com.project.jafet.framework.fr.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.jafet.framework.fr.crud.SequenceCRUD;
import com.project.jafet.framework.fr.model.Sequence;

public class SequenceUtil {
	
	@Autowired
	SequenceCRUD sequenceCrud;
	
	private static SequenceUtil sequenceUtil;
	
	private SequenceUtil() {
		
	}
	
	public static SequenceUtil getSequencer() {
		if(sequenceUtil == null) {
			sequenceUtil = new SequenceUtil();
		}
		
		return sequenceUtil;
	}
	
	public int getNextSeq(String labelName) {
		
		Sequence sequence = sequenceCrud.findBySequenceName(labelName);
		
		int label = sequence.getValue();
		
		sequence.setValue(label+10);
		
		sequenceCrud.save(sequence);
		
		return label+10;
		
	}
	
}