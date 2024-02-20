package com.project.jafet.framework.fr.util;

import com.project.jafet.framework.fr.crud.SequenceCRUD;
import com.project.jafet.framework.fr.model.Sequence;

public class SequenceUtil {
	
	public static int getNextSeq(SequenceCRUD sequenceCrud, String labelName) {
		
		Sequence sequence = sequenceCrud.findBySequenceName(labelName);
		
		int label = sequence.getValue();
		
		sequence.setValue(label+10);
		
		sequenceCrud.save(sequence);
		
		return label+10;
		
		
	}
	
}
