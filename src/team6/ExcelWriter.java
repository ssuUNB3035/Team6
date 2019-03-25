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
			
			HSSFCell numCell = nextRow.createCell(m);
			HSSFCell nameCell = nextRow.createCell(m+1);
			HSSFCell othersCell = nextRow.createCell(m+2);
			HSSFCell failsCell = nextRow.createCell(m+3);
			HSSFCell marginalsCell = nextRow.createCell(m+4);
			HSSFCell meetsCell = nextRow.createCell(m+5);
			HSSFCell exceedsCell = nextRow.createCell(m+6);
			
			numCell.setCellValue(courseIn.getCourseNum());
			nameCell.setCellValue(courseIn.getCourseName());
			othersCell.setCellValue(courseIn.getOthersCount());
			failsCell.setCellValue(courseIn.getFailsCount());
			marginalsCell.setCellValue(courseIn.getMarginalsCount());
			meetsCell.setCellValue(courseIn.getMeetsCount());
			exceedsCell.setCellValue(courseIn.getExceedsCount());
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
		
		String columnHeaders[] = {"Course Number", "Course Name", "Others", "Fails", "Marginala","Meets", "Exceeds"};
		for(int c = 0; c < columnHeaders.length; c++) {
			HSSFCell cell = row.createCell(c);
			cell.setCellValue(columnHeaders[c]);
		}
		/*
		HSSFCell courseNum = row.createCell(0);
		HSSFCell courseName = row.createCell(1);
		courseNum.setCellValue("Course Number");
		courseName.setCellValue("Course Name");
		*/
		
		int n = 0, m = 0;
		//Is it possible to replace this with a row iterator even though the for-each loop works fine?
		for(Course courseIn: sortedList) {
			n++;
			HSSFRow nextRow = sheet.createRow(n);
			
			HSSFCell numCell = nextRow.createCell(m);
			HSSFCell nameCell = nextRow.createCell(m+1);
			HSSFCell othersCell = nextRow.createCell(m+2);
			HSSFCell failsCell = nextRow.createCell(m+3);
			HSSFCell marginalsCell = nextRow.createCell(m+4);
			HSSFCell meetsCell = nextRow.createCell(m+5);
			HSSFCell exceedsCell = nextRow.createCell(m+6);
			
			numCell.setCellValue(courseIn.getCourseNum());
			nameCell.setCellValue(courseIn.getCourseName());
			othersCell.setCellValue(courseIn.getOthersCount());
			failsCell.setCellValue(courseIn.getFailsCount());
			marginalsCell.setCellValue(courseIn.getMarginalsCount());
			meetsCell.setCellValue(courseIn.getMeetsCount());
			exceedsCell.setCellValue(courseIn.getExceedsCount());
		}
		
		workbook.write(new FileOutputStream(fileName));
		workbook.close();
		//System.out.println("Courses have been successfully copied to the Raw List sheet.");
	}
}
