package com.project.jafet.framework.fr.util;

import com.project.jafet.framework.Constants;
import com.project.jafet.framework.fr.crud.SequenceCRUD;
import com.project.jafet.framework.fr.model.Sequence;

public class SequenceUtil {
	
	private SequenceCRUD sequenceCrud;
	
	private static SequenceUtil sequenceUtil;
	
	private SequenceUtil() {
		
	}
	
	public static SequenceUtil getSequencer(SequenceCRUD sequenceCrud) {
		if(sequenceUtil == null) {
			sequenceUtil = new SequenceUtil();
		}
		sequenceUtil.sequenceCrud = sequenceCrud;
		return sequenceUtil;
	}
	
	public int getNextSeq(String labelName) {
		
		Sequence sequence = sequenceCrud.findBySequenceName(labelName);
		
		int label = sequence.getValue();
		
		sequence.setValue(label	+	Constants.INC);
		
		sequenceCrud.save(sequence);
		
		return label;
		
	}
	
}