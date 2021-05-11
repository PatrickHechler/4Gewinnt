package viergewinnt.logik.objects;

import java.util.ArrayList;
import java.util.List;

import viergewinnt.logik.interfaces.Spieler;
import viergewinnt.logik.interfaces.Spielfeld;


public class SpielfeldImpl implements Spielfeld {
	
	private static final int DEFAULT_REIHEN_ANZAHL    = 5;
	private static final int DEFAULT_MAX_REIHEN_GRÖẞE = 5;
	
	
	
	private final int              maxReihenGröße;
	private final List <Spieler>[] reihen;
	
	
	
	public SpielfeldImpl() {
		this(DEFAULT_REIHEN_ANZAHL, DEFAULT_MAX_REIHEN_GRÖẞE);
	}
	
	@SuppressWarnings("unchecked")
	public SpielfeldImpl(int reihenAnzahl, int maxReihenGröße) {
		super();
		this.maxReihenGröße = maxReihenGröße;
		this.reihen = (List <Spieler>[]) new List <?>[reihenAnzahl];
		for (int i = 0; i < reihen.length; i ++ ) {
			reihen[i] = new ArrayList <Spieler>();
		}
	}
	
	
	
	@Override
	public void steinEinwerfen(Spieler spieler, int reihe) throws IndexOutOfBoundsException, RuntimeException {
		if (reihe < 0 || reihe >= this.reihen.length) throw new IndexOutOfBoundsException(reihe);
		List <Spieler> dieseReihe = reihen[reihe];
		if (dieseReihe.size() < maxReihenGröße) dieseReihe.add(spieler);
		else throw new RuntimeException("bereits die maximale größe erreicht: reihe=" + reihe + " reihen[reihe]=" + dieseReihe + " maxReihenGröße=" + maxReihenGröße);
	}
	
	@Override
	public boolean fertigGespiel() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Spieler ergebnis() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
