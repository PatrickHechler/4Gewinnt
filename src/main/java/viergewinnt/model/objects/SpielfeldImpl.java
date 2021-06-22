package viergewinnt.model.objects;

import java.util.ArrayList;
import java.util.List;

import viergewinnt.model.interfaces.Spielfeld;
import viergewinnt.view.interfaces.Spieler;


public class SpielfeldImpl implements Spielfeld {
	
	private static final int DEFAULT_REIHEN_ANZAHL = 7;
	private static final int DEFAULT_MAX_REIHEN_GRÖẞE = 6;
	
	
	
	private final int maxReihenGröße;
	private final List <Spieler>[] reihen;
	
	
	
	public SpielfeldImpl() {
		this(DEFAULT_REIHEN_ANZAHL, DEFAULT_MAX_REIHEN_GRÖẞE);
	}
	
	@SuppressWarnings("unchecked")
	public SpielfeldImpl(int reihenAnzahl, int maxReihenGröße) {
		super();
		this.maxReihenGröße = maxReihenGröße;
		this.reihen = (List <Spieler>[]) new List <?>[reihenAnzahl];
		for (int reihenIndex = 0; reihenIndex < reihen.length; reihenIndex ++ ) {
			reihen[reihenIndex] = new ArrayList <Spieler>();
		}
	}
	
	
	
	@Override
	public void steinEinwerfen(Spieler spieler, int reihe) throws IndexOutOfBoundsException, RuntimeException {
		if (reihe < 0 || reihe >= this.reihen.length) throw new IndexOutOfBoundsException("reihe=" + reihe + " reihenanzahl=" + reihen.length);
		List <Spieler> dieseReihe = reihen[reihe];
		if (dieseReihe.size() < maxReihenGröße) dieseReihe.add(spieler);
		else throw new RuntimeException("bereits die maximale größe erreicht: reihe=" + reihe + " reihen[reihe]=" + dieseReihe + " maxReihenGröße=" + maxReihenGröße);
	}
	
	@Override
	public boolean fertigGespiel() {
		if (ergebnis() != null) return true;
		for (List <Spieler> dieseReihe : reihen) {
			if (dieseReihe.size() < maxReihenGröße) return false;
		}
		return true;
	}
	
	@Override
	public Spieler ergebnis() {
		for (List <Spieler> dieseReihe : reihen) {
			int s = dieseReihe.size();
			if (s >= 4) {
				Spieler gewinner = dieseReihe.get(s - 1);
				if (gewinner != dieseReihe.get(s - 2)) {
					continue;
				}
				if (gewinner != dieseReihe.get(s - 3)) {
					continue;
				}
				if (gewinner != dieseReihe.get(s - 4)) {
					continue;
				}
				return gewinner;
			}
		}
		for (int reihenIndex = 3; reihenIndex < reihen.length; reihenIndex ++ ) {
			List <Spieler> dieseReihe = reihen[reihenIndex];
			for (int gewinnerIndex = 3; gewinnerIndex < dieseReihe.size(); gewinnerIndex ++ ) {
				Spieler gewinner = dieseReihe.get(gewinnerIndex);
				List <Spieler> reihe = reihen[reihenIndex - 1];
				if (reihe.size() <= gewinnerIndex - 1 || gewinner != reihe.get(gewinnerIndex - 1)) {
					continue;
				}
				reihe = reihen[reihenIndex - 2];
				if (reihe.size() <= gewinnerIndex - 2 || gewinner != reihe.get(gewinnerIndex - 2)) {
					continue;
				}
				reihe = reihen[reihenIndex - 3];
				if (reihe.size() <= gewinnerIndex - 3 || gewinner != reihe.get(gewinnerIndex - 3)) {
					continue;
				}
				return gewinner;
			}
		}
		for (int reihenIndex = 3; reihenIndex < reihen.length; reihenIndex ++ ) {
			List <Spieler> dieseReihe = reihen[reihenIndex];
			for (int gewinnerIndex = 0; gewinnerIndex < dieseReihe.size(); gewinnerIndex ++ ) {
				Spieler gewinner = dieseReihe.get(gewinnerIndex);
				List <Spieler> reihe = reihen[reihenIndex - 1];
				if (reihe.size() <= gewinnerIndex + 1 || gewinner != reihe.get(gewinnerIndex + 1)) {
					continue;
				}
				reihe = reihen[reihenIndex - 2];
				if (reihe.size() <= gewinnerIndex + 2 || gewinner != reihe.get(gewinnerIndex + 2)) {
					continue;
				}
				reihe = reihen[reihenIndex - 3];
				if (reihe.size() <= gewinnerIndex + 3 || gewinner != reihe.get(gewinnerIndex + 3)) {
					continue;
				}
				return gewinner;
			}
		}
		for (int reihenIndex = 0; reihenIndex < reihen.length - 4; reihenIndex ++ ) {
			List <Spieler> dieseReihe = reihen[reihenIndex];
			for (int gewinnerIndex = 0; gewinnerIndex < dieseReihe.size(); gewinnerIndex ++ ) {
				Spieler gewinner = dieseReihe.get(gewinnerIndex);
				List <Spieler> reihe = reihen[reihenIndex + 1];
				if (reihe.size() <= gewinnerIndex || gewinner != reihe.get(gewinnerIndex)) {
					continue;
				}
				reihe = reihen[reihenIndex + 2];
				if (reihe.size() <= gewinnerIndex || gewinner != reihe.get(gewinnerIndex)) {
					continue;
				}
				reihe = reihen[reihenIndex + 3];
				if (reihe.size() <= gewinnerIndex || gewinner != reihe.get(gewinnerIndex)) {
					continue;
				}
				return gewinner;
			}
		}
		return null;
	}
	
	@Override
	public Spieler[][] alleReihen() {
		Spieler[][] alle = new Spieler[reihen.length][];
		for (int index = 0; index < reihen.length; index ++ ) {
			alle[index] = reihen[index].toArray(new Spieler[reihen[index].size()]);
		}
		return alle;
	}
	
	@Override
	public Spieler[] reihe(int index) {
		return reihen[index].toArray(new Spieler[reihen[index].size()]);
	}
	
	@Override
	public int reihenAnzahl() {
		return reihen.length;
	}
	
	@Override
	public int maximaleReihenGröße() {
		return maxReihenGröße;
	}
	
}
