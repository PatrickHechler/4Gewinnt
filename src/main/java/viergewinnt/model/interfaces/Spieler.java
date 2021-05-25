package viergewinnt.model.interfaces;

public interface Spieler {
	
	default Object lock() {
		return this;
	}
	
	boolean hatSichEntschieden();
	
	int zugMachen();
	
}
