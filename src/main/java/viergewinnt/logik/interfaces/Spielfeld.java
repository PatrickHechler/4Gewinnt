package viergewinnt.logik.interfaces;

public interface Spielfeld {
	
	void steinEinwerfen(Spieler spieler, int reihe);
	
	boolean fertigGespiel();
	
	Spieler ergebnis();
	
	Spieler[][] alleReihen();
	
	Spieler[] reihe(int i);
	
	int reihenAnzahl();
	
}
