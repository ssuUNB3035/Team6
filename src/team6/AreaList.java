package team6;

import java.util.ArrayList;
import java.io.IOException;

public class AreaList {	

	private static ArrayList<Area> areaList = new ArrayList<Area>();
	
	public static void makeAreaList() {		
		try {
			ArrayList<String> areaNames = FileHandler.getAreaNames("results_EE2014.xlsx");
			
			for(int i=0; i<areaNames.size(); i++) {
				areaList.add(new Area(areaNames.get(i)));
				System.out.println(areaList.get(i));
			}	
		}
		
		catch(IOException e) {
			System.out.println("IO Error - could not resolve input");
		}
	}		
}
