package team6;
/**
 * @author Uwera Ntaganzwa
 * @author Ryan Nitz
 */

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
	 * Writes the Raw List to a specified Excel workbook. 
	 * @param sortedList - The Raw List of courses to be written in Excel. (This is also the Master List)
	 * @param fileName - A specific File to which the results should be written
	 * @throws FileNotFoundException - Thrown when the specified file is not valid
	 * @throws IOException
	 */
	public static void writeRawList(ArrayList<Course> sortedList, String fileName) throws FileNotFoundException, IOException {
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.createSheet("Raw List");
		
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
		
		wb.write(new FileOutputStream(fileName));
	}
	
	public static void writeMasterList(Cohort cohort) {
		XSSFSheet sheet = workbook.createSheet("MasterList");
		XSSFRow row;
		XSSFCell cell;
		
		ArrayList<String> master = cohort.getMasterList();
		
		for(int i = 0; i < master.size(); i++) {
			row = sheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(master.get(i));
		}
		
	}
	
	public static void writeGlobalDistribution(Cohort cohort, String fileName) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.createSheet("Global Distributions");
		wb.write(new FileOutputStream(fileName));
		int rowIndex = 0;
		rowIndex = writeGlobalDistribution(cohort, rowIndex, fileName);
		rowIndex = writeYearDistribution(cohort, rowIndex, fileName);
		rowIndex = writeLocationDistribution(cohort, rowIndex, fileName);
	}
	
	/**
	 * Creates a new sheet for cohort data to be displayed and fills global distribution data
	 * @param cohort - A set of transcripts
	 * @throws IOException 
	 */
	public static int writeGlobalDistribution(Cohort cohort, int index, String fileName) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		int rowIndex = index;
		XSSFSheet sheet = wb.getSheet("Global Distributions");
		XSSFRow row = sheet.createRow(rowIndex);
		XSSFCell cell = row.createCell(0);
		
		cell.setCellValue("Global Distributions");//data header
		
		String[] headers = {"Others", "Fails", "Marginals", "Meets", "Exceeds"};
		int[] globalDistribution = cohort.getGlobalDistribution();
		
		row = sheet.createRow(rowIndex+1);//levels titles
		for(int c = 0; c < headers.length; c++) {
			cell = row.createCell(c);
			cell.setCellValue(headers[c]);
		}
		
		row = sheet.createRow(rowIndex+2);//level counts
		for(int i = 0; i < globalDistribution.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(globalDistribution[i]);
		}
		wb.write(new FileOutputStream(fileName));
		return rowIndex + 4;
	}
	
/**
	 * Writes the number of students in the year that they are currently in (or completed). 
	 * @param cohort - A set of transcripts
 * @throws IOException 
	 */
	public static int writeYearDistribution(Cohort cohort, int index, String fileName) throws IOException {
		int rowIndex = index;
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheet("Global Distributions");
		XSSFRow row = sheet.createRow(rowIndex);
		XSSFCell cell = row.createCell(0);
		
		cell.setCellValue("Year Distribution");
		
		String[] headers = {"First", "Second", "Third", "Fourth"};
		int[] yearDistribution = cohort.getYearDistribution();
		
		row = sheet.createRow(rowIndex+1);
		for(int c = 0; c < headers.length; c++) {
			cell = row.createCell(c);
			cell.setCellValue(headers[c]);
		}
		
		row = sheet.createRow(rowIndex+2);
		for(int i = 0; i < yearDistribution.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(yearDistribution[i]);
		}
		wb.write(new FileOutputStream(fileName));
		return rowIndex + 4;
	}
	
	public static int writeLocationDistribution(Cohort cohort, int index, String fileName) throws IOException {
		int rowIndex = index;
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheet("Global Distributions");
		XSSFRow row = sheet.createRow(rowIndex);
		XSSFCell cell = row.createCell(0);
		
		cell.setCellValue("Course Location Distribution");
		
		String[] headers = {"Fredericton", "Saint John", "Other"};
		int[] locationDistribution = {cohort.getFrederictonCount(), cohort.getSaintJohnCount(), cohort.getOtherLocationCount()};
		
		row = sheet.createRow(rowIndex+1);
		for(int c = 0; c < headers.length; c++) {
			cell = row.createCell(c);
			cell.setCellValue(headers[c]);
		}
		
		row = sheet.createRow(rowIndex+2);
		for(int i = 0; i < locationDistribution.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(locationDistribution[i]);
		}
		wb.write(new FileOutputStream(fileName));
		return rowIndex + 4;
	}
	/**
	 * Creates an Area Distribution sheet and writes the area distribution to it
	 * @param sortedList - A sorted list of all the areas
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeAreaDistribution(ArrayList<Course> sortedList) throws FileNotFoundException, IOException {
		XSSFSheet sheet = workbook.createSheet("Area Distribution");
		XSSFRow row = sheet.createRow(0);
		String columnHeaders[] = {"Area", "Others", "Fails", "Marginals","Meets", "Exceeds"};
		for(int c = 0; c < columnHeaders.length; c++) {
			XSSFCell cell = row.createCell(c);
			cell.setCellValue(columnHeaders[c]);
		}
		
		int n = 0, m = 0;
		/*
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
			
		}*/
		workbook.write(new FileOutputStream("Results.xslx"));
	}
	
	/**
	 * This will return a list of all courses that exist in a specified area.
	 * @param areaConfig - The excel file that will be opened to extract data from.
	 * @param area - The area header to establish the area courses to be extracted.
	 * @return areaCourses - The list of courses that were in the specified area.
	 * @throws IOException
	 * @throws FileNotFoundException - When the specified file name does not exist.
	 */
	 public static ArrayList<String> getAreaCourses(String areaConfig, String area) throws IOException, FileNotFoundException {

	    InputStream ExcelFileToRead = new FileInputStream(areaConfig);
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
		
	/**
	 * This method will get all of the areas that are defined within the excel sheet - 'Areas'
	 * @param areaConfig - The excel file that will be opened to extract data from.
	 * @return areaNames - The names of all the areas that can be accessed. 
	 * @throws IOException
	 */
	 public static ArrayList<String> getAreaNames(String areaConfig) throws IOException{
			
		InputStream ExcelFileToRead = new FileInputStream(areaConfig);
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
			
	public static ArrayList<ArrayList<String>> getEquivalentCourses(String areaConfig) throws IOException, FileNotFoundException {
		
		InputStream ExcelFileToRead = new FileInputStream(areaConfig);
        XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFSheet sheet = wb.getSheet("Equivelents");
        
        ArrayList<ArrayList<String>> courses = new ArrayList<ArrayList<String>>();
        
        int rowStart = Math.min(15, sheet.getFirstRowNum());
        int rowEnd = Math.max(1400, sheet.getLastRowNum());

        for (int rowNum = rowStart; rowNum < rowEnd-2; rowNum++) {
           Row r = sheet.getRow(rowNum);
           
           if (r == null) {
               break;
           }
           
           int lastColumn = Math.max(r.getLastCellNum(), 0);
           ArrayList<String> temp = new ArrayList<String>();
           
           for (int cn = 0; cn < lastColumn; cn++) {

        	   Cell c = r.getCell(cn);
               if (c == null) {
                  temp.add("NULL");
               } else {
            	  temp.add(r.getCell(cn).getStringCellValue());
               }
           }

           courses.add(temp);
        }
        
    	return courses;
	}
	//TODO: Handle printing to a txt file
	/**
	 * Prints the global distribution to the console.
	 * @param cohort - A set of transcripts
	 */
	 public static void printGlobalDistribution(Cohort cohort) {
		 int[] global = cohort.getGlobalDistribution();
		 String globalString = "Global: \t\t";
		 for(int i = 0; i < global.length; i++) {
			globalString += global[i] + "\t";
		 }
		 System.out.println(globalString);
	}
			
	//TODO: Handle printing to a txt file
	/**
	 * Prints the year distribution to the console
	 * @param cohort - A set of transcripts
	 */
	public static void printYearDistribution(Cohort cohort) {
		int[] yearDist = cohort.getYearDistribution();
		String yearString = "Students in year: 1, 2, 3, 4";
		for(int i = 0; i < yearDist.length; i++) {
			yearString += yearDist[i] + ", ";
		}
	}
			
	/**
	 * Returns a list of all the stored files; config files and output excel files
	 * @return files - A list of the files stored in the current working directory
	 * @throws IOException
	 */
	public static String retrieveStoredFiles(){
		String directoryPath;
		String files = "";
		ArrayList<File> retrievedFiles = new ArrayList<File>();
		try {
			directoryPath = new java.io.File( "." ).getCanonicalPath();
			File directory = new File(directoryPath);
			File [] storedFiles = directory.listFiles();
			for(File f : storedFiles) {
				if ((f.getName().endsWith(".xslx") || (f.getName().endsWith(".txt")))){
					retrievedFiles.add(f);
					System.out.println(f.getName());
				}
			}
			for(File f: retrievedFiles) {
				files += "*" + f.getName() + " \n";
			}

			if(storedFiles.length == 0) {
				System.out.print("There are no stored files.");
			}
		} catch (IOException e) {
			System.out.println("Directory not found.");
			e.printStackTrace();
		}
		return files;
	}
	
}
