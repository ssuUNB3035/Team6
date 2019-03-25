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
	
	public static void writeToExcel(ArrayList<Course> sortedList) throws FileNotFoundException, IOException {
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
}



