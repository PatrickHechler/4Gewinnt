package viergewinnt.model.interfaces;

public interface Spieler {
	
	Object lock();
	
	boolean hatSichEntschieden();
	
	int zugMachen();
	
	void zugBeginnt();
	
	void rebuild();
	
	void gewonnen();
	
	void verloren();
	
	void unentschieden();
	
}
