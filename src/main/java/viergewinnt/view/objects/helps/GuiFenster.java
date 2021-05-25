package viergewinnt.view.objects.helps;

import javax.swing.JButton;
import javax.swing.JFrame;

import viergewinnt.model.interfaces.Spielfeld;


public class GuiFenster extends JFrame {
	
	/** UID */
	private static final long serialVersionUID = 7722436922407402080L;
	
	
	
	private JButton[][] felder;
	
	
	
	public GuiFenster() {
	}
	
	public GuiFenster load(Spielfeld sf) {
		felder = new JButton[sf.reihenAnzahl()][sf.maximaleReihenGröße()];
		
		setLayout(null);
		setBounds(0, 0, felder.length * 50, felder[0].length * 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//do not kill the VM
		setLocationRelativeTo(null);
		setVisible(true);
		// TODO Auto-generated method stub
		
		
		
		return this;
	}
	
}
