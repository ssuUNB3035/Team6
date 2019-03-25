/**
 * @author Uwera Ntaganzwa
 */
import javax.swing.*; 
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
		private static JLabel message;
		private static JLabel message2;
		ArrayList<Course> sortedList = new ArrayList<>();
		GUI(){}
	    public static void main(String args[]){
	       JFrame frame = new JFrame("Student Transcript Analyser");
	       JPanel panel = new JPanel();
	       frame.setSize(400,350);
	       frame.add(panel);

	       
	       JButton uploadButton = new JButton("Add & Parse Transcripts");
	 
	       uploadButton.setBounds(50, 200, 150, 30);
	       panel.add(uploadButton);
	       message = new JLabel("No files chosen.");
	       panel.add(message);
	       
	       JButton excelButton = new JButton("Write Raw List to Excel");
	       message2 = new JLabel("No Raw List spreadsheet yet.");
	       panel.add(excelButton);
	       panel.add(message2);
	       
	       GUI fileChooser = new GUI();
	       uploadButton.addActionListener(fileChooser);
	       excelButton.addActionListener(fileChooser);
	       
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	       }
	    
	    public void actionPerformed(ActionEvent e){
	    	 String event = e.getActionCommand();  
	         if (event.equals("Add & Parse Transcripts")) { 
	             File directory = null;
	             directory = TranscriptReader.getDirectory();
	     			
	             File[] transcriptSet = directory.listFiles();
	     	     System.out.println("Transcript count: " + transcriptSet.length);
	     	     TranscriptReader.parseTranscripts(transcriptSet);
		     message.setText(transcriptSet.length + " transcripts successfully parsed."); //might need a better statement.

	     	        //prints to console
	     	     System.out.println(CourseList.printRawList());
	         }
	         if(event.equals("Write Raw List to Excel")){
	        	  
	        	  sortedList = CourseList.getCourseList();
	        	  sortedList.sort(null);
	        	  try {
					ExcelWriter.writeToExcel(sortedList);
					message2.setText("A Raw List spreadsheet has been created.");
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
