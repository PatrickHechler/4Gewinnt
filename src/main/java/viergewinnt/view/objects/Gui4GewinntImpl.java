package viergewinnt.view.objects;

import viergewinnt.model.interfaces.Spielfeld;
import viergewinnt.view.interfaces.Gui4Gewinnt;
import viergewinnt.view.objects.helps.GuiFenster;


public class Gui4GewinntImpl implements Gui4Gewinnt {
	
	private final Spielfeld sf;
	private final GuiFenster fenster;
	
	
	
	public Gui4GewinntImpl(Spielfeld sf) {
		this.sf = sf;
		this.fenster = new GuiFenster().load(sf);
	}
	
	
	
	@Override
	public boolean hatSichEntschieden() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int zugMachen() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		
	}
	
}
