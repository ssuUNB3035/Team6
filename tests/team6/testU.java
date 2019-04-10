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
		RankSchema.getRankSchema();
	}
	
	@Test
	void testGetFYearMin() {
		int x = RankSchema.getFourthYearMin();
		assertEquals(120, x);
	}
	
	@Test
	void testGetTYearMin() {
		int x = RankSchema.getThirdYearMin();
		assertEquals(80, x);
	}
}
