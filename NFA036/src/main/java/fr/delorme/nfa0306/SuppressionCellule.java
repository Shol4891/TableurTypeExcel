package fr.delorme.nfa0306;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SuppressionCellule implements ActionListener {
	JPanel contenu;
	JFrame fenetreM;
	TableauGraphique tab;

	public SuppressionCellule(TableauGraphique tab) {

		this.tab = tab;

		fenetreM = new JFrame();
		fenetreM.setTitle("Effacer");
		fenetreM.setLocation(1250, 250);
		fenetreM.setResizable(false);
		contenu = new JPanel();
		fenetreM.setContentPane(contenu);
		contenu.setPreferredSize(new Dimension(150, 120));
		contenu.setBackground(new Color(0, 146, 230));
		contenu.setLayout(new FlowLayout());

		coor = new JLabel("Ligne, colonne : ");
		contenu.add(coor);
		ligne = new JTextField(5);
		contenu.add(ligne);
		col = new JTextField(5);
		contenu.add(col);

		ok = new JButton("OK");
		contenu.add(ok);
		ok.addActionListener(this);
		canc = new JButton("Cancel");
		contenu.add(canc);
		canc.addActionListener(this);

		fenetreM.pack();
		fenetreM.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == canc)
			fenetreM.setVisible(false);

		if (e.getSource() == ok) {
			coorX = Integer.parseInt(ligne.getText());
			coorY = Integer.parseInt(col.getText());
			tab.effaceCase(coorX, coorY);
		}
	}

	public void afficher() {
		fenetreM.setVisible(true);
	}

	private int coorX, coorY;

	private JLabel coor;
	private JTextField ligne, col;
	private JButton ok, canc;
}