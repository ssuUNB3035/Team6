package team6;

import java.util.ArrayList;

public class Grade {
	
	ArrayList<String> gradeElements;
	
	public Grade(ArrayList<String> gradeElements) {
		this.gradeElements = gradeElements;
	}
	
	public String getCourseNumber() {
		return gradeElements.get(0);
	}
	
	public String getCourseSection() {
		return gradeElements.get(1);
	}
	
	public String getCourseName() {
		return gradeElements.get(2);
	}
	
	public String getLetterGrade() {
		return gradeElements.get(3);
	}
	
	public String getCreditHours() {
		return gradeElements.get(4);
	}
	
	public String getYearCompleted() {
		return gradeElements.get(5);
	}
	
	public String toString() {
		return gradeElements.toString();
	}
}
