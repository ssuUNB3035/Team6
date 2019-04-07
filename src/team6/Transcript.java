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
	
	private int saintJohnCount;
	private int frederictonCount;
	private int otherInstituteCount;
	
	private ArrayList<Grade> grades;
	
	/**
	 * Will hold a temporary transcript for student data calculations.
	 */
	public Transcript(String transcriptName){
		this.transcriptID = transcriptCount++;
		this.transcriptName = transcriptName;
		this.attemptedCreditHours = 0.00;
		this.grades = new ArrayList<Grade>();
	}
	
	/**
	 * Adds a the elements extracted from the transcript file into a temporary transcript object for student related data
	 * @param gradeElements - The elements of a grade extracted from the transcript file.
	 * @return boolean if the grade was 
	 */
	public boolean addGrade(Grade grade) {
		this.updateLocations(grade.getCourseSection());
		this.grades.add(grade);
		double attemptedCH = Double.parseDouble(grade.getCreditHours());
		return updateGPA(grade.getLetterGrade(), attemptedCH);
		
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
	
	private void updateLocations(String section) {
		if(section.contains("FR")) {
			this.frederictonCount++;
		}else if(section.contains("SJ")) {
			this.saintJohnCount++;
		}else {
			this.otherInstituteCount++;
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
	
	public int getFrederictonCount() {
		return this.frederictonCount;
	}
	
	public int getSaintJohnCount() {
		return this.saintJohnCount;
	}
	
	public int getOtherLocationCount() {
		return this.otherInstituteCount;
	}

	/**
	 * @return template - The string template to display the transcripts details.
	 */
	public String toString() {
		String template = "\nTranscript ID: " + this.transcriptID + "\n"
				+ "Transcript Name: " + this.transcriptName + "\n";
		for(Grade grade : grades) {
			template += grade.toString() + "\n";
		}
		return template += "Credit Hours: " + attemptedCreditHours + "\n"
				+ "Cumulative GPA: " + this.getGPANumber() + " / " + this.getGPALetter() + "\n";
	}
	
}
