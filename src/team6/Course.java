 package team6;
/**
 * @author: Ryan Nitz, Keith LeBlanc
 */

import java.util.ArrayList;

public class Course implements Comparable<Course>{
	
	private String courseNum;

	private String courseName;
 
	private int others;
	
	private int fails;
	
	private int marginals;
	
	private int meets;
	
	private int exceeds;

	//NOTE: yes keep the array of all the information so that we can do something with it if ever we need to. 
	public Course(Grade grade) {
		this.courseNum = grade.getCourseNumber();
		this.courseName = grade.getCourseName();
		setGrade(grade.getLetterGrade());
	}
	
	/**
	 * Increments the appropriate level depending on the grade provided
	 * @param grade - The letter grade achieved by a student taking this course
	 * @return boolean - If the grade was accounted for successfully or not.
	 */
	boolean setGrade(String grade) {
		grade = grade.replace("-", "").replace("+","");
		
		if(grade.length() > 1) {
			this.others++;
		}else if(grade.compareTo(LevelSchema.getMargin(0)) == 0 || grade.compareTo(LevelSchema.getMargin(1)) == 1) {
			//System.out.println("grade in: " + grade + " = fails");
			this.fails++;
		}else if(grade.compareTo(LevelSchema.getMargin(1)) == 0 || grade.compareTo(LevelSchema.getMargin(2)) == 1) {
			//System.out.println("grade in: " + grade + " = marginals");
			this.marginals++;
		}else if(grade.compareTo(LevelSchema.getMargin(2)) == 0 || grade.compareTo(LevelSchema.getMargin(3)) == 1) {
			//System.out.println("grade in: " + grade + " = meets");
			this.meets++;
		}else if(grade.compareTo(LevelSchema.getMargin(3)) == 0) {
			//System.out.println("grade in: " + grade + " = exceeds");
			this.exceeds++;
		}else if(grade.compareTo(LevelSchema.getMargin(0)) == -1 || grade.compareTo(LevelSchema.getMargin(3)) == 1) {
			//System.out.println("grade in: " + grade + " = others");
			this.others++;
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
		if(this.courseNum.compareTo(other.courseNum) < 0) {
			return -1;
		}else if(this.courseNum.compareTo(other.courseNum) > 0) {
			return 1;
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