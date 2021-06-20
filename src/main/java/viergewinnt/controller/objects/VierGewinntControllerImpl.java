package viergewinnt.controller.objects;

import viergewinnt.controller.interfaces.VierGewinntController;
import viergewinnt.model.interfaces.Spielfeld;
import viergewinnt.model.objects.SpielfeldImpl;
import viergewinnt.view.interfaces.Spieler;
import viergewinnt.view.objects.Gui4GewinntSpielerImpl;

public class VierGewinntControllerImpl implements VierGewinntController {
	
	private Spieler s1;
	private Spieler s2;
	private Spielfeld sf;
	
	public VierGewinntControllerImpl() {
		sf = new SpielfeldImpl();
		s1 = new Gui4GewinntSpielerImpl(sf);
		s2 = new Gui4GewinntSpielerImpl(sf);
	}
	
	public VierGewinntControllerImpl(Spieler s1) {
		sf = new SpielfeldImpl();
		this.s1 = s1;
		s2 = new Gui4GewinntSpielerImpl(sf);
	}
	
	public VierGewinntControllerImpl(Spieler s1, Spieler s2) {
		sf = new SpielfeldImpl();
		this.s1 = s1;
		this.s2 = s2;
	}
	
	public VierGewinntControllerImpl(int reihenAnzahl, int maxReihenGröße) {
		sf = new SpielfeldImpl(reihenAnzahl, maxReihenGröße);
		s1 = new Gui4GewinntSpielerImpl(sf);
		s2 = new Gui4GewinntSpielerImpl(sf);
	}
	
	public VierGewinntControllerImpl(Spieler s1, int reihenAnzahl, int maxReihenGröße) {
		sf = new SpielfeldImpl(reihenAnzahl, maxReihenGröße);
		this.s1 = s1;
		s2 = new Gui4GewinntSpielerImpl(sf);
	}
	
	public VierGewinntControllerImpl(Spieler s1, Spieler s2, int reihenAnzahl, int maxReihenGröße) {
		sf = new SpielfeldImpl(reihenAnzahl, maxReihenGröße);
		this.s1 = s1;
		this.s2 = s2;
	}
	
	@Override
	public void run() {
		int runde = 0;
		while ( !sf.fertigGespiel()) {
			int zug;
			s2.rebuild();
			s1.rebuild();
			s1.zugBeginnt();
			Object lock = s1.lock();
			synchronized (lock) {
				while (true) {
					while ( !s1.hatSichEntschieden()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					zug = s1.zugMachen();
					if (sf.reihe(zug).length >= sf.maximaleReihenGröße()) {
						s1.zugBeginnt();
					} else {
						break;
					}
				}
			}
			sf.steinEinwerfen(s1, zug);
			s1.rebuild();
			s2.rebuild();
			s2.zugBeginnt();
			if (sf.fertigGespiel()) {
				break;
			}
			lock = s2.lock();
			synchronized (lock) {
				while (true) {
					while ( !s2.hatSichEntschieden()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					zug = s2.zugMachen();
					if (sf.reihe(zug).length >= sf.maximaleReihenGröße()) {
						s2.zugBeginnt();
					} else {
						break;
					}
				}
			}
			sf.steinEinwerfen(s2, zug);
			System.out.println("runde=" + runde);
			runde ++ ;
		}
		System.out.println("FINISH");
		System.out.println("runde=" + runde);
		s1.rebuild();
		s2.rebuild();
		Spieler gewinner = sf.ergebnis();
		if (gewinner == null) {
			s1.unentschieden();
			s2.unentschieden();
		} else if (gewinner == s1) {
			s1.gewonnen();
			s2.verloren();
		} else if (gewinner == s2) {
			s1.verloren();
			s2.gewonnen();
		} else {
			throw new AssertionError("unbekannter Spieler hat gewonnen: '" + gewinner + "', s1: '" + s1 + "', s2: '" + s2 + "'");
		}
	}
	
	public static void main(String[] args) {
		new VierGewinntControllerImpl().run();
		System.out.println("FINISH");
	}
	
}
