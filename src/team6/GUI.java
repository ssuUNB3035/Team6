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


public class GUI extends JFrame implements ActionListener {
		/**
		 * Set of transcripts parsed in one run
		 */
		Cohort cohort;
		/**
		 * The name of the directory from which a results file is selected
		 */
		private static String directoryName = "";
		/**
		 * The file containing area and equivalences and to which results are written
		 */
		private static String fileName = readInFile();
		/**
		 * The number of times the parse button is clicked
		 */
		private static int parseCount;
		/**
		 * The number of times the write to excel button is clicked
		 */
		private static int writeCount;
		/**
		 * Button for parsing a given set of transcripts
		 */
		private static JButton parseButton;
		/**
		 * Button for writing results to an excel file
		 */
		private static JButton excelButton;
		/**
		 * Button for retrieve stored files
		 */
		private static JButton retrieveFilesButton;
		/**
		 * The message displayed when the parseButton is clicked
		 */
		private static JLabel message;
		/**
		 * The message displayed when the excelButton is clicked
		 */
		private static JLabel message2;
		/**
		 * The message displayed when the retrieve files button is clicked
		 */
		private static JLabel retrieveMessage;
		/**
		 * The names of the files found when the retrieveFilesButton is clicked
		 */
		private static JLabel listedFiles;
		/**
		 * A raw list of courses created when transcripts have been parsed successfully
		 */
		ArrayList<Course> sortedList = new ArrayList<>();
		
		GUI(){}
		
		
	    public static void main(String args[]){
	       JFrame frame = new JFrame("Student Transcript Analyser");
	       JPanel panel = new JPanel();
	       frame.setSize(500,450);
	       frame.add(panel);
	       
	       /**
			* Initializes parseButton and sets a default message corresponding to the action performed on the button
			*/
	       parseButton = new JButton("Parse Transcripts");
	       parseButton.setBounds(50, 200, 150, 30);
	       panel.add(parseButton);
	       message = new JLabel("No transcripts parsed yet.");
	       panel.add(message);
	       parseCount =0;
	       
	       /**
			* Initializes & hides the write to excel button and sets a default message corresponding to the action performed on the button
			*/
	       excelButton = new JButton("Write Results to Excel");
	       message2 = new JLabel("No workbook created yet.");
	       panel.add(excelButton);
	       panel.add(message2);
	       writeCount = 0;
	       excelButton.setVisible(false);
	       message2.setVisible(false);
	       
	       /**
			* Initializes & hides the retrieveFilesButton and sets a default message corresponding to the action performed on the button
			*/
	       retrieveFilesButton = new JButton("Retrieve Stored Files");
	       retrieveMessage = new JLabel("");
	       listedFiles = new JLabel("");
	       panel.add(retrieveFilesButton);
	       panel.add(retrieveMessage);
	       panel.add(listedFiles);
	       retrieveFilesButton.setVisible(false);
	       retrieveMessage.setVisible(false);
	       listedFiles.setVisible(false);
	       
	       /**
	        * Adds event listeners for all buttons
	        */
	       GUI fileChooser = new GUI();
	       parseButton.addActionListener(fileChooser);
	       excelButton.addActionListener(fileChooser);
	       retrieveFilesButton.addActionListener(fileChooser);	       
	       
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	    }
	    
	    public void actionPerformed(ActionEvent e){
	    	 String event = e.getActionCommand(); 
	    	 /**
	    	  * Parses Transcripts when parseButton is clicked
	    	  * @throws IllegalArgumentException - When the user tries to parse the same transcript multiple times
	    	  * @throws IOException - When transcripts cannot be opened
	    	  */
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
		     	     cohort = TranscriptReader.parseTranscripts(transcriptSet);
		     	     message.setText(transcriptSet.length + " transcripts successfully parsed.");
		     	     excelButton.setVisible(true);
		     	     message2.setVisible(true);
		     	     //prints to console
		     	     System.out.println(CourseList.printTextRawList());
		     	     //Automatically prints cohorts global to the excel. 
		     	     
		     	     FileHandler.writeGlobalDistribution(cohort, fileName);
		     	     AreaList.makeAreaList();

		     	     parseCount++; 
		        } catch (IllegalArgumentException e1) {
		        	message.setText("Error parsing transcripts. This cohort may have already been parsed.");
		        } catch (IOException e1) {
					e1.printStackTrace();
				}
	         }
	         /**
	          * Writes results to specified output file when clicked
	          * @throws FileNotFoundException - When the output file is not found
	          * @throws IllegalArgumentException - When the output file has already been written to
	          * @throws IOException - When specified file cannot be opened/read
	          */
	         if(event.equals("Write Results to Excel")){
	        	 if (writeCount > 0) {
			        	message.setText("A results workbook has already been created.");
			        	throw new IllegalArgumentException();
			        }
	        	  sortedList = CourseList.getCourseList();
	        	  
	        	  try {
					FileHandler.writeRawList(sortedList, fileName);
					FileHandler.writeMasterList(cohort, fileName);
					FileHandler.writeAreaDistribution(AreaList.getAreas(), fileName);
					message2.setText("Results have been written to an excel workbook.");
					retrieveFilesButton.setVisible(true);
					retrieveMessage.setVisible(true);
					listedFiles.setVisible(true);
				} catch (FileNotFoundException e1) {
					message.setText("File not found.");
					e1.printStackTrace();
				}catch(IllegalArgumentException ia) {
					message.setText("This excel had already been written to. Please delete output sheets or define a new workbook");
				}catch (IOException e1) {
					message.setText("Failed to write to excel.");
					e1.printStackTrace();
				}
	         }
	         /**
	          * Lists the files in the system available to the user for further processing
	          */
	         if(event.equals("Retrieve Stored Files")){
	        	 retrieveMessage.setText("The following files are available for further processing:\n" + FileHandler.retrieveStoredFiles());	 
	         }
}
	    /**
	     * Allows a user to select an output file
	     * @return name - The output file selected when the program starts
	     */
	    private static String readInFile() {
	    	String source = System.getProperty("user.dir");
	    	JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select your Config File"); //name for chooser
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("Files", ".xlsx"); //filter to show only that
            fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
            //fileChooser.addChoosableFileFilter(filter);
            fileChooser.setSelectedFile(new File(source)); //when you want to show the name of file into the chooser
            fileChooser.setVisible(true);
            int result = fileChooser.showOpenDialog(fileChooser);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                directoryName = fileChooser.getSelectedFile().getAbsolutePath();
            } else {
                return "NULL";
            }
            
      	  String name = fileChooser.getSelectedFile().getAbsolutePath();
      	  if (!name.contains(".")) {
      		  name = name + ".xlsx";
      	  }
      	  else if (name.substring(name.lastIndexOf("."), name.length()) != ".xlsx") {
      		  name = name.replace(name.substring(name.lastIndexOf("."), name.length()), ".xlsx");
      		  System.out.println(name);
      	  }
      	  
      	  return name;
	    }
}
