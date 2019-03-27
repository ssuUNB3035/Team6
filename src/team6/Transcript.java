package team6;
/**
 * @author: Ryan Nitz
 */

import java.util.ArrayList;

public class Transcript {
	
	private static int transcriptCount = 1;
	
	private int transcriptID;
	
	private String cohortName;
	
	private double creditHours;
	
	private ArrayList<String> grades;
	
	/**
	 * Will hold a temporary transcript for student data calculations.
	 * @param cohort - The name of the cohort that the transcript belongs to.
	 */
	public Transcript(String cohort){
		this.transcriptID = transcriptCount++;
		this.cohortName = cohort;
		this.creditHours = 0.00;
		this.grades = new ArrayList<String>();
	}
	
	//TODO: take the grade and credit hours and update GPA every time a grade is added to this temp transcript. 
	/**
	 * Adds a the elements extracted from the transcript file into a temporary transcript object for student related data
	 * @param gradeElements - The elements of a grade extracted from the transcript file.
	 * @return boolean if the grade was 
	 */
	public boolean addGrade(ArrayList<String> gradeElements) {
		creditHours += Double.parseDouble(gradeElements.get(4));
		grades.add(gradeElements.toString());
		return true;
	}
	
	public String toString() {
		String template = "Cohort: " + this.cohortName + ", Transcript: " + this.transcriptID + "\n";
		for(String grade : grades) {
			template += grade + "\n";
		}
		return template += "Credit Hours: " + creditHours + "\n";
	}
	
}
