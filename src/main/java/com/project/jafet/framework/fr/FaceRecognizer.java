package com.project.jafet.framework.fr;

import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_COLOR;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.equalizeHist;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import com.project.jafet.framework.Constants;
import com.project.jafet.framework.fr.model.Sequence;
import com.project.jafet.framework.fr.model.TestRequestModel;
import com.project.jafet.framework.req.model.AddUserRequest;
import com.project.jafet.framework.services.ImageProcessor;

public class FaceRecognizer {
	
	public static void train(int label) {
		try {
			CascadeClassifier face_cascade = new CascadeClassifier(Constants.HAAR_CLASSIFIER);
	        org.bytedeco.opencv.opencv_face.FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
	        
	        File root = new File(Constants.TRAINING_DATA);

	        FilenameFilter imgFilter = new FilenameFilter() {
	            public boolean accept(File dir, String name) {
	                name = name.toLowerCase();
	                return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
	            }
	        };

	        File[] imageFiles = root.listFiles(imgFilter);

	        MatVector images = new MatVector(imageFiles.length);
	        
	        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
	        IntBuffer labelsBuf = labels.createBuffer();
	        
	        int counter = 0;
	        
	        for (File image : imageFiles) {
	            Mat img = imread(image.getAbsolutePath(), IMREAD_COLOR);//IMREAD_GRAYSCALE
	            Mat videoMatGray = new Mat();
	    		
	    		cvtColor(img, videoMatGray, COLOR_BGR2GRAY);	
	    		equalizeHist(videoMatGray, videoMatGray);
	            RectVector faces = new RectVector();
	            // Find the faces in the frame:
	            face_cascade.detectMultiScale(videoMatGray, faces); 
	            long size = faces.size();
	            if(size > 0) {
		            for(int i = 0; i < size; i++) {
		            	Rect faceRect = faces.get(i);
		            	int width = faceRect.width();
		            	int height = faceRect.height();
		            	if(width < 800 || height < 800) {
		            		continue;
		            	}
		            	Mat faceImg = new Mat(videoMatGray, faceRect);
		            	//org.bytedeco.opencv.global.opencv_imgproc.rectangle(faceImg,faceRect, new Scalar(0, 255, 0, 1));
		            	org.bytedeco.opencv.global.opencv_imgcodecs.imwrite("faceWithRect"+(Math.random()*100)+".png", faceImg);
		            	//images.put(counter,faceImg);
		            	//labelsBuf.put(counter,label);
		            	//counter++;
		            }
	            }
	        }
	        
	        //faceRecognizer.train(images,labels);
	        //faceRecognizer.save(Constants.TRAINED_MODEL);
	        
	        images.close();
	        faceRecognizer.close();
	        face_cascade.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateModelForFace(TestRequestModel tReqModel, int seqVal) {
		try {
			File inputFile = ImageProcessor.convertBase64ToFile(tReqModel.reqImg);
			Mat imgMat = imread(inputFile.getAbsolutePath(), IMREAD_GRAYSCALE);
			updateModelWithHaarCascade(imgMat, seqVal);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateModelForFace(AddUserRequest addUserReq, int seqVal) {
		try {
			if(addUserReq.getImages() != null) {
				for(String image64 : addUserReq.getImages()) {
					File inputFile = ImageProcessor.convertBase64ToFile(image64);
					Mat imgMat = imread(inputFile.getAbsolutePath(), IMREAD_GRAYSCALE);
					updateModelWithHaarCascade(imgMat, seqVal);
					inputFile.delete();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void recognize(TestRequestModel tReqModel) {
		try {
            File inputFile = ImageProcessor.convertBase64ToFile(tReqModel.reqImg);
			Mat imgMat = imread(inputFile.getAbsolutePath());
			haarCascade(imgMat);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void haarCascade(Mat imgMat) {
		
		CascadeClassifier face_cascade = new CascadeClassifier(Constants.HAAR_CLASSIFIER);
		org.bytedeco.opencv.opencv_face.FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
        faceRecognizer.read(Constants.TRAINED_MODEL);
		
		Mat videoMatGray = new Mat();
		
		cvtColor(imgMat, videoMatGray, COLOR_BGRA2GRAY);	
		equalizeHist(videoMatGray, videoMatGray);
		
        RectVector faces = new RectVector();
        // Find the faces in the frame:
        face_cascade.detectMultiScale(videoMatGray, faces); 
        
        int size = (int) faces.size();
        
        MatVector images = new MatVector(size);
        Mat labels = new Mat(size, 1, CV_32SC1);
        
        IntBuffer labelsBuf = labels.createBuffer();
        
        for(int i = 0; i < faces.size(); i++) {
        	Rect faceRect = faces.get(i);
        	Mat faceImg = new Mat(videoMatGray, faceRect);
        	images.put(i,faceImg);
        	labelsBuf.put(i,1000);
        }
        
        faceRecognizer.train(images, labels);
        
        for(int i =0; i < images.size(); i++) {
        	Mat img = images.get(i);
        	org.bytedeco.opencv.global.opencv_imgproc.rectangle(img, faces.get(i), new Scalar(0, 255, 0, 1));
        	org.bytedeco.opencv.global.opencv_imgcodecs.imwrite("faceWithRect"+(Math.random()*100)+".png", img);
        }
        
        for(int i=0; i < faces.size(); i++) {
            Rect face_i = faces.get(i);

            Mat face = new Mat(videoMatGray, face_i);
            IntPointer label = new IntPointer(1);
            DoublePointer confidence = new DoublePointer(1);
            faceRecognizer.predict(face, label, confidence);
	        // Retrieve recognized user ID
	        int recognizedUserId = label.get(0);
	        double confidenceValue = confidence.get(0);
            System.out.println("recognizedUserId "+recognizedUserId+" confidenceValue "+confidenceValue);
        }
        face_cascade.close();
	}
	
	private static void updateModelWithHaarCascade(Mat image, int seqVal) {
		org.bytedeco.opencv.opencv_face.FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
        faceRecognizer.read(Constants.TRAINED_MODEL);


        MatVector matVector = new MatVector(1);
        Mat labelInt = new Mat(1,1, CV_32SC1);
        
        matVector.put(0, image);
        IntBuffer intBuffer = labelInt.createBuffer();
        
        intBuffer.put(0, seqVal);
        
        faceRecognizer.update(matVector, labelInt);
        faceRecognizer.save(Constants.TRAINED_MODEL);
	}
	
	private static void cascadeTrain(org.bytedeco.opencv.opencv_face.FaceRecognizer faceRecognizer,Mat imgMat, int label) {
		CascadeClassifier face_cascade = new CascadeClassifier(Constants.HAAR_CLASSIFIER);
		
		Mat videoMatGray = new Mat();
		
		cvtColor(imgMat, videoMatGray, COLOR_BGRA2GRAY);	
		equalizeHist(videoMatGray, videoMatGray);
		
        RectVector faces = new RectVector();
        // Find the faces in the frame:
        face_cascade.detectMultiScale(videoMatGray, faces); 
        
        int size = (int) faces.size();
        
        MatVector images = new MatVector(size);
        Mat labels = new Mat(size, 1, CV_32SC1);
        
        IntBuffer labelsBuf = labels.createBuffer();
        
        for(int i = 0; i < faces.size(); i++) {
        	Rect faceRect = faces.get(i);
        	Mat faceImg = new Mat(videoMatGray, faceRect);
        	images.put(i,faceImg);
        	labelsBuf.put(i,1000);
        }
        
        faceRecognizer.train(images, labels);
        face_cascade.close();
	}

}