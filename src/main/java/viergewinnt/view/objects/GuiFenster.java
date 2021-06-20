package viergewinnt.view.objects;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viergewinnt.model.interfaces.Spielfeld;
import viergewinnt.model.objects.SpielfeldImpl;
import viergewinnt.view.interfaces.ButtonClickListener;
import viergewinnt.view.interfaces.Spieler;


public class GuiFenster extends JFrame {
	
	/** UID */
	private static final long serialVersionUID = 7722436922407402080L;
	
	
	private static Icon leer;
	private static Icon gelb;
	private static Icon rot;
	private static Icon rotGelb;
	
	private JButton[][] felder;
	
	private final Spieler ich;
	
	public GuiFenster(Spieler ich) {
		this.ich = ich;
	}
	
	public GuiFenster load(Spielfeld sf, ButtonClickListener bcl, String title) {
		synchronized (GuiFenster.class) {
			if (leer == null) {
				ImageIcon zw = new ImageIcon(getClass().getResource("/leeresFeld.png"));
				leer = new ImageIcon(zw.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
				zw = new ImageIcon(getClass().getResource("/gelbesFeld.png"));
				gelb = new ImageIcon(zw.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
				zw = new ImageIcon(getClass().getResource("/rotesFeld.png"));
				rot = new ImageIcon(zw.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
				zw = new ImageIcon(getClass().getResource("/rotGelbFeld.png"));
				rotGelb = new ImageIcon(zw.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			}
		}
		
		setTitle(title);
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
				felder[x][y].addActionListener(e -> {
					bcl.clicked(x);
				});
				add(felder[x][y]);
				setzteFeld(x, y, null);
			}
		}
		
		setLocationRelativeTo(null);
		setVisible(true);
		return this;
	}
	
	public void setzteFeld(int reihenIndex, int spaltenIndex, Spieler spieler) {
		JButton setzte = felder[reihenIndex][spaltenIndex];
		if (spieler == null) {
			setzte.setEnabled(true);
			setzte.setIcon(leer);
			setzte.setDisabledIcon(leer);
		} else {
			setzte.setEnabled(false);
			if (spieler == ich) {
				setzte.setIcon(gelb);
				setzte.setDisabledIcon(gelb);
			} else {
				setzte.setIcon(rot);
				setzte.setDisabledIcon(rot);
			}
		}
	}
	
	public void gewonnen() {
		setTitle("GEWONNEN");
		JOptionPane.showMessageDialog(this, "Du hast gewonnen!", "GEWONNEN", JOptionPane.INFORMATION_MESSAGE, gelb);
	}
	
	public void verloren() {
		setTitle("VERLOREN");
		JOptionPane.showMessageDialog(this, "Du hast verloren!", "VERLOREN", JOptionPane.INFORMATION_MESSAGE, rot);
	}
	
	public void unentschieden() {
		setTitle("UNENTSCHIEDEN");
		JOptionPane.showMessageDialog(this, "Das war ein unentschieden!", "UNENTSCHIEDEN", JOptionPane.INFORMATION_MESSAGE, rotGelb);
	}
	
}
