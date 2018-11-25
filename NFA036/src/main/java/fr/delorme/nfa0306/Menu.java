package fr.delorme.nfa0306;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu implements ActionListener {

	JPanel jpane;
	JFrame fenetre;
	JButton affNum, affLit, modCell, supCell, impTxt, sveTxt;
	TableauGraphique tab;
	MyCellule[][] tft;

	boolean etat = false;
	ModificationCellule m;
	boolean etat1 = false;
	SuppressionCellule s;
	boolean etatT = false;
	boolean etatS = false;
	EcritureTxt w;

	public Menu(TableauGraphique tab) {
		this.tab = tab;

		fenetre = new JFrame();
		fenetre.setTitle("Menu");
		fenetre.setLocation(950, 0);t
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpane = new JPanel();
		fenetre.setContentPane(jpane);
		jpane.setLayout(new BoxLayout(jpane, BoxLayout.PAGE_AXIS));
		jpane.setPreferredSize(new Dimension(180, 180));
		jpane.setBackground(new Color(0, 146, 230));
		jpane.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

		// Integration des differents boutons:
		affNum = new JButton("Affichage Numerique");
		affLit = new JButton("Affichage Litteral");
		modCell = new JButton("Modifier Cellule");
		supCell = new JButton("Effacer Cellule");
		sveTxt = new JButton("Sauver en fichier txt");
		jpane.add(affNum);
		jpane.add(affLit);
		jpane.add(modCell);
		jpane.add(supCell);
		jpane.add(sveTxt);

		affNum.addActionListener(this);
		affLit.addActionListener(this);
		modCell.addActionListener(this);
		supCell.addActionListener(this);
		sveTxt.addActionListener(this);

		fenetre.pack();
		fenetre.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == affNum)
			tab.affichageNum();

		if (e.getSource() == affLit)
			tab.affichageLit();

		if (e.getSource() == modCell) {
			if (etat == false) {
				m = new ModificationCellule(tab);
				etat = true;
			} else if (m instanceof ModificationCellule)
				m.afficher();
		}

		if (e.getSource() == supCell) {
			if (etat1 == false) {
				s = new SuppressionCellule(tab);
				etat1 = true;
			} else if (s instanceof SuppressionCellule)
				s.afficher();
		}
		if (e.getSource() == sveTxt) {
			new EcritureTxt(tab);
		}
	}
}