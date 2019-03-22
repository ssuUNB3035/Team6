package team6;
/**
 * @author: Ryan Nitz
 */

import java.util.ArrayList;

public class Course implements Comparable<Course>{
	
	private String courseNum;
	
	private String creditHours;

	private String courseName;

	//TODO: set these to a raw distribution class. 
	private int other;
	
	private int fails;
	
	private int marginal;
	
	private int meets;
	
	private int exceeds;
	
	
	//TODO: Is this if a student had retaken a course? because equivalences aren't checked here I think?
	//private String replaces = "";

	//NOTE: yes keep the array of all the information so that we can do something with it if ever we need to. 
	public Course(ArrayList<String> courseElements) {
		this.courseNum = courseElements.get(0);
		this.courseName = courseElements.get(2);
		setGrade(courseElements.get(3));
		this.creditHours = courseElements.get(4);
	}
	
	
	//TODO: set the default or use the custom ach. schema
	boolean setGrade(String grade) {
		
		grade = grade.replace("-", "").replace("+","");
		switch(grade) {
			case "A":
				this.exceeds++;
				break;
			case "B":
				this.meets++;
				break;
			case "C":
				this.marginal++;
				break;
			case "D":
				this.fails++;
				break;
			case "F":
				this.fails++;
				break;
			default:
				this.other++;
		}
		return true;
	}
	
	public String getCreditHours() {
		return this.creditHours;
	}
	
	public String getCourseNum() {
		return this.courseNum;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	@Override
	public int compareTo(Course other) {
		if(this.courseNum.compareTo(other.courseNum) > 1) {
			return 1;
		}else if(this.courseNum.compareTo(other.courseNum) < 0) {
			return -1;
		}
		return 0;
	}

	public String toString() {
		String template = this.courseNum + ": \t"
				+ "\t  " + this.other
				+ "\t  " + this.fails
				+ "\t  " + this.marginal
				+ "\t  " + this.meets
				+ "\t  " + this.exceeds + "\n";
		
		return template;
	}
}
