package team6;
/**
 * @author Samuel Su
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

public class testS {

Grade g;
	
	@BeforeEach
	void setUp() throws Exception {
		ArrayList<String> courseElements = new ArrayList<String>();
		courseElements.add("CS 1023");
		courseElements.add("SJ01B");
		courseElements.add("DATA STRUCTURES & ALGORITHMS");
		courseElements.add("A- ");
		courseElements.add("4.00");
		courseElements.add("2011/WI ");

		g = new Grade(courseElements);
	}
	
	@Test
	public void testGetCourseNumber() {
		assertEquals("CS 1023", g.getCourseNumber());
	}
	
	@Test
	public void testGetCreditHours() {
		assertEquals("4.00", g.getCreditHours());
	}

}