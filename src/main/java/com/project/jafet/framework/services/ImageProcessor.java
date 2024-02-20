package com.project.jafet.framework.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageProcessor {
	
	
	public static File convertBase64ToFile(String base64) throws Exception {
		String dImgData = base64.split(",")[1];
		byte[] imageBytes = Base64.getDecoder().decode(dImgData);
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
		String fName = getRandom(dImgData)+".png";
		File outputfile = new File(fName);
		outputfile.createNewFile();
		ImageIO.write(img, "png", outputfile);
		return outputfile;
	}
	
	public static String getRandom(String s){
	    Random random = new Random();
	    int i = random.nextInt(s.length());
	    int j = random.nextInt(s.length());
	    int k = random.nextInt(s.length());
	    return String.valueOf(s.charAt(i)+s.charAt(j)+s.charAt(k));
	}

}
