import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {
	
	GradeBook book1;
	GradeBook book2;

	@BeforeEach
	void setUp() throws Exception {
		book1 = new GradeBook(5);
		book1.addScore(50);
		book1.addScore(70);
		book1.addScore(100);
		book2 = new GradeBook(5);
		book2.addScore(30);
		book2.addScore(60);
		book2.addScore(95);
	}

	@AfterEach
	void tearDown() throws Exception {
		book1 = null;
		book2 = null;
	}

	@Test
	void testGetScoresSize() {
		assertEquals(book1.getScoresSize(), 3);
		assertEquals(book2.getScoresSize(), 3);
	}

	@Test
	void testToString() {
		System.out.println(book1.toString());
		assertTrue(book1.toString().equals("50.0 70.0 100.0"));
		assertTrue(book2.toString().equals("30.0 60.0 95.0"));
	}

	@Test
	void testAddScore() {
		book1.addScore(200);
		book2.addScore(100);
		assertTrue(book1.toString().equals("50.0 70.0 100.0 200.0"));
		assertTrue(book2.toString().equals("30.0 60.0 95.0 100.0"));
		assertEquals(book1.getScoresSize(), 4);
		assertEquals(book2.getScoresSize(), 4);
	}

	@Test
	void testSum() {
		assertEquals(book1.sum(), 220);
		assertEquals(book2.sum(), 185);
	}

	@Test
	void testMinimum() {
		assertEquals(book1.minimum(), 50);
		assertEquals(book2.minimum(), 30);
	}

	@Test
	void testFinalScore() {
		assertEquals(book1.finalScore(), 170);
		assertEquals(book2.finalScore(), 155);
	}

}
