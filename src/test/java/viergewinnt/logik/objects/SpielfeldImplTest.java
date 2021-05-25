package viergewinnt.logik.objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import viergewinnt.logik.interfaces.Spieler;
import viergewinnt.logik.interfaces.Spielfeld;


class SpielfeldImplTest {
	
	static final int REIHEN_ANZAHL = 5;
	static final int MAX_GRÖSSE = 5;
	
	Spielfeld sf;
	Spieler s1;
	Spieler s2;
	
	@BeforeEach
	void setUp() throws Exception {
		sf = new SpielfeldImpl(REIHEN_ANZAHL, MAX_GRÖSSE);
		//@formatter:off
		s1 = new Spieler() { @Override public int zugMachen() { return -1; } };
		s2 = new Spieler() { @Override public int zugMachen() { return -1; } };
		//@formatter:on
	}
	
	@AfterEach
	void tearDown() throws Exception {
		sf = null;
		s1 = null;
		s2 = null;
	}
	
	@Test
	void testErgebnis() {
		sf.steinEinwerfen(newSpieler(), 0);
		sf.steinEinwerfen(newSpieler(), 0);
		sf.steinEinwerfen(newSpieler(), 0);
		sf.steinEinwerfen(newSpieler(), 0);
		sf.steinEinwerfen(newSpieler(), 0);
		
		sf.steinEinwerfen(newSpieler(), 1);
		sf.steinEinwerfen(newSpieler(), 1);
		sf.steinEinwerfen(newSpieler(), 1);
		sf.steinEinwerfen(newSpieler(), 1);
		sf.steinEinwerfen(newSpieler(), 1);
		
		sf.steinEinwerfen(newSpieler(), 2);
		sf.steinEinwerfen(newSpieler(), 2);
		sf.steinEinwerfen(newSpieler(), 2);
		sf.steinEinwerfen(newSpieler(), 2);
		sf.steinEinwerfen(newSpieler(), 2);
		
		sf.steinEinwerfen(newSpieler(), 3);
		sf.steinEinwerfen(newSpieler(), 3);
		sf.steinEinwerfen(newSpieler(), 3);
		sf.steinEinwerfen(newSpieler(), 3);
		sf.steinEinwerfen(newSpieler(), 3);
		
		sf.steinEinwerfen(newSpieler(), 4);
		sf.steinEinwerfen(newSpieler(), 4);
		sf.steinEinwerfen(newSpieler(), 4);
		sf.steinEinwerfen(newSpieler(), 4);
		sf.steinEinwerfen(newSpieler(), 4);
		
		assertNull(sf.ergebnis());
		
		sf = new SpielfeldImpl();
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s1, 0);
		assertEquals(s1, sf.ergebnis());
		
		sf = new SpielfeldImpl();
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s1, 1);
		sf.steinEinwerfen(s1, 2);
		sf.steinEinwerfen(s1, 3);
		assertEquals(s1, sf.ergebnis());
		
		sf = new SpielfeldImpl();
		sf.steinEinwerfen(s1, 0);
		
		sf.steinEinwerfen(s2, 1);
		sf.steinEinwerfen(s1, 1);
		
		sf.steinEinwerfen(s2, 2);
		sf.steinEinwerfen(s2, 2);
		sf.steinEinwerfen(s1, 2);
		
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s1, 3);
		assertEquals(s1, sf.ergebnis());
		
		sf = new SpielfeldImpl();
		sf.steinEinwerfen(s2, 3);
		
		sf.steinEinwerfen(s2, 2);
		sf.steinEinwerfen(s2, 2);
		
		sf.steinEinwerfen(s1, 1);
		sf.steinEinwerfen(s1, 1);
		sf.steinEinwerfen(s2, 1);
		
		sf.steinEinwerfen(s2, 0);
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s2, 0);
		assertEquals(s2, sf.ergebnis());
		
		sf = new SpielfeldImpl();
		sf.steinEinwerfen(s2, 4);
		sf.steinEinwerfen(s1, 4);
		
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s1, 3);
		
		sf.steinEinwerfen(s1, 2);
		sf.steinEinwerfen(s2, 2);
		sf.steinEinwerfen(s2, 2);
		sf.steinEinwerfen(s1, 2);
		
		sf.steinEinwerfen(s2, 1);
		sf.steinEinwerfen(s1, 1);
		sf.steinEinwerfen(s2, 1);
		sf.steinEinwerfen(s2, 1);
		sf.steinEinwerfen(s1, 1);
		assertEquals(s2, sf.ergebnis());
	}
	
	//@formatter:off
	private Spieler newSpieler() {
		return new Spieler() { @Override public int zugMachen() { return 0; } };
	}
	//@formatter:on
	
	@Test
	void testFertigGespielt() {
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 0);
		assertFalse(sf.fertigGespiel());
		
		sf.steinEinwerfen(newSpieler(), 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 1);
		assertFalse(sf.fertigGespiel());
		
		sf.steinEinwerfen(newSpieler(), 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 2);
		assertFalse(sf.fertigGespiel());
		
		sf.steinEinwerfen(newSpieler(), 3);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 3);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 3);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 3);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 3);
		assertFalse(sf.fertigGespiel());
		
		sf.steinEinwerfen(newSpieler(), 4);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 4);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 4);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 4);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(newSpieler(), 4);
		
		assertTrue(sf.fertigGespiel());
		
		sf = new SpielfeldImpl();
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 0);
		assertTrue(sf.fertigGespiel());
		
		sf = new SpielfeldImpl();
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 3);
		assertTrue(sf.fertigGespiel());
		
		sf = new SpielfeldImpl();
		sf.steinEinwerfen(s1, 0);
		assertFalse(sf.fertigGespiel());
		
		sf.steinEinwerfen(s2, 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 1);
		
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 2);
		
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 3);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 3);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 3);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 3);
		assertTrue(sf.fertigGespiel());
		
		sf = new SpielfeldImpl();
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 3);
		
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 2);
		
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 1);
		
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 0);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 0);
		assertTrue(sf.fertigGespiel());
		
		sf = new SpielfeldImpl();
		sf.steinEinwerfen(s2, 4);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 4);
		assertFalse(sf.fertigGespiel());
		
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 3);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 3);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 3);
		assertFalse(sf.fertigGespiel());
		
		sf.steinEinwerfen(s1, 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 2);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 2);
		assertFalse(sf.fertigGespiel());
		
		sf.steinEinwerfen(s2, 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s1, 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 1);
		assertFalse(sf.fertigGespiel());
		sf.steinEinwerfen(s2, 1);
		assertTrue(sf.fertigGespiel());
	}
	
	@Test
	void testSteinEinwerfenVolleReihe() {
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s2, 0);
		sf.steinEinwerfen(s2, 0);
		sf.steinEinwerfen(s1, 0);
		sf.steinEinwerfen(s1, 0);
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 0));// reihe sollte voll sein
		sf.steinEinwerfen(s1, 1);
		sf.steinEinwerfen(s2, 1);
		sf.steinEinwerfen(s2, 1);
		sf.steinEinwerfen(s1, 1);
		sf.steinEinwerfen(s1, 1);
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 0));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 1));// reihe sollte voll sein
		sf.steinEinwerfen(s1, 2);
		sf.steinEinwerfen(s2, 2);
		sf.steinEinwerfen(s2, 2);
		sf.steinEinwerfen(s1, 2);
		sf.steinEinwerfen(s1, 2);
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 0));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 1));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 2));// reihe sollte voll sein
		sf.steinEinwerfen(s1, 3);
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s2, 3);
		sf.steinEinwerfen(s1, 3);
		sf.steinEinwerfen(s1, 3);
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 0));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 1));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 2));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 3));// reihe sollte voll sein
		sf.steinEinwerfen(s1, 4);
		sf.steinEinwerfen(s2, 4);
		sf.steinEinwerfen(s2, 4);
		sf.steinEinwerfen(s1, 4);
		sf.steinEinwerfen(s1, 4);
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 0));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 1));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 2));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 3));// reihe sollte voll sein
		assertThrows(RuntimeException.class, () -> sf.steinEinwerfen(s2, 4));// reihe sollte voll sein
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
	
	@Test
	void testSteinEinwerfenDiffrentSizes() {
		for (int reihenAnz = 0; reihenAnz < 25; reihenAnz ++ ) {
			for (int maxGrr = 0; maxGrr < 25; maxGrr ++ ) {
				sf = new SpielfeldImpl(reihenAnz, maxGrr);
				reihenTesten(maxGrr, reihenAnz);
			}
		}
	}
	
	@Test
	void testSteinEinwerfenDiffrentHugeSizes() {
		for (int reihenAnz = 0; reihenAnz < 100; reihenAnz ++ ) {
			for (int maxGrr = 0; maxGrr < 100; maxGrr ++ ) {
				sf = new SpielfeldImpl(reihenAnz, maxGrr);
				reihenTesten(maxGrr, reihenAnz);
			}
		}
	}
	
	@Test
	//@formatter:off
	void testAlleReihen() {
		assertArrayEquals(new Spieler[REIHEN_ANZAHL][0], sf.alleReihen());
		sf.steinEinwerfen(s1, 4);
		assertArrayEquals(new Spieler[][] {
			{},
			{},
			{},
			{},
			{s1},
		}, sf.alleReihen());
		sf.steinEinwerfen(s2, 3);
		assertArrayEquals(new Spieler[][] {
			{},
			{},
			{},
			{s2},
			{s1},
		}, sf.alleReihen());
		sf.steinEinwerfen(s1, 2);
		assertArrayEquals(new Spieler[][] {
			{},
			{},
			{s1},
			{s2},
			{s1},
		}, sf.alleReihen());
		sf.steinEinwerfen(s1, 1);
		assertArrayEquals(new Spieler[][] {
			{},
			{s1},
			{s1},
			{s2},
			{s1},
		}, sf.alleReihen());
		sf.steinEinwerfen(s1, 0);
		assertArrayEquals(new Spieler[][] {
			{s1},
			{s1},
			{s1},
			{s2},
			{s1},
		}, sf.alleReihen());
		sf.steinEinwerfen(s2, 0);
		assertArrayEquals(new Spieler[][] {
			{s1,s2},
			{s1},
			{s1},
			{s2},
			{s1},
		}, sf.alleReihen());
		sf.steinEinwerfen(s2, 0);
		assertArrayEquals(new Spieler[][] {
			{s1,s2,s2},
			{s1},
			{s1},
			{s2},
			{s1},
		}, sf.alleReihen());
		sf.steinEinwerfen(s2, 0);
		assertArrayEquals(new Spieler[][] {
			{s1,s2,s2,s2},
			{s1},
			{s1},
			{s2},
			{s1},
		}, sf.alleReihen());
		sf.steinEinwerfen(s1, 0);
		assertArrayEquals(new Spieler[][] {
			{s1,s2,s2,s2,s1},
			{s1},
			{s1},
			{s2},
			{s1},
		}, sf.alleReihen());
	}
	//@formatter:on
	
	@Test
	void testReihe() {
		// Spieler[] reihe(int i);
		testReihen(new Spieler[REIHEN_ANZAHL][0]);
		sf.steinEinwerfen(s1, 4);
		testReihen(new Spieler[][] {{}, {}, {}, {}, {s1 }, });
		sf.steinEinwerfen(s2, 3);
		testReihen(new Spieler[][] {{}, {}, {}, {s2 }, {s1 }, });
		sf.steinEinwerfen(s1, 2);
		testReihen(new Spieler[][] {{}, {}, {s1 }, {s2 }, {s1 }, });
		sf.steinEinwerfen(s1, 1);
		testReihen(new Spieler[][] {{}, {s1 }, {s1 }, {s2 }, {s1 }, });
		sf.steinEinwerfen(s1, 0);
		testReihen(new Spieler[][] {{s1 }, {s1 }, {s1 }, {s2 }, {s1 }, });
		sf.steinEinwerfen(s2, 0);
		testReihen(new Spieler[][] {{s1, s2 }, {s1 }, {s1 }, {s2 }, {s1 }, });
		sf.steinEinwerfen(s2, 0);
		testReihen(new Spieler[][] {{s1, s2, s2 }, {s1 }, {s1 }, {s2 }, {s1 }, });
		sf.steinEinwerfen(s2, 0);
		testReihen(new Spieler[][] {{s1, s2, s2, s2 }, {s1 }, {s1 }, {s2 }, {s1 }, });
		sf.steinEinwerfen(s1, 0);
		testReihen(new Spieler[][] {{s1, s2, s2, s2, s1 }, {s1 }, {s1 }, {s2 }, {s1 }, });
	}
	
	private void testReihen(Spieler[][] spieler) {
		for (int index = 0; index < spieler.length; index ++ ) {
			assertArrayEquals(spieler[index], sf.reihe(index));
		}
	}
	
	@Test
	void testReihenAnzahl() {
		assertEquals(REIHEN_ANZAHL, sf.reihenAnzahl());
		for (int reihenAnzahl = 0; reihenAnzahl < 100; reihenAnzahl ++ ) {
			for (int maxGr = 0; maxGr < 10; maxGr ++ ) {
				sf = new SpielfeldImpl(reihenAnzahl, maxGr);
				assertEquals(reihenAnzahl, sf.reihenAnzahl());
			}
		}
	}
	
	@Test
	void testMaximaleReihenGröße() {
		assertEquals(MAX_GRÖSSE, sf.maximaleReihenGröße());
		for (int maxGr = 0; maxGr < 100; maxGr ++ ) {
			for (int reihenAnzahl = 0; reihenAnzahl < 10; reihenAnzahl ++ ) {
				sf = new SpielfeldImpl(reihenAnzahl, maxGr);
				assertEquals(maxGr, sf.maximaleReihenGröße());
			}
		}
	}
	
	private void reihenTesten(int magGr, int reihenAnz) {
		assertThrows(IndexOutOfBoundsException.class, () -> sf.steinEinwerfen(s1, -1));
		for (int ii = 0; ii < reihenAnz; ii ++ ) {
			for (int i = 0; i < magGr; i ++ ) {
				sf.steinEinwerfen(s1, ii);
			}
			try {
				sf.steinEinwerfen(s1, ii);
				fail("no exeption!");
			} catch (RuntimeException re) {
				if (re.getClass() != RuntimeException.class) fail("wrong exeption", re);
			}
		}
		assertThrows(IndexOutOfBoundsException.class, () -> sf.steinEinwerfen(s1, reihenAnz));
	}
	
}
