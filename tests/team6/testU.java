package team6;
/**
 * @author Uwera Ntaganzwa
 */

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testU {
	
	@BeforeEach
	void setUp() throws Exception {
		//
	}
	
	@Test
	void testGetFYearMin() {
		int x = RankSchema.getFourthYearMin();
		assertEquals(0, x);
	}
	
	@Test
	void testGetTYearMin() {
		int x = RankSchema.getThirdYearMin();
		assertEquals(0, x);
	}
}
