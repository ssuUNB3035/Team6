/**
 * @author: Ryan Nitz
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

//TODO: Add an application/GUI class that will take the main method in this one
public class TranscriptReader{
	
	final static int ELEM_SIZE = 6;
	
	//TODO: Move this to the gui once it's ready
    public static void main(String args[]){
        
        File directory = getDirectory();
        File[] transcriptSet = directory.listFiles();
        System.out.println("Transcript count:" + transcriptSet.length);
        
        //TODO:Press parse button on GUI. Should keep it simple on the gui for now
        //on button press: TranscriptReader.parseTranscripts(transcriptSet);
        parseTranscripts(transcriptSet);

        //TODO: apache hook to print the CourseList into excel
        System.out.println(CourseList.printRawList());
    }
    
    
    
    
	
	//TODO: in the future, have the GUI able to change the path in the config file.
	//TODO: add the config file in the package and have this method read from it.
	public static File getDirectory() {
		//File dir = new File("src/" + args[0]);
		//File dir = parse the config file
        File dir = new File("src/transcripts");
        return dir;
	}
	
	//TODO: Add error handling/ boolean response
	//TODO: analyze if the iteration of transcripts should be in seperate method and the transcript line-by-line reading should be alone too??
	public static boolean parseTranscripts(File[] transcriptSet) {
		ArrayList<String> gradeElements = new ArrayList<String>(ELEM_SIZE);
		ArrayList<Transcript> transcriptList = new ArrayList<Transcript>();
	    Scanner sc = null;
	        
		for (File transcript : transcriptSet) {
				
	    	try{
	    		sc = new Scanner(transcript);
	    	}catch(FileNotFoundException f){
	    		f.printStackTrace();
	            System.out.println("Error opening file, skipping: " + transcript.getName());
	        }
	    		
	    	//TODO: analyze line. Transcript may not be useful. Although we do need
	    	//		to check the entire transcript to determine the year of the student.
	    	Transcript tempTranscript = new Transcript(getDirectory().getName());
	    		
	    	//read every line
	        while(sc.hasNext()){
	        	String line = sc.nextLine();
	            line = line.trim();
	                
	            if(!line.isEmpty()) {
	            	gradeElements = getGradeElements(line);
	            	CourseList.addCourse(gradeElements);
	            	tempTranscript.addGrade(gradeElements);             
	            }
	        }
	        transcriptList.add(tempTranscript);
	        System.out.println(tempTranscript.toString());    
		}	
		return true;	
	}
	
	public static ArrayList<String> getGradeElements(String line){
		ArrayList<String> elementSet = new ArrayList<String>(ELEM_SIZE);
        	
		for(int i = 0; i < ELEM_SIZE-1; i++){
			String element = line.substring(0, line.indexOf("  "));
                
			if(i == 3 && element.length() == 4){
				//TODO: Mark what a no grade will be??
				elementSet.add("");
			}else{
				elementSet.add(element);
				line = line.substring(line.indexOf("  "), line.length()).trim();
			}
                
		}
        elementSet.add(line);//this picks up the rest of the line to avoid access string operations.       
		return elementSet;
	}
}