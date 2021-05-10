package viergewinnt.logik.objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import viergewinnt.logik.interfaces.Spieler;
import viergewinnt.logik.interfaces.Spielfeld;


class SpielfeldImplTest {
	
	static final int REIHEN_ANZAHL = 5;
	static final int MAX_GRÖSSE    = 5;
	
	Spielfeld sf;
	Spieler   s1;
	Spieler   s2;
	
	@BeforeEach
	void setUp() throws Exception {
		sf = new SpielfeldImpl(REIHEN_ANZAHL, MAX_GRÖSSE);
		//@formatter:off
		s1 = new Spieler() { @Override public int zugMachen() { return 0; } };
		s2 = new Spieler() { @Override public int zugMachen() { return 0; } };
		//@formatter:on
	}
	
	@AfterEach
	void tearDown() throws Exception {
		sf = null;
		s1 = null;
		s2 = null;
	}
	
	@Test
	@Disabled
	void testSpielfeldImpl() {
		fail("Not yet implemented");
	}
	
	@Test
	@Disabled
	void testSpielfeldImplIntInt() {
		fail("Not yet implemented");
	}
	
	@Test
	void testSteinEinwerfenVolleReihe() {
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s2, 0);
		sf.steinEinwerfen(s2, 0);
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s1, 0);
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 0));// reihe sollte voll sein
	}
	
	@Test
	void testSteinEinwerfenReihe() {
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s1, 1);
		sf.steinEinwerfen(s1, 2);
		sf.steinEinwerfen(s1, 3);
		sf.steinEinwerfen(s1, 4);
		assertThrows(IndexOutOfBoundsException.class, () -> sf.steinEinwerfen(s1, -1));// keine negative reihe
		assertThrows(IndexOutOfBoundsException.class, () -> sf.steinEinwerfen(s1, REIHEN_ANZAHL));// reihen beginnen bei 0
	}
	
	@Test
	void testSteinEinwerfenGood() {
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s2, 0);
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s2, 0);
		sf.steinEinwerfen(s2, 0);
		
		sf.steinEinwerfen(s2, 1);
		sf.steinEinwerfen(s1, 1);
		sf.steinEinwerfen(s2, 1);
		sf.steinEinwerfen(s1, 1);
		sf.steinEinwerfen(s2, 1);
		
		sf.steinEinwerfen(s1, 2);
		sf.steinEinwerfen(s2, 2);
		sf.steinEinwerfen(s1, 2);
		sf.steinEinwerfen(s2, 2);
		sf.steinEinwerfen(s1, 2);
		
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s1, 3);
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s1, 3);
		
		sf.steinEinwerfen(s2, 4);
		sf.steinEinwerfen(s1, 4);
		sf.steinEinwerfen(s2, 4);
		sf.steinEinwerfen(s2, 4);
		sf.steinEinwerfen(s1, 4);
	}
	
}
