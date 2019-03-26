 package team6;
/**
 * @author: Ryan Nitz, Keith LeBlanc
 */

import java.util.ArrayList;

public class Course implements Comparable<Course>{
	
	private String courseNum;
	
	private String creditHours;

	private String courseName;

	//TODO: set these to a raw distribution class. 
	private int others;
	
	private int fails;
	
	private int marginals;
	
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
	
	
	//TODO: use the custom ach. schema
	boolean setGrade(String grade) {
		grade = grade.replace("-", "").replace("+","");

		if(grade == LevelSchema.getMargin(0)){
			this.exceeds++;
		}
		else if(grade == LevelSchema.getMargin(1)){
			this.meets++;
		}
		else if(grade == LevelSchema.getMargin(2)){
			this.marginals++;
		}
		else if(grade == LevelSchema.getMargin(3)){
			this.fails++;
		}
		else {
			this.others++;
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
	
	public int getOthersCount() {
		return this.others;
	}
	
	public int getFailsCount() {
		return this.fails;
	}
	
	public int getMarginalsCount() {
		return this.marginals;
	}
	
	public int getMeetsCount() {
		return this.meets;
	}
	
	public int getExceedsCount() {
		return this.exceeds;
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
				+ "\t  " + this.others
				+ "\t  " + this.fails
				+ "\t  " + this.marginals
				+ "\t  " + this.meets
				+ "\t  " + this.exceeds + "\n";
		
		return template;
	}
}
