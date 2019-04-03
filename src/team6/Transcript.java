package team6;
/**
 * @author: Ryan Nitz
 */

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Transcript {
	
	private static int transcriptCount = 1;
	
	private int transcriptID;
	
	private String transcriptName;
	
	private double attemptedCreditHours;
	
	private double achievedCreditHours;
	
	private double totalCreditHours;
	
	private double gpaNumber;
	
	private String gpaLetter;
	
	//this is used only for output standards
	private ArrayList<String> gradeStrings;
	
	//private ArrayList<String[]> gradeElements;
	
	/**
	 * Will hold a temporary transcript for student data calculations.
	 */
	public Transcript(String transcriptName){
		this.transcriptID = transcriptCount++;
		this.transcriptName = transcriptName;
		this.attemptedCreditHours = 0.00;
		this.gradeStrings = new ArrayList<String>();
		//this.gradeElements = new ArrayList<String[]>();
	}
	
	/**
	 * Adds a the elements extracted from the transcript file into a temporary transcript object for student related data
	 * @param gradeElements - The elements of a grade extracted from the transcript file.
	 * @return boolean if the grade was 
	 */
	public boolean addGrade(ArrayList<String> gradeElements) {
		this.gradeStrings.add(gradeElements.toString());
		//this.gradeElements.add((String[]) gradeElements.toArray());
		double attemptedCH = Double.parseDouble(gradeElements.get(4));
		return updateGPA(gradeElements.get(3), attemptedCH);
		
	}
	
	/**
	 * For every new grade that is entered, we must keep the transcripts GPA updated.
	 * @param grade - The new grade.
	 * @param attemptedCH - The attempted credit hours from the new grade
	 * @return boolean, If the GPA was updated.
	 */
	private boolean updateGPA(String grade, double attemptedCH) {
		ArrayList<String> gradeSet = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "F"));
		grade = grade.trim().replace("-", "").replace("+","");
		
		if(gradeSet.contains(grade)) {
			switch(grade) {
				case "A":
					this.achievedCreditHours += 4.00 * attemptedCH;
					break;
				case "B":
					this.achievedCreditHours += 3.00 * attemptedCH;
					break;
				case "C":
					this.achievedCreditHours += 2.00 * attemptedCH;
					break;
				case "D":
					this.achievedCreditHours += 1.00 * attemptedCH;
					break;
				default:
					//also a fail
					this.achievedCreditHours += 0.00 * attemptedCH;
					break;
			}
			
			this.attemptedCreditHours += attemptedCH;
			this.totalCreditHours = this.achievedCreditHours/this.attemptedCreditHours;
			updateGPALetter();
			this.gpaNumber = totalCreditHours;

			return true;
		}
		return false;
	}
	
	/**
	 * Updates the letter GPA for the transcript.
	 */
	private void updateGPALetter() {
		if(totalCreditHours > 3.6){
            this.gpaLetter = "A";
        }else if(this.gpaNumber > 2.7){
        	this.gpaLetter = "B";
        }else if(this.gpaNumber > 1.7){
        	this.gpaLetter = "C";
        }else if(this.gpaNumber > 1.0){
        	this.gpaLetter = "D";
        }else if(this.gpaNumber > 0.0){
        	this.gpaLetter = "F";
        }
	}
	
	/**
	 * @return gpaLetter - The letter grade for the GPA
	 */
	public String getGPALetter() {
		return this.gpaLetter;
	}
	
	/**
	 * @return totalCreditHours - The credit hours formated into a two decimal place string
	 */
	public String getGPANumber() {
		NumberFormat fmt = new DecimalFormat("#0.00");
		return fmt.format(this.totalCreditHours);
	}
	
	/**
	 * @return attemptedCreditHours - The max credit hours that they could have achieved
	 */
	public double getAttemptedCreditHours() {
		return attemptedCreditHours;
	}
	
	/**
	 * @return achievedCreditHours - The actual achieved credit hours after completing the course.
	 */
	public double getAchievedCreditHours() {
		return this.achievedCreditHours;
	}
	
	/**
	 * @return totalCreditHours - The credit hours as the double.
	 */
	public double getTotalCreditHours() {
		return this.totalCreditHours;
	}

	/**
	 * @return template - The string template to display the transcripts details.
	 */
	public String toString() {
		String template = "Transcript ID: " + this.transcriptID + "\n"
				+ "Transcript Name: " + this.transcriptName + "\n";
		for(String grade : gradeStrings) {
			template += grade + "\n";
		}
		return template += "Credit Hours: " + attemptedCreditHours + "\n"
				+ "Cumulative GPA: " + this.getGPANumber() + " / " + this.getGPALetter() + "\n";
	}
	
}
