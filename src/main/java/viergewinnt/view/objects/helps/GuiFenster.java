package viergewinnt.view.objects.helps;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import viergewinnt.model.interfaces.Spieler;
import viergewinnt.model.interfaces.Spielfeld;
import viergewinnt.model.objects.SpielfeldImpl;
import viergewinnt.view.interfaces.ButtonClickListener;
import viergewinnt.view.objects.Gui4GewinntImpl;


public class GuiFenster extends JFrame {
	
	/** UID */
	private static final long serialVersionUID = 7722436922407402080L;
	
	
	private static Icon leer;
	private static Icon gelb;
	private static Icon rot;
	
	private JButton[][] felder;
	
	private final Spieler ich;
	
	public GuiFenster(Spieler ich) {
		this.ich = ich;
	}
	
	public GuiFenster load(Spielfeld sf, ButtonClickListener bcl) {
		synchronized (GuiFenster.class) {
			if (leer != null) {
				leer = new ImageIcon(getClass().getResource("./leeresFeld.png"));
				gelb = new ImageIcon(getClass().getResource("./gelbesFeld.png"));
				rot = new ImageIcon(getClass().getResource("./rotesFeld.png"));
			}
		}
		
		
		felder = new JButton[sf.reihenAnzahl()][sf.maximaleReihenGröße()];
		setLayout(null);
		setBounds(0, 0, felder.length * 50 + 16, (felder[0].length * 50) + 39);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		for (int i = 0; i < felder.length; i ++ ) {
			final int x = i;
			for (int ii = 0; ii < felder[x].length; ii ++ ) {
				final int y = ii;
				felder[x][y] = new JButton();
				felder[x][y].setBounds(x * 50, (felder[0].length - y) * 50 - 50, 50, 50);
				felder[x][y].addActionListener(e -> bcl.clicked(x));
				add(felder[x][y]);
				setzteFeld(x, y, null);
			}
		}
		
		setLocationRelativeTo(null);
		setVisible(true);
		return this;
	}
	
	public static void main(String[] args) {
		SpielfeldImpl spielfeldImpl = new SpielfeldImpl();
		Gui4GewinntImpl spieler1 = new Gui4GewinntImpl(spielfeldImpl);
		GuiFenster gf1 = new GuiFenster(spieler1).load(spielfeldImpl, spieler1);
		Gui4GewinntImpl spieler2 = new Gui4GewinntImpl(spielfeldImpl);
		GuiFenster gf2 = new GuiFenster(spieler1).load(spielfeldImpl, spieler1);
		spielfeldImpl.steinEinwerfen(spieler1, 4);
		spielfeldImpl.steinEinwerfen(spieler2, 4);
		spieler1.repaint();
		spieler2.repaint();
	}
	
	public void setzteFeld(int reihenIndex, int spielerIndex, Spieler spieler) {
		JButton setzte = felder[reihenIndex][spielerIndex];
		if (spieler == null) {
			setzte.setIcon(leer);
		} else if (spieler == ich) {
			setzte.setIcon(gelb);
		} else {
			setzte.setIcon(rot);
		}
	}
	
}
