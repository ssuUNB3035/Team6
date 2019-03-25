package team6;
/**
 * @author Uwera Ntaganzwa
 */
import javax.swing.*; 
import java.awt.event.*;
import java.io.File;

import javax.swing.filechooser.*;

public class GUI extends JFrame implements ActionListener {
		private static JLabel message;
		GUI(){}
	    public static void main(String args[]){
	       JFrame frame = new JFrame("Student Transcript Analyser");
	       JPanel panel = new JPanel();
	       frame.setSize(450,450);
	       frame.add(panel);

	       
	       JButton uploadButton = new JButton("Add Transcripts");
	       uploadButton.setBounds(50, 200, 150, 30);
	       panel.add(uploadButton);
	       message = new JLabel("No files chosen.");
	       panel.add(message);
	       
	       GUI fileChooser = new GUI();
	       uploadButton.addActionListener(fileChooser);
	       
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	       }
	    
	    public void actionPerformed(ActionEvent e){
	    	 String event = e.getActionCommand();  
	         if (event.equals("Add Transcripts")) {
	        	 /**
	             JFileChooser chooser = new JFileChooser();
	             chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	             int result = chooser.showOpenDialog(null); 

	             if (result == JFileChooser.APPROVE_OPTION){ 
	                File directory = null;
	                directory = chooser.getSelectedFile();
	     			//directory = TranscriptReader.getDirectory();
	     			
	            	File[] transcriptSet = directory.listFiles();
	            	message.setText("Transcript count: " + transcriptSet.length);
	     	        System.out.println("Transcript count: " + transcriptSet.length);
	     	        TranscriptReader.parseTranscripts(transcriptSet);

	     	        //prints to console
	     	        System.out.println(CourseList.printRawList());
	             } 

	             else
	                 message.setText("No transcripts added");*/
	         } 
	    }
}
