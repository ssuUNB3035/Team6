/**
 * @author: Ryan Nitz
 */

import java.util.ArrayList;

public class Transcript {
	
	
	//static to make the class variable increment?
	private int transcriptID;
	
	private String cohortName;
	
	private double attemptedCreditHours;
	
	public Transcript(String cohort){
		//TODO:increment transcript ID
		this.transcriptID = 1;//filler
		this.cohortName = cohort; 
		this.attemptedCreditHours = 0.00;
	}
	
	
	public boolean addGrade(int filler) {
		return true;
	}
	
	public String toString() {
		String template = "Cohort: " + this.cohortName + ", Transcript: " + this.transcriptID;
		template += "\nEnter transcript details here even though we never need to print htem in \n"
				+ "Credit Hours:" + attemptedCreditHours + "\n";
		return template;
	}
	
}
