/**
 * @author: Ryan Nitz
 */

import java.util.ArrayList;

public class Course {
	
	
	private String courseNum;
	
	private String campus;
	
	private String courseName;
	
	private String letterGrade;
	
	private String creditHours;
	
	private String year;
	
	
	/*
	private int other;
	
	private int fails;
	
	private int mraginal;
	
	private int meets;
	
	private int exceeds;
	
	private String creditHours
	*/
	
	//TODO: Is this if a student had retaken a course? because equivalences aren't accounted for here I think?
	//private String replaces = "";

	//NOTE: yes keep the array of all the information so that we can do something with it if ever we need to. 
	public Course(ArrayList<String> courseElements) {
		
		this.courseNum = courseElements.get(0);
		this.campus = setCampus(courseElements.get(1));
		this.courseName = courseElements.get(2);
		this.letterGrade = setGrade(courseElements.get(3));
		this.creditHours = courseElements.get(4);
		this.year = courseElements.get(5);
		
		CourseList.addCourse(this.courseNum, this.letterGrade);
	}
	
	
	
	//TODO: What are we returning as the campus name?
	private String setCampus(String sectionCode) {
		
		String prefix = sectionCode.substring(0, 2);
		
		if(prefix.equals("FR")) {
			return "Fredericton";
		}else if(prefix.equals("SJ")) {
			return "Saint John";
		}else {
			return "N/A";
		}
	}
	
	//TODO:decide how to trim grades.
	//Trim the grade.
	private String setGrade(String grade) {
		return grade;
	}
	
	public String getName() {
		return this.courseName;
	}
	

	public String toString() {
		return "\t" + courseNum 	+ ", "
					+ campus 		+ ", "
					+ courseName 	+ ", "
					+ letterGrade 	+ ", "
					+ creditHours 	+ ", "
					+ year 			+ "\n";
	}
	
}
