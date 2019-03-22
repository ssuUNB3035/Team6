package team6;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.*;

public class ExcelWriter {
	/*
	 * This part was for testing
	public static void main(String[] args) throws IOException {
		/*
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		HSSFCell cell2 = row.createCell(1);
		HSSFCell cell3 = row.createCell(2);
		cell.setCellValue("1, Cell");
		cell2.setCellValue("2, Cell");
		cell3.setCellValue("3, Cell");
		
		workbook.write(new FileOutputStream("pl.xsl"));
		workbook.close();
		
		
		ArrayList<Course> list = new ArrayList<>();
		list.add(new Course("CS2043", "Software Engineering"));
		list.add(new Course("CS2263", "Systems Software Devt"));
		list.add(new Course("CS2383", "Algorithms"));
		
		writeToExcel(list);
		
		
	}*/
	
	//Should this method take in a filename? That would mean that they create a new excel file.
	//I guess we could also create a new file.
	//file not found exception would help depending on what we decide. Also, we can add a customized message
	public static void writeToExcel(ArrayList<Course> sortedList) throws FileNotFoundException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Raw List");
		HSSFRow row = sheet.createRow(0);
		HSSFCell courseNum = row.createCell(0);
		HSSFCell courseName = row.createCell(1);
		courseNum.setCellValue("Course Number");
		courseName.setCellValue("Course Name");
		
		int n = 0, m = 0;
		//A row iterator could be a better design choice.
		//This part of the method will therefore be improved soon.
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
		System.out.println("Courses have been successfully copied to the Raw List sheet.");
	}
}
