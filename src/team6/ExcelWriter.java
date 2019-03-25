package team6;
/**
 * @author Uwera Ntaganzwa
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.*;

public class ExcelWriter {
	private static ArrayList<String> createdFiles = new ArrayList<>();
	
	//Can this be replace with a file.exists method?		
	public static boolean findFile(String name){
		boolean success = false;
		for(String names : createdFiles) {
			if(names == "RawList.xsl") {
				//then the file exists and can be written to
				success = true;
			}
		}
		if(success == false) {
			String fName = "RawList.xsl";
			createdFiles.add(fName);
			success = true;
		}
		return success;
		
		
	}
	public static void writeToExcel(ArrayList<Course> sortedList) throws FileNotFoundException, IOException {
		findFile("RawList.xsl");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Raw List");
		HSSFRow row = sheet.createRow(0);
		HSSFCell courseNum = row.createCell(0);
		HSSFCell courseName = row.createCell(1);
		courseNum.setCellValue("Course Number");
		courseName.setCellValue("Course Name");
		
		int n = 0, m = 0;
		//Is it possible to replace this with a row iterator even though the for-each loop works fine?
		for(Course courseIn: sortedList) {
			n++;
			HSSFRow nextRow = sheet.createRow(n);
			HSSFCell cell = nextRow.createCell(m);
			HSSFCell nextCell = nextRow.createCell(m+1);
			cell.setCellValue(courseIn.getCourseNum());
			nextCell.setCellValue(courseIn.getCourseName());
		}
		
		workbook.write(new FileOutputStream("RawList.xsl"));
		workbook.close();
		//System.out.println("Courses have been successfully copied to the Raw List sheet.");
	}
	
	public static void writeToExcel(ArrayList<Course> sortedList, String fileName) throws FileNotFoundException, IOException {
		findFile(fileName);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Raw List");
		HSSFRow row = sheet.createRow(0);
		HSSFCell courseNum = row.createCell(0);
		HSSFCell courseName = row.createCell(1);
		courseNum.setCellValue("Course Number");
		courseName.setCellValue("Course Name");
		
		int n = 0, m = 0;
		//Is it possible to replace this with a row iterator even though the for-each loop works fine?
		for(Course courseIn: sortedList) {
			n++;
			HSSFRow nextRow = sheet.createRow(n);
			HSSFCell cell = nextRow.createCell(m);
			HSSFCell nextCell = nextRow.createCell(m+1);
			cell.setCellValue(courseIn.getCourseNum());
			nextCell.setCellValue(courseIn.getCourseName());
		}
		
		workbook.write(new FileOutputStream(fileName));
		workbook.close();
		//System.out.println("Courses have been successfully copied to the Raw List sheet.");
	}
}
