package viergewinnt.controller.interfaces;

import viergewinnt.model.interfaces.Spielfeld;
import viergewinnt.model.objects.SpielfeldImpl;
import viergewinnt.view.interfaces.Gui4Gewinnt;
import viergewinnt.view.objects.Gui4GewinntImpl;

public class VierGewinntControllerImpl implements VierGewinntController {
	
	private Gui4Gewinnt s1;
	private Gui4Gewinnt s2;
	private Spielfeld sf;
	
	public VierGewinntControllerImpl() {
		sf = new SpielfeldImpl();
		s1 = new Gui4GewinntImpl(sf);
		s2 = new Gui4GewinntImpl(sf);
	}
	
	@Override
	public void run() {
		while ( !sf.fertigGespiel()) {
			s1.repaint();
			s1.zugBeginnt();
			Object lock = s1.lock();
			synchronized (lock) {
				while ( !s1.hatSichEntschieden()) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			sf.steinEinwerfen(s1, s1.zugMachen());
			s2.repaint();
			s2.zugBeginnt();
			if (sf.fertigGespiel()) {
				break;
			}
			lock = s2.lock();
			synchronized (lock) {
				while ( !s2.hatSichEntschieden()) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			sf.steinEinwerfen(s2, s2.zugMachen());
		}
		//TODO finish game
	}
	
	public static void main(String[] args) {
		new VierGewinntControllerImpl().run();
	}
	
}
