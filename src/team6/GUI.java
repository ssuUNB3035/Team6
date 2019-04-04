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

public class GUI extends JFrame implements ActionListener{
		private static int parseCount;
		private static JButton parseButton;
		private static JButton excelButton;
		private static JLabel message;
		private static JLabel message2;
		private static JFileChooser jchooser;
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
	       
	       GUI filejchooser = new GUI();
	       parseButton.addActionListener(filejchooser);
	       excelButton.addActionListener(filejchooser);
	       
	       
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
		        	jchooser = new JFileChooser(); 
		        	 jchooser.setCurrentDirectory(new java.io.File("."));
		        	 jchooser.setDialogTitle("Select Transcript Directory");
		        	 jchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        	 jchooser.setAcceptAllFileFilterUsed(false);

		        	 if (jchooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
		        		 System.out.println("You're path: " + jchooser.getSelectedFile() + "\\");
		        	 }
		        	 else {
		        		 System.out.println("No Selection ");
		        	 }
		             
		             String directoryName = jchooser.getSelectedFile() + "\\";
		             System.out.println(directoryName);
		             File directory = new File(directoryName);
		             directory.mkdir();
		             
		             File[] transcriptSet = directory.listFiles();
		     	     System.out.println("Transcript count: " + transcriptSet.length);
		     	     
		     	     LevelSchema.getSchemaConfig();
		     	     Cohort cohort = null;
					try {
						cohort = TranscriptReader.parseTranscripts(transcriptSet);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
