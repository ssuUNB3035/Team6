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
	
	public static void defineRanks(String r) {
		String [] minValues = r.split("\\,");
		for(int i = 0; i < rankMargins.length; i++) {
			rankMargins[i] = Integer.parseInt(minValues[i]);
		}
	}
	
	public static int getSecondYearMin() {
		secondYearMin = rankMargins[0];
		return secondYearMin;
	}
	
	public static int getThirdYearMin() {
		thirdYearMin = rankMargins[1];
		return thirdYearMin;
	}
	
	public static int getFourthYearMin() {
		fourthYearMin = rankMargins[2];
		return fourthYearMin;
	}
}
