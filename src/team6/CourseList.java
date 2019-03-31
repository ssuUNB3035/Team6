package team6;
/**
 * @author: Ryan Nitz
 */

import java.util.ArrayList;

class CourseList {
	
	/**
	 * List to hold unique courses for Raw Distribution output
	 */
	public static ArrayList<Course> courseList = new ArrayList<Course>();

	/**
	 * Adds a course to the list if it is unique, increments a bin if it already exists.
	 * @param gradeElements The elements of the grade read from the transcript
	 * @return boolean If the course has been accounted for or not.
	 */
	public static boolean addCourse(ArrayList<String> gradeElements){
		boolean courseExists = false;
		int courseIndex = 0;
		
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseNum().compareTo(gradeElements.get(0)) == 0) {
				courseExists = true;
				courseIndex = i;
				break;
			}
		}
		
		if(courseExists) {
			courseList.get(courseIndex).setGrade(gradeElements.get(3));
		}else {
			Course course = new Course(gradeElements);
			courseList.add(course);
		}
		
		return true;
	}
	
	/**
	 * @return The Raw course List
	 */
	public static ArrayList<Course> getCourseList(){
		courseList.sort(null);
		return courseList;
	}
	
	/**
	 * Prints the Raw Course alphabetically list in a text format.
	 */
	//TODO: Change this to output to a .txt file.
	public static String printTextRawList() {
		courseList.sort(null);
		
		String template = "Raw course List: \n";
		template += "\t\t\tOther   Fails Marginal  Meets  Exceeds \n";
		for(Course course : CourseList.courseList) {
				template += course.toString();
		}
		return template;
		
	}
}
