package team6;
/**
 * @author Uwera Ntaganzwa
 * @author Ryan Nitz
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.*;

public class FileHandler {
	private static ArrayList<String> createdFiles = new ArrayList<>();
	
	//Can this be replace with a file.exists method?		
	public static boolean findFile(String name){
		boolean success = false;
		for(String names : createdFiles) {
			if(names == "Results.xslx") {
				//then the file exists and can be written to
				success = true;
			}
		}
		if(success == false) {
			String fName = "Results.xslx";
			createdFiles.add(fName);
			success = true;
		}
		return success;
	}
	public static void addConfigFile(File file) throws IOException {
		File configFile = file;
		BufferedReader reader = new BufferedReader(new FileReader(file)); 
		BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
		String str; 
		while ((str = reader.readLine()) != null) { 
		    writer.write(str);
		}
		reader.close();
		writer.close();
	}
		
	public static void writeRawList(ArrayList<Course> sortedList) throws FileNotFoundException, IOException {
		findFile("Results.xslx");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Raw List");
		HSSFRow row = sheet.createRow(0);
		
		String columnHeaders[] = {"Course Number", "Course Name", "Others", "Fails", "Marginals","Meets", "Exceeds"};
		for(int c = 0; c < columnHeaders.length; c++) {
			HSSFCell cell = row.createCell(c);
			cell.setCellValue(columnHeaders[c]);
		}
		
		int n = 0, m = 0;
		for(Course courseIn: sortedList) {
			n++;
			HSSFRow nextRow = sheet.createRow(n);
			
			HSSFCell numCell = nextRow.createCell(m);
			HSSFCell nameCell = nextRow.createCell(m+1);
			numCell.setCellValue(courseIn.getCourseNum());
			nameCell.setCellValue(courseIn.getCourseName());
			
			int[] levels = courseIn.getLevels();
			for(int c = 0; c < levels.length; c++) {
				HSSFCell cell = nextRow.createCell(c+2);
				cell.setCellValue(levels[c]);
			}
		}
		
		workbook.write(new FileOutputStream("Results.xslx"));
		workbook.close();
		//System.out.println("Courses have been successfully copied to the Raw List sheet.");
	}
	
	public static void writeRawList(ArrayList<Course> sortedList, String fileName) throws FileNotFoundException, IOException {
		findFile(fileName);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Raw List");
		
		HSSFRow row = sheet.createRow(0);
		

		String columnHeaders[] = {"Course Number", "Course Name", "Others", "Fails", "Marginal","Meets", "Exceeds"};

		for(int c = 0; c < columnHeaders.length; c++) {
			HSSFCell cell = row.createCell(c);
			cell.setCellValue(columnHeaders[c]);
		}
		
		int n = 0, m = 0;
		for(Course courseIn: sortedList) {
			n++;
			HSSFRow nextRow = sheet.createRow(n);
			
			HSSFCell numCell = nextRow.createCell(m);
			HSSFCell nameCell = nextRow.createCell(m+1);
			numCell.setCellValue(courseIn.getCourseNum());
			nameCell.setCellValue(courseIn.getCourseName());
			
			int[] levels = courseIn.getLevels();
			for(int c = 0; c < levels.length; c++) {
				HSSFCell cell = nextRow.createCell(c+2);
				cell.setCellValue(levels[c]);
			}
		}
		
		workbook.write(new FileOutputStream(fileName));
		workbook.close();
	}
}
