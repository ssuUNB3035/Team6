package team6;
/**
 * @author Uwera Ntaganzwa
 */
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.Desktop;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
		private static JButton addFile;
		private static JButton parseButton;
		private static JButton excelButton;
		private static JLabel fileMsg;
		private static JLabel message;
		private static JLabel message2;
		ArrayList<Course> sortedList = new ArrayList<>();
		GUI(){}
	    public static void main(String args[]){
	       JFrame frame = new JFrame("Student Transcript Analyser");
	       JPanel panel = new JPanel();
	       frame.setSize(500,450);
	       frame.add(panel);

	       addFile = new JButton("Add a Config File");
	       panel.add(addFile);
	       fileMsg = new JLabel("No transcripts parsed yet.");
	       panel.add(fileMsg);
	       
	       parseButton = new JButton("Parse Transcripts");
	       parseButton.setBounds(50, 200, 150, 30);
	       panel.add(parseButton);
	       message = new JLabel("No transcripts parsed yet.");
	       panel.add(message);
	       parseButton.setVisible(false);
	       message.setVisible(false);
	       
	       excelButton = new JButton("Write Raw List to Excel");
	       message2 = new JLabel("No Raw List spreadsheet yet.");
	       panel.add(excelButton);
	       panel.add(message2);
	       excelButton.setVisible(false);
	       message2.setVisible(false);
	       
	       GUI fileChooser = new GUI();
	       addFile.addActionListener(fileChooser);
	       parseButton.addActionListener(fileChooser);
	       excelButton.addActionListener(fileChooser);
	       
	       
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	    }
	    
	    public void actionPerformed(ActionEvent e){
	    	 String event = e.getActionCommand(); 
	    	 
	    	 if (event.equals("Add a Config File")) {
	    		 JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	    		 int returnValue = chooser.showOpenDialog(null);

	    		 if (returnValue == JFileChooser.APPROVE_OPTION) {
	    			File selectedFile = chooser.getSelectedFile();
	    			try {
						FileHandler.addConfigFile(selectedFile);
						fileMsg.setText("Config file successfully added. Transcripts can now be parsed.");
						parseButton.setVisible(true);
						message.setVisible(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}
	    		 
	    	 }
	         if (event.equals("Parse Transcripts")) { 
	        	 addFile.setVisible(false);
	        	 fileMsg.setVisible(false);
	             File directory = null;
	             directory = TranscriptReader.getDirectory();
	     			
	             File[] transcriptSet = directory.listFiles();
	     	     System.out.println("Transcript count: " + transcriptSet.length);
	     	     
	     	     LevelSchema.getSchemaConfig();
	     	     TranscriptReader.parseTranscripts(transcriptSet);
	     	     message.setText(transcriptSet.length + " transcripts successfully parsed."); //might need a better statement.
	     	     excelButton.setVisible(true);
	     	     message2.setVisible(true);
	     	     //prints to console
	     	     System.out.println(CourseList.printTextRawList());
	         }
	         
	         if(event.equals("Write Raw List to Excel")){
	        	  
	        	  sortedList = CourseList.getCourseList();
	        	  
	        	  try {
					FileHandler.writeRawList(sortedList);
					message2.setText("A Raw List spreadsheet has been created and added.");
				} catch (FileNotFoundException e1) {
					message.setText("Failed to write to excel. File not found.");
					e1.printStackTrace();
				} catch (IOException e1) {
					message.setText("Failed to write to excel.");
					e1.printStackTrace();
				}
	         }
	    }
}
