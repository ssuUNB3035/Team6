package team6;
/**
 * @author Keith LeBlanc
 */

import java.util.ArrayList;
import java.io.*;

public class Area {
	private ArrayList<String> areaCourses;

	private static final int GRADELIST = 5;
	private String areaName;
	private int[] gradeAmount = new int[GRADELIST];

	/**
	 * @param areaName The name of the area
	 */
	public Area(String areaName) {

		this.areaName = areaName;
		try {
			areaCourses = FileHandler.getAreaCourses("results_EE2014.xlsx", areaName);
			
			for(int j=0; j<CourseList.courseList.size(); j++) {
				if(areaCourses.contains(CourseList.courseList.get(j).getCourseNum())) {
					addCourse(CourseList.courseList.get(j));
				}
			}			
		}
		
		catch(FileNotFoundException e) {
			System.out.println("ERROR: Could not find file");
		}
		catch(IOException e) {
			System.out.println("ERROR: IO Failure");
		}
	}
	
	/**
	 * @return The name of the area
	 */
	public String getAreaName() {
		return this.areaName;
	}
	
	/**
	 * @param courseInArea The course with grades to be added to the area distribution
	 */	
	public void addCourse(Course courseInArea) {
		this.gradeAmount[4] += courseInArea.getExceedsCount();
		this.gradeAmount[3] += courseInArea.getMeetsCount();
		this.gradeAmount[2] += courseInArea.getMarginalsCount();
		this.gradeAmount[1] += courseInArea.getFailsCount();
		this.gradeAmount[0] += courseInArea.getOthersCount();
	}

	/**
	 * @param margin The grade type (exceeds, meets, marginals, fails, others) being searched for
	 * @return The amount of the indexed grades
	 */
	public int getGradesCount(int margin) {
		return this.gradeAmount[margin];
	}
	
	/**
	 * @return levels - The amount of each level
	 */ 
	public int[] getLevels() {
		return gradeAmount;
	}
	
	public String toString() {
		String template = areaName + " - ";		
		
		template += this.gradeAmount[0] + ", " + this.gradeAmount[1] + ", " + this.gradeAmount[2] + ", " + this.gradeAmount[3] + ", " + this.gradeAmount[4];
		
		return template;
	}
}
