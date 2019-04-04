package team6;
/**
 * @author Uwera Ntaganzwa
 */
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{
		private static int parseCount;
		private static JButton parseButton;
		private static JButton excelButton;
		private static JLabel message;
		private static JLabel message2;
		ArrayList<Course> sortedList = new ArrayList<>();
		GUI(){}
	    public static void main(String args[]){
	    	
	    	
	    	//THIS IS THE TESTING BLOCK FOR READING AREA FROM EXCEL.
	    	//CHECK CONSOLE ON LAUNCH, rand = placeholder for hardcoded file name
	    	try{
	    		ArrayList<String> areaNames = FileHandler.getAreaNames("rand");
				FileHandler.getAreaCourses("rand", areaNames.get(1));
				FileHandler.retrieveStoredFiles();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    
	       JFrame frame = new JFrame("Student Transcript Analyser");
	       JPanel panel = new JPanel();
	       frame.setSize(500,450);
	       frame.add(panel);
	       
	       parseButton = new JButton("Parse Transcripts");
	       parseButton.setBounds(50, 200, 150, 30);
	       panel.add(parseButton);
	       message = new JLabel("No transcripts parsed yet.");
	       panel.add(message);
	       parseCount = 0;
	       
	       excelButton = new JButton("Write Raw List to Excel");
	       message2 = new JLabel("No Raw List spreadsheet yet.");
	       panel.add(excelButton);
	       panel.add(message2);
	       excelButton.setVisible(false);
	       message2.setVisible(false);
	       
	       GUI fileChooser = new GUI();
	       parseButton.addActionListener(fileChooser);
	       excelButton.addActionListener(fileChooser);
	       
	       
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	    }
	    
	    public void actionPerformed(ActionEvent e) throws IllegalArgumentException{
	    	 String event = e.getActionCommand(); 

	         if (event.equals("Parse Transcripts")) { 
		        if (parseCount > 0) {
		        	message.setText("Transcripts in this cohort have already been parsed.");
		        	throw new IllegalArgumentException();
		        }
		        try {	 
		             File directory = null;
		             directory = TranscriptReader.getDirectory();
		     			
		             File[] transcriptSet = directory.listFiles();
		     	     System.out.println("Transcript count: " + transcriptSet.length);
		     	     
		     	     LevelSchema.getSchemaConfig();
		     	     Cohort cohort = TranscriptReader.parseTranscripts(transcriptSet);
		     	     message.setText(transcriptSet.length + " transcripts successfully parsed."); //might need a better statement.
		     	     excelButton.setVisible(true);
		     	     message2.setVisible(true);
		     	     //prints to console
		     	     System.out.println(CourseList.printTextRawList());
		     	     //NOTE: automatically prints cohorts global to the excel. might want to change?
		     	     FileHandler.writeGlobalDistribution(cohort);
		     	     FileHandler.writeYearDistribution(cohort);
		     	     parseCount++; 
		        } catch (IllegalArgumentException e1) {
		        	message.setText("new message");
		        	e1.printStackTrace();
		        }
	         }
	         
	         if(event.equals("Write Raw List to Excel")){
	        	  
	        	  sortedList = CourseList.getCourseList();
	        	  
	        	  try {
					FileHandler.writeRawList(sortedList);
					message2.setText("A Raw List spreadsheet has been added to a Results workbook.");
				} catch (FileNotFoundException e1) {
					message.setText("Failed to write to excel. Results file not found.");
					e1.printStackTrace();
				} catch (IOException e1) {
					message2.setText("Failed to write to excel.");
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					message2.setText("The workbook already contains a sheet named 'Raw List'");
				}
	         }
	    }
}
