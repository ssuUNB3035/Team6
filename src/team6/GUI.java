package team6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

public class GUI {

	    public static void main(String args[]){
	       JFrame frame = new JFrame("Student Transcript Analyser");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(750,500);
	       JButton uploadButton = new JButton("Upload Transcript");
	       uploadButton.setBounds(50, 150, 100, 30);
	       frame.add(uploadButton);
	       frame.setVisible(true);
	       
	       
	       
	       	//just threw my min method code in here to have the system run. 
	       	//TODO: couple this all with gui elements
	       	File directory = null;
			directory = TranscriptReader.getDirectory();
			
	        File[] transcriptSet = directory.listFiles();
	        System.out.println("Transcript count:" + transcriptSet.length);
	        TranscriptReader.parseTranscripts(transcriptSet);

	        //prints to console
	        System.out.println(CourseList.printRawList());
	        
	        //TODO: add option to print to file
	        
	        //prints to excel
	        try {
				ExcelWriter.writeToExcel(CourseList.getCourseList());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	   
	    
}
