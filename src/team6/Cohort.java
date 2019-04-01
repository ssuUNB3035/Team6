package team6;

import java.util.ArrayList;

/**
 * 
 * @author Ryan Nitz
 *
 */

public class Cohort {
	private int firstYearCount;
	private int secondYearCount;
	private int thirdYearCount;
	private int fourthYearCount;
	
	private String cohortName;
	
	private ArrayList<Transcript> transcripts;
	
	public Cohort(String cohortName) {
		this.cohortName = cohortName;
		this.transcripts = new ArrayList<Transcript>();
	}
	
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
		}
		
		return false;
	}
	
	public String getCohortName() {
		return this.cohortName;
	}
	
	
	public int getFirstYearCount() {
		return firstYearCount;
	}
	
	public int getSecondYearCount() {
		return secondYearCount;
	}
	
	public int getThirdYearCount() {
		return thirdYearCount;
	}
	
	public int getFourthYearCount() {
		return fourthYearCount;
	}
	
	public int[] getYearDistribution() {
		int yearCounts[] = {firstYearCount, secondYearCount, thirdYearCount, fourthYearCount};
		return yearCounts;
	}
	
	public String toString() {
		String template = "Students in First Year: " + this.firstYearCount + "\n"
				+"Students in Second Year: " + this.secondYearCount + "\n"
				+"Students in Third Year: " + this.thirdYearCount + "\n"
				+"Students in Fourth Year: " + this.fourthYearCount + "\n";
		return template;
	}
}
