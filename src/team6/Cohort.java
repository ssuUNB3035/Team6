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
	private String cohortName;
	private ArrayList<Transcript> transcripts;
	
	/**
	 * @param cohortName - The name of the cohort file.
	 */
	public Cohort(String cohortName) {
		this.cohortName = cohortName;
		this.transcripts = new ArrayList<Transcript>();
	}
	
	/**
	 * Adds a transcript to the cohort object and determines the year of the student.
	 * @param transcript - The transcript being added
	 * @return boolean - If the transcript wad added to the list successfully.
	 */
	public boolean addTranscript(Transcript transcript) {
		double creditHours = 0.00;
		if(transcripts.add(transcript)) {
			creditHours = transcript.getAttemptedCreditHours();
			if(creditHours < 40) {
				firstYearCount++;
			}else if(creditHours < 80) {
				secondYearCount++;
			}else if(creditHours < 120) {
				thirdYearCount++;
			}else if(creditHours >= 120){
				fourthYearCount++;
			}
			return true;
		}
		
		return false;
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
	 * @return	fourthYearCount - The number of transcripts that in their fourth year.
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
