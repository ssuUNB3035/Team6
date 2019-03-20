/**
 * @author: Ryan Nitz
 */

import java.util.ArrayList;

//Question? do we keep track of the fails marginals etc here? 
//If we want to use an array of courses that store the marginal fails etc, then we need a new course class that doesn't store the INDIVIDUAL courses that every single student has taken.


class CourseList {
	
	private static ArrayList<String> courseList = new ArrayList<String>();
	
	static boolean addCourse(String courseNum, String letterGrade){
		
		//if course does not exist in the list.
		if(!courseList.contains(courseNum)) {	
			//TODO:should we check for co-ops?
			courseList.add(courseNum);
		}else {
			//increment one of the marginal, fails, exceeds, etc
		} 
		
		return true;
	}
	
	public static String printRawList() {
		courseList.sort(null);
		
		String template = "Raw course list: \n";
		for(String course : CourseList.courseList) {
			template += course + "\n";
		}
		return template;
	}
}
