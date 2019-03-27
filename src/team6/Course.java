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
	
	/**
	 * Increments the appropriate level depending on the grade provided
	 * @param grade - The letter grade achieved by a student taking this course
	 * @return boolean - If the grade was accounted for successfully or not.
	 */
	boolean setGrade(String grade) {
		grade = grade.replace("-", "").replace("+","");
		

		if(grade.equals(LevelSchema.getMargin(3))){
			this.exceeds++;
			//System.out.println("grade in: " + grade + " = exceeds");
		}
		else if(grade.equals(LevelSchema.getMargin(2))){
			this.meets++;
			//System.out.println("grade in: " + grade + " = meets");
		}
		else if(grade.equals(LevelSchema.getMargin(1))){
			this.marginals++;
			//System.out.println("grade in: " + grade + " = marginals");
		}
		else if(grade.equals(LevelSchema.getMargin(0))){
			this.fails++;
			//System.out.println("grade in: " + grade + " = fails");
		}
		else {
			this.others++;
			//System.out.println("grade in: " + grade + " = others");
		}
		
		return true;
	}
	
	/**
	 * @return The course number / course code
	 */
	public String getCourseNum() {
		return this.courseNum;
	}
	
	/**
	 * @return The name of the course
	 */
	public String getCourseName() {
		return this.courseName;
	}
	
	/**
	 * @return The credit hours tat can be attempted
	 */
	public String getCreditHours() {
		return this.creditHours;
	}
	
	/**
	 * @return The 'others' level count
	 */
	public int getOthersCount() {
		return this.others;
	}
	
	/**
	 * @return The 'fails' level count
	 */
	public int getFailsCount() {
		return this.fails;
	}
	
	/**
	 * @return The 'marginals' level count
	 */
	public int getMarginalsCount() {
		return this.marginals;
	}
	
	/**
	 * @return The 'meets' level count
	 */
	public int getMeetsCount() {
		return this.meets;
	}
	
	/**
	 * @return The 'exceeds' level count
	 */
	public int getExceedsCount() {
		return this.exceeds;
	}
	
	/**
	 * @return levels - The count for each of the levels.
	 */
	public int[] getLevels() {
		int levels[] = {others,fails,marginals,meets,exceeds};
		return levels;
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