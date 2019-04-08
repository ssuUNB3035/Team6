package team6;

/**
 * @author Ryan Nitz
 */

import java.util.ArrayList;

public class Grade {
	
    /**
     * A set of all the elements that make up a grade in the transcript
     */
	ArrayList<String> gradeElements;
	
	/**
     * Assigns all of the grade elements to the object
     */
	public Grade(ArrayList<String> gradeElements) {
		this.gradeElements = gradeElements;
	}
	
	/**
     * @return The course number element
     */
	public String getCourseNumber() {
		return gradeElements.get(0);
	}
	
	/**
     * @return The course section element
     */
	public String getCourseSection() {
		return gradeElements.get(1);
	}
	
	/**
     * @return The course name element
     */
	public String getCourseName() {
		return gradeElements.get(2);
	}
	
	/**
     * @return The course letter grade element
     */
	public String getLetterGrade() {
		return gradeElements.get(3);
	}
	
	/**
     * @return The course credit hours element
     */
	public String getCreditHours() {
		return gradeElements.get(4);
	}
	
	/**
     * @return The course year completed element
     */
	public String getYearCompleted() {
		return gradeElements.get(5);
	}
	
	/**
     * @return The course number element
     */
	public String toString() {
		return gradeElements.toString();
	}
}
