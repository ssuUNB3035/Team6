package team6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

class TestTeam6 {
	
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
	void testCourseName() {		
		Course c = new Course(g);		
		
		assertEquals(c.getCourseName(), "DATA STRUCTURES & ALGORITHMS");
	}
	
}
