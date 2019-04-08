package team6;
/**
 * @author Ryan Nitz
 * @author Keith LeBlanc
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
	public static boolean addCourse(Grade grade){
		boolean courseExists = false;
		int courseIndex = 0;
		
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseNum().compareTo(grade.getCourseNumber()) == 0) {
				courseExists = true;
				courseIndex = i;
				break;
			}
		}
		
		if(courseExists) {
			courseList.get(courseIndex).setGrade(grade.getLetterGrade());
		}else {
			if (grade.getLetterGrade().equals("")) {
				//Do nothing :D
			}
			else {
				Course course = new Course(grade);
				courseList.add(course);
			}
		}
		
		return true;
	}
	
	/**
	 * @param courseNum The identifier of the course
	 * @return courseIndex The index in the master list of the course being searched
	 */
	public static int searchCourse(String courseNum) {

		boolean found = false;
		int courseIndex = -1;
		for(int i=0; i<courseList.size() && !found; i++) {
			//System.out.println(courseList.get(i).getCourseNum() + "/" + courseNum + " search");

			if (courseList.get(i).getCourseNum().equals(courseNum)) {
				found = true;
				courseIndex = i;
				System.out.println("course found");
			}
		}
		
		return courseIndex;
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
	//TODO: add to output to a .txt file in fileHandler.
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
