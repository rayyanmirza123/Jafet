package com.project.jafet.framework.fr.util;

import com.project.jafet.framework.Constants;

public class FaceRecognizerUtils {

	public static String getRandomFileName(String prefix, String extension) {
		return prefix+(Math.random()*100)+"."+extension;
	}
	
	public static String getRandomFileName() {
		return getRandomFileName(Constants.IMG_PREFIX, Constants.IMG_EXT);
	}
	
}
