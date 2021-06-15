package viergewinnt.view.objects;

import viergewinnt.model.interfaces.Spieler;
import viergewinnt.model.interfaces.Spielfeld;
import viergewinnt.view.interfaces.ButtonClickListener;
import viergewinnt.view.objects.helps.GuiFenster;


public class Gui4GewinntSpielerImpl implements Spieler, ButtonClickListener {
	
	private final Spielfeld sf;
	private final GuiFenster fenster;
	private volatile int reihe;
	
	
	
	public Gui4GewinntSpielerImpl(Spielfeld sf) {
		this.sf = sf;
		this.fenster = new GuiFenster(this).load(sf, this, "WARTE");
		this.reihe = -1;
	}
	
	
	
	@Override
	public boolean hatSichEntschieden() {
		return reihe != -1;
	}
	
	@Override
	public int zugMachen() {
		int ret = reihe;
		if (ret == -1) {
			throw new IllegalStateException("ich habe mich noch nicht entschieden!");
		}
		fenster.setTitle("WARTE");
		reihe = -1;
		return ret;
	}
	
	@Override
	public void rebuild() {
		Spieler[][] alles = sf.alleReihen();
		int maxReihenGröße = sf.maximaleReihenGröße();
		for (int reihenIndex = 0; reihenIndex < alles.length; reihenIndex ++ ) {
			int spielerIndex;
			for (spielerIndex = 0; spielerIndex < alles[reihenIndex].length; spielerIndex ++ ) {
				fenster.setzteFeld(reihenIndex, spielerIndex, alles[reihenIndex][spielerIndex]);
			}
			for (; spielerIndex < maxReihenGröße; spielerIndex ++ ) {
				fenster.setzteFeld(reihenIndex, spielerIndex, null);
			}
		}
		fenster.repaint();
	}
	
	@Override
	public void clicked(int reihe) {
		this.reihe = reihe;
		Object lock = lock();
		synchronized (lock) {
			lock.notifyAll();
		}
	}
	
	@Override
	public Object lock() {
		return this;
	}
	
	@Override
	public void zugBeginnt() {
		fenster.setTitle("DEIN ZUG");
	}
	
	@Override
	public void gewonnen() {
		new Thread(() -> fenster.gewonnen()).start();
	}
	
	@Override
	public void verloren() {
		new Thread(() -> fenster.verloren()).start();
	}
	
	@Override
	public void unentschieden() {
		new Thread(() -> fenster.unentschieden()).start();
	}
	
}
