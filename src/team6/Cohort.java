package team6;

import java.util.ArrayList;

/**
 * @author Ryan Nitz
 */

public class Cohort {
	
	/**
	 * The amount of students that are in their first year based on credit hours
	 */
	private int firstYearCount;
	/**
	 * The amount of students that are in their second year based on credit hours
	 */
	private int secondYearCount;
	/**
	 * The amount of students that are in their fourth year based on credit hours
	 */
	private int thirdYearCount;
	/**
	 * The amount of students that are in their fifth year based on credit hours
	 */
	private int fourthYearCount;
	
	/**
	 * The amount of grades that are within the others grades margin
	 */
	private int globalOthers = 0;
	/**
	 * The amount of grades that are within the failing grades margin
	 */
	private int globalFails = 0;
	/**
	 * The amount of grades that are within the marginals grades margin
	 */
	private int globalMarginals = 0;
	/**
	 * The amount of grades that are within the meets grades margin
	 */
	private int globalMeets = 0;
	/**
	 * The amount of grades that are within the exceeds grades margin
	 */
	private int globalExceeds = 0;
	
	/**
	 * The number of courses that were taken in Saint John
	 */
	private int saintJohnCount;
	/**
	 * The number of courses that were taken in fredericton
	 */
	private int frederictonCount;
	/**
	 * The number of courses that were taken in other locations
	 */
	private int otherInstituteCount;

	/**
	 * The name of the cohort, or the folder that holds all of the transcripts
	 */
	private String cohortName;
	/**
	 * A list of transcript objects that exist in the folder/cohort
	 */
	private ArrayList<Transcript> transcripts;
	/**
	 * A list of all unique courses before being equated with equivalences
	 */
	private ArrayList<String> masterList;
	
	/**
	 * @param cohortName - The name of the cohort file.
	 */
	public Cohort(String cohortName) {
		this.cohortName = cohortName;
		this.transcripts = new ArrayList<Transcript>();
		this.masterList = new ArrayList<String>();
	}
	
	/**
	 * Adds a transcript to the cohort object and determines the year of the student.
	 * @param transcript - The transcript being added
	 * @return boolean - If the transcript wad added to the list successfully.
	 */
	public boolean addTranscript(Transcript transcript) {
		RankSchema.getRankSchema();
		double creditHours = 0.00;
		
		if(transcripts.add(transcript)) {
			
			creditHours = transcript.getAttemptedCreditHours();
			if(creditHours < RankSchema.getSecondYearMin()) {
				firstYearCount++;
			}else if(creditHours < RankSchema.getThirdYearMin()) {
				secondYearCount++;
			}else if(creditHours < RankSchema.getFourthYearMin()) {
				thirdYearCount++;
			}else if(creditHours >= RankSchema.getFourthYearMin()) {
				fourthYearCount++;
			}
			
			this.frederictonCount += transcript.getFrederictonCount();
			this.saintJohnCount += transcript.getSaintJohnCount();
			this.otherInstituteCount += transcript.getOtherLocationCount();

			return true;
		}
		return false;
	}
	
	/**
	 * Adds a course to the unique master list of courses
	 * @param grade The grade that holds course values
	 * @return boolean - If the course was successfully added
	 */
	public boolean addCourseToMaster(Grade grade) {
		String courseID = grade.getCourseNumber() + ", " + grade.getCourseName();
		if(!masterList.contains(courseID)) {
			return masterList.add(courseID);
		}
		return false;
	}
	
	/**
	 * Return the master list of all the courses
	 * @return masterlist - The ArrayList of Strings
	 */
	public ArrayList<String> getMasterList(){
		masterList.sort(null);
		return masterList;
	}
	
	/**
	 * @return cohortName - The name of the cohort
	 */
	public String getCohortName() {
		return this.cohortName;
	}
	
	/**
	 * @return firstYearCount - The number of transcripts that are in their first year.
	 */
	public int getFirstYearCount() {
		return firstYearCount;
	}
	
	/**
	 * @return secondYearCount - The number of transcripts that are in their second year.
	 */
	public int getSecondYearCount() {
		return secondYearCount;
	}
	
	/**
	 * @return thirdYearCount - The number of transcripts that are in their third year.
	 */
	public int getThirdYearCount() {
		return thirdYearCount;
	}
	
	/**
	 * @return	fourthYearCount - The number of transcripts that are in their fourth year.
	 */
	public int getFourthYearCount() {
		return fourthYearCount;
	}
	
	/**
	 * @return yearCounts[] - The distribution of the students in each year. 
	 */
	public int[] getYearDistribution() {
		int yearCounts[] = {firstYearCount, secondYearCount, thirdYearCount, fourthYearCount};
		return yearCounts;
	}
	
	/**
	 * Increases the others level in the cohort;
	 * @param othersIncrease
	 */
	public void increaseGlobalOthers(int othersIncrease) {
		this.globalOthers += othersIncrease;
	}
	
	/**
	 * Increases the fails level in the cohort;
	 * @param othersIncrease
	 */
	public void increaseGlobalFails(int failsIncrease) {
		this.globalFails += failsIncrease;
	}

	/**
	 * Increases the marginals level in the cohort;
	 * @param othersIncrease
	 */
	public void increaseGlobalMarginals(int marginalsIncrease) {
		this.globalMarginals += marginalsIncrease;
	}

	/**
	 * Increases the meets level in the cohort;
	 * @param othersIncrease
	 */
	public void increaseGlobalMeets(int meetsIncrease) {
		this.globalMeets += meetsIncrease;
	}

	/**
	 * Increases the exceeds level in the cohort;
	 * @param othersIncrease
	 */
	public void increaseGlobalExceeds(int exceedsIncrease) {
		this.globalExceeds += exceedsIncrease;
	}
	
	/**
	 * Increases all of the levels for the cohort, where applicable
	 * @param levels - The array of numbers to increase each level by.
	 */
	public void increaseGlobalDistribution(int levels[]) {
		this.globalOthers += levels[0];
		this.globalFails += levels[1];
		this.globalMarginals += levels[2];
		this.globalMeets += levels[3];
		this.globalExceeds += levels[4];
	}
	
	/**
	 * @return distribution - The level distribution of the cohort in an array
	 */
	public int[] getGlobalDistribution() {
		int distribution[] = {this.globalOthers, this.globalFails, this.globalMarginals, this.globalMeets, this.globalExceeds};
		return distribution;
	}
	
	/**
	 * Iterates through the courses in the cohort to update the cohort's distribution
	 */
	public void calculateCourseLevels(){
		ArrayList<Course> courses = CourseList.getCourseList();
		for(Course course : courses) {
			this.increaseGlobalDistribution(course.getLevels());
		}
	}
	
	/**
	 * Returns the count of courses that were taken in fredericton
	 * @return frederictonCount
	 */
	public int getFrederictonCount() {
		return this.frederictonCount;
	}
	
	/**
	 * Returns the count of courses that were taken in fredericton
	 * @return saintJohnCount
	 */
	public int getSaintJohnCount() {
		return this.saintJohnCount;
	}
	
	/**
	 * Returns the count of courses that were taken in fredericton
	 * @return otherInstituteCount
	 */
	public int getOtherLocationCount() {
		return this.otherInstituteCount;
	}
	
	/**
	 * @return The formated string for year counts.
	 */
	public String toString() {
		String template = "Students in First Year: " + this.firstYearCount + "\n"
				+"Students in Second Year: " + this.secondYearCount + "\n"
				+"Students in Third Year: " + this.thirdYearCount + "\n"
				+"Students in Fourth Year: " + this.fourthYearCount + "\n";
		return template;
	}
}
