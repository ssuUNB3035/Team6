/**
 * @author: Ryan Nitz
 */

import java.util.ArrayList;

//Question? do we keep track of the fails marginals etc here? 
//If we want to use an array of courses that store the marginal fails etc, then we need a new course class that doesn't store the INDIVIDUAL courses that every single student has taken.


class CourseList {
	
	public static ArrayList<Course> courseList = new ArrayList<Course>();
	public static ArrayList<String> courseNumbers = new ArrayList<String>();

	
	public static ArrayList<String> addCourse(ArrayList<String> gradeElements){
		Course course = null;
		//if course does not exist in the list.
		if(!courseNumbers.contains(gradeElements.get(0))) {	
			//TODO:should we check for co-ops?
			course = new Course(gradeElements);
			courseList.add(course);
			courseNumbers.add(gradeElements.get(0));
		}else {
			int existingCourseNumberPosition = courseNumbers.indexOf(gradeElements.get(0));
			course = courseList.get(existingCourseNumberPosition);
			course.setGrade(gradeElements.get(3));
		} 
		
		return gradeElements;
	}
	
	public static ArrayList<Course> getCourseList(){
		return courseList;
	}
	
	public static String printRawList() {
		courseList.sort(null);
		
		String template = "Raw course List: \n";
		for(Course course : CourseList.courseList) {
				template += course.toString();
		}
		return template;
		
	}
}
