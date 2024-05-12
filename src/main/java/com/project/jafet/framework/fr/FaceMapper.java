package com.project.jafet.framework.fr;

import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;

import java.nio.IntBuffer;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
//import org.bytedeco.opencv.opencv_face.FaceRecognizer;
//import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
//import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Rect;

public class FaceMapper {

	public static final String MODULE  = FaceMapper.class.getName();
	
	// face-recognizer local-binary-pattern-histogram
	//private static FaceRecognizer lbphFaceRecognizer;
	
	//
	
	/*
	 * static { lbphFaceRecognizer = LBPHFaceRecognizer.create(); }
	 */
	
	public static class FaceVec {
		public MatVector images;
		public Mat labels;
		public IntBuffer labelsBuf;
		
		FaceVec(Mat	videoMatGray, RectVector vector, int label){
			long size = vector.size();
			images = new MatVector(size);
			labels = new Mat(size, 1, CV_32SC1);
			labelsBuf = labels.createBuffer();
			
			for(int i =	0;	i	<	vector.size();	i++) {
				Rect faceRect = vector.get(i);
				try (Mat face = new Mat(videoMatGray, faceRect)) {
					images.put(i, face);
				}
				labelsBuf.put(i, label);
			}
		}
		
	}
	
	
	
	
}