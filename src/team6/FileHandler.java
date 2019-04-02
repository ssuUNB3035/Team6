package team6;
/**
 * @author Uwera Ntaganzwa
 * @author Ryan Nitz
 */


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.*;

public class FileHandler {
	
	static HSSFWorkbook workbook = new HSSFWorkbook();
	
	/**
	 * 	
	 * @param sortedList The Raw List of courses to be written in Excel
	 * @throws FileNotFoundException 
	 * @throws IOException
	 */
	public static void writeRawList(ArrayList<Course> sortedList) throws FileNotFoundException, IOException {
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
		
		workbook.write(new FileOutputStream("Results.xsl"));
		workbook.close();
		//System.out.println("Courses have been successfully copied to the Raw List sheet.");
	}
	
	public static void writeRawList(ArrayList<Course> sortedList, String fileName) throws FileNotFoundException, IOException {
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
