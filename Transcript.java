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
	
	public Transcript(String cohort){
		this.transcriptID = transcriptCount++;
		this.cohortName = cohort;
		this.creditHours = 0.00;
		this.grades = new ArrayList<String>();
	}
	
	//TODO: take the grade and credit hours and update GPA every time a grade is added to this temp transcript. 
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
