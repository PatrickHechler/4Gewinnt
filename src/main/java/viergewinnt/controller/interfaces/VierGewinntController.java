package viergewinnt.controller.interfaces;

import viergewinnt.model.interfaces.Spielfeld;
import viergewinnt.view.interfaces.Spieler;

/**
 * Der {@link VierGewinntController} bildet die Brücke von der {@link Spieler view} und dem {@link Spielfeld model}
 */
public interface VierGewinntController extends Runnable {
	
	/**
	 * startet ein 4 Gewinnt Spiel und leitet dort die Kommunikation von der {@link Spieler view} und dem {@link Spielfeld model}
	 */
	@Override
	void run();
	
}
