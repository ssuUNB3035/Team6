package team6;
/**
 * @author Uwera Ntaganzwa
 * @author Ryan Nitz
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileHandler {
	
	static XSSFWorkbook workbook = new XSSFWorkbook();
	
	/**
	 * Writes a Raw List of courses to Excel and includes the raw distribution for each course in the Master List
	 * @param sortedList - The Raw List of courses to be written in Excel (This is also the Master List)
	 * @throws FileNotFoundException - Thrown when "Results.xslx" is not found
	 * @throws IOException
	 */
	public static void writeRawList(ArrayList<Course> sortedList) throws FileNotFoundException, IOException {
		XSSFSheet sheet = workbook.createSheet("Raw List");
		XSSFRow row = sheet.createRow(0);
		String columnHeaders[] = {"Course Number", "Course Name", "Others", "Fails", "Marginals","Meets", "Exceeds"};
		for(int c = 0; c < columnHeaders.length; c++) {
			XSSFCell cell = row.createCell(c);
			cell.setCellValue(columnHeaders[c]);
		}
		
		int n = 0, m = 0;
		for(Course courseIn: sortedList) {
			n++;
			XSSFRow nextRow = sheet.createRow(n);
			
			XSSFCell numCell = nextRow.createCell(m);
			XSSFCell nameCell = nextRow.createCell(m+1);
			numCell.setCellValue(courseIn.getCourseNum());
			nameCell.setCellValue(courseIn.getCourseName());
			
			int[] levels = courseIn.getLevels();
			int c = 0;
			for(c = 0; c < levels.length; c++) {
				XSSFCell cell = nextRow.createCell(c+2);
				cell.setCellValue(levels[c]);
			}
			
			XSSFCell globalCell = nextRow.createCell(c+1);
		}
		workbook.write(new FileOutputStream("Results.xslx"));
		workbook.close();
		//System.out.println("Courses have been successfully copied to the Raw List sheet.");
	}
	/**
	 * 
	 * @param sortedList - The Raw List of courses to be written in Excel. (This is also the Master List)
	 * @param fileName - A specific File to which the results should be written
	 * @throws FileNotFoundException - Thrown when the specified file is not valid
	 * @throws IOException
	 */
	
	public static void writeRawList(ArrayList<Course> sortedList, String fileName) throws FileNotFoundException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Raw List");
		
		XSSFRow row = sheet.createRow(0);
		

		String columnHeaders[] = {"Course Number", "Course Name", "Others", "Fails", "Marginal","Meets", "Exceeds"};

		for(int c = 0; c < columnHeaders.length; c++) {
			XSSFCell cell = row.createCell(c);
			cell.setCellValue(columnHeaders[c]);
		}
		
		int n = 0, m = 0;
		for(Course courseIn: sortedList) {
			n++;
			XSSFRow nextRow = sheet.createRow(n);
			
			XSSFCell numCell = nextRow.createCell(m);
			XSSFCell nameCell = nextRow.createCell(m+1);
			numCell.setCellValue(courseIn.getCourseNum());
			nameCell.setCellValue(courseIn.getCourseName());
			
			int[] levels = courseIn.getLevels();
			for(int c = 0; c < levels.length; c++) {
				XSSFCell cell = nextRow.createCell(c+2);
				cell.setCellValue(levels[c]);
			}
		}
		
		workbook.write(new FileOutputStream(fileName));
		workbook.close();
	}
	
	
	//File name is not used until excel config is being read. 
	//TODO: Check if areaConfig should be the string name of the actual file
	//This method is to get the area group once you know the area names.
	/**
	 * This will return a list of all courses that exist in a specified area.
	 * @param areaConfig - The excel file that will be opened to extract data from.
	 * @param area - The area header to establish the area courses to be extracted.
	 * @return areaCourses - The list of courses that were in the specified area.
	 * @throws IOException
	 * @throws FileNotFoundException - When the specified file name does not exist.
	 */
	public static ArrayList<String> getAreaCourses(String areaConfig, String area) throws IOException, FileNotFoundException {
		
		InputStream ExcelFileToRead = new FileInputStream("results_EE2014.xlsx");
        XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFSheet sheet = wb.getSheet("Areas");
        
        ArrayList<String> areaCourses = new ArrayList<String>();
        Iterator<Row> rowIterator = sheet.iterator();
        Row row = rowIterator.next();
    	Iterator <Cell> cellIterator = row.cellIterator();
    	
    	int areaColumnIndex = 0;
    	
    	while(cellIterator.hasNext()) {
    		Cell cell = cellIterator.next();
    		if(area.equals(cell.getStringCellValue())) {
    			areaColumnIndex = cell.getColumnIndex();
    		}
    	}
        
    	while(rowIterator.hasNext()) {
    		row = rowIterator.next();
    		if(row.getCell(areaColumnIndex) != null) {
    			String course = row.getCell(areaColumnIndex).getStringCellValue();
    			areaCourses.add(course);
    		}else {
    			break;
    		}
    	}
    	
    	System.out.println(areaCourses.toString());
    	return areaCourses;
	}
	
	//TODO: Check if areaConfig should be the string name of the actual file
	/**
	 * This method will get all of the areas that are defined within the excel sheet - 'Areas'
	 * @param areaConfig - The excel file that will be opened to extract data from.
	 * @return areaNames - The names of all the areas that can be accessed. 
	 * @throws IOException
	 */
	public static ArrayList<String> getAreaNames(String areaConfig) throws IOException{
		
		InputStream ExcelFileToRead = new FileInputStream("results_EE2014.xlsx");
        XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFSheet sheet = wb.getSheet("Areas");
        
        ArrayList<String> areaNames = new ArrayList<String>();
        Row row = sheet.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
    	
    	
    	while(cellIterator.hasNext()) {
    		Cell cell = cellIterator.next();
    		areaNames.add(cell.getStringCellValue());
    	}
		
    	System.out.println(areaNames.toString());
		return areaNames;
	}
	
	//TODO: add a method to print to excel
	//TODO: handle printing to file.
	/**
	 * Prints the global distribution to the console.
	 * @param cohort
	 */
	public static void printGlobalDistribution(Cohort cohort) {
		int[] global = cohort.getGlobalDistribution();
		String globalString = "Global: \t\t";
		for(int i = 0; i < global.length; i++) {
			globalString += global[i] + ",\t";
		}
		System.out.println(globalString);
	}
	
}
