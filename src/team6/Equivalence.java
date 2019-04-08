package team6;
/**
 * @author Samuel Su
 */

import java.util.*;
import java.io.*;

public class Equivalence {
	
	private ArrayList<ArrayList<String>> eClass; 
	private ArrayList<ArrayList<String>> aClass;
	private ArrayList<String> aNames;
	
	public Equivalence() throws IOException {
		
		//aClass.get( vertical ).get( horizontal )
		aClass = new ArrayList<ArrayList<String>>();
		eClass = FileHandler.getEquivalentCourses("results_EE2014.xlsx");
		ArrayList<String> aNames = FileHandler.getAreaNames("results_EE2014.xlsx");
		for (int i = 0;i<aNames.size();i++) {
			aClass.add(FileHandler.getAreaCourses("results_EE2014.xlsx", aNames.get(i)));
		}
	}
	
	public String hasEquivalence(String course) {
		String result = course;
		// .get(rows).get(column)
		for (int i = 0; i < eClass.size(); i++) {
			for (int r = 0; r < eClass.get(0).size(); r++) {
				if (course.equals(eClass.get(i).get(r))) {
					result = eClass.get(0).get(r);
				}
			}
		}
		
		//Returns as normal if there isn't any related courses.
		return result;
	}
	
	public String getDisipline(String course) {
		String result = "NULL";
		
		// .get(rows).get(column)
		for (int i = 0; i < aNames.size(); i++) {
			for (int r = 0; r < aClass.get(i).size(); r++) {
				if (course == aClass.get(i).get(r)) {
					result = aNames.get(i);
				}
			}
		}
		
		//Returns NULL if there isn't a related discipline.
		return result;
		
	}
	
}