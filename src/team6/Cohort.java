package team6;

import java.util.ArrayList;

/**
 * @author Ryan Nitz
 */

public class Cohort {
	
	private int firstYearCount;
	private int secondYearCount;
	private int thirdYearCount;
	private int fourthYearCount;
	
	private int globalOthers = 0;
	private int globalFails = 0;
	private int globalMarginals = 0;
	private int globalMeets = 0;
	private int globalExceeds = 0;
	
	private int saintJohnCount;
	private int frederictonCount;
	private int otherInstituteCount;
	
	private String cohortName;
	private ArrayList<Transcript> transcripts;
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
	
	public boolean addCourseToMaster(Grade grade) {
		String courseID = grade.getCourseNumber() + ", " + grade.getCourseName();
		if(!masterList.contains(courseID)) {
			return masterList.add(courseID);
		}
		return false;
	}
	
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
	
	public int getFrederictonCount() {
		return this.frederictonCount;
	}
	
	public int getSaintJohnCount() {
		return this.saintJohnCount;
	}
	
	public int getOtherLocationCount() {
		return this.otherInstituteCount;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String toString() {
		String template = "Students in First Year: " + this.firstYearCount + "\n"
				+"Students in Second Year: " + this.secondYearCount + "\n"
				+"Students in Third Year: " + this.thirdYearCount + "\n"
				+"Students in Fourth Year: " + this.fourthYearCount + "\n";
		return template;
	}
}
