package team6;
/**
 * @author Uwera Ntaganzwa
 */
import java.io.*;
import java.util.Scanner;

public class RankSchema {
	private static final int RANKS = 3;
	private static int[] rankMargins = new int[RANKS];
	static int secondYearMin;
	static int thirdYearMin;
	static int fourthYearMin;

	/**
	 * Reads in the contents of the rankConfig file 
	 */
	public static void getRankSchema() {
		File rankConfig = new File("rankConfig.txt");
		Scanner sc = null;
		
		try {
			sc = new Scanner(rankConfig);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String ranks = sc.nextLine();
		defineRanks(ranks);
	}
	/**
	 * Sets the minimum requirements for second, third, and fourth year.
	 * Assumption: the minimum requirements of firstYear = 0.00 CH - not required in config file
	 * @param r - The contents of the file
	 */
	public static void defineRanks(String r) {
		String [] minValues = r.split("\\,");
		for(int i = 0; i < rankMargins.length; i++) {
			rankMargins[i] = Integer.parseInt(minValues[i]);
		}
	}
	
	/**
	 * @return secondYearMin - The minimum credit hours required for one to be in their second year
	 */
	public static int getSecondYearMin() {
		secondYearMin = rankMargins[0];
		return secondYearMin;
	}
	/**
	 * @return thirdYearMin - The minimum credit hours required for one to be in their third year
	 */
	public static int getThirdYearMin() {
		thirdYearMin = rankMargins[1];
		return thirdYearMin;
	}
	/**
	 * @return fourthYearMin - The minimum credit hours required for one to be in their fourth year or above
	 */
	public static int getFourthYearMin() {
		fourthYearMin = rankMargins[2];
		return fourthYearMin;
	}
}
