/**
 * @author: Ryan Nitz
 */

import java.util.ArrayList;

public class Transcript {
	
	private ArrayList<Course> courses;
	
	//static to make the class variable increment?
	private int transcriptID;
	
	private String cohortName;
	
	Transcript(String cohort){
		//TODO:increment transcript ID
		this.transcriptID = 1;//filler
		this.cohortName = cohort;
		this.courses = new ArrayList<Course>(); 
	}
	
	
	public boolean addCourse(Course course) {
		return courses.add(course);
	}
	
	public String toString() {
		String template = "Cohort: " + this.cohortName + ", Transcript: " + this.transcriptID;
		for(Course course : courses) {
			template += course.toString();
		}
		return template + "\n";
	}
	
}
