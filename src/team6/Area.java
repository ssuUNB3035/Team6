package team6;
/**
 * @author Keith LeBlanc
 */

import java.util.ArrayList;
import java.io.*;

public class Area {
	private ArrayList<String> areaCourses;

	private String areaName;
	private int others, fails, marginals, meets, exceeds;

	/**
	 * @param areaName The name of the area
	 */
	public Area(String areaName) {

		this.areaName = areaName;
		try {
			areaCourses = FileHandler.getAreaCourses("results_EE2014.xlsx", areaName);
			for(int i=0; i<areaCourses.size(); i++) {				
				for(int j=0; j<CourseList.courseList.size(); j++) {
					if(areaCourses.contains(CourseList.courseList.get(j).getCourseNum())) {
						addCourse(CourseList.courseList.get(j));
					}
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
		this.exceeds += courseInArea.getExceedsCount();
		this.meets += courseInArea.getMeetsCount();
		this.marginals += courseInArea.getMarginalsCount();
		this.fails += courseInArea.getFailsCount();
		this.others += courseInArea.getOthersCount();
	}

	/**
	 * @return The amount of "others" grades
	 */
	public int getOthersCount() {
		return this.others;
	}
	
	/**
	 * @return The amount of "fails" grades
	 */
	public int getFailsCount() {
		return this.fails;
	}
	
	/**
	 * @return The amount of "marginals" grades
	 */
	public int getMarginalsCount() {
		return this.marginals;
	}
	
	/**
	 * @return The amount of "meets" grades
	 */
	public int getMeetsCount() {
		return this.meets;
	}
	
	/**
	 * @return The amount of "exceeds" grades
	 */
	public int getExceedsCount() {
		return this.exceeds;
	}
	
	/**
	 * @return areaAverage - The average GPA in the area
	 */
	public double getAverage() {
		double areaAverage = 0.0;
		for(int i=0; i<areaCourses.size(); i++) {
			
		}
		
		return areaAverage;
	}
	
	/**
	 * @return levels - The amount of each level
	 */
	public int[] getLevels() {
		int levels[] = {others,fails,marginals,meets,exceeds};
		return levels;
	}
	
	public String toString() {
		String template = areaName + " ";		
		//for(int i=0; i<areaCourses.size(); i++) {
		//	template += areaCourses.get(i) + " ";
		//}		
		template += this.others + ", " + this.fails + ", " + this.marginals + ", " + this.meets + ", " + this.exceeds;
		
		return template;
	}
}
