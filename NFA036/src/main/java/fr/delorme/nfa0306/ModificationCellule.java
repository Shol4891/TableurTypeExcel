package fr.delorme.nfa0306;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ModificationCellule implements ActionListener {

	JPanel contenu;
	JFrame fenetreM;
	TableauGraphique tab;

	public ModificationCellule(TableauGraphique tab) {

		this.tab = tab;

		fenetreM = new JFrame();
		fenetreM.setTitle("Coordonnées et valeur : ");
		fenetreM.setLocation(950, 250);
		fenetreM.setResizable(false);
		contenu = new JPanel();
		fenetreM.setContentPane(contenu);
		contenu.setPreferredSize(new Dimension(230, 160));
		contenu.setBackground(new Color(0, 146, 230));
		contenu.setLayout(new FlowLayout());

		coor = new JLabel("Ligne, colonne : ");
		contenu.add(coor);
		ligne = new JTextField(5);
		contenu.add(ligne);
		col = new JTextField(5);
		contenu.add(col);
		valLab = new JLabel("Nouvelle valeur : ");
		contenu.add(valLab);
		val = new JTextField(20);
		contenu.add(val);

		gr = new ButtonGroup();
		radio1 = new JRadioButton("Valeur num.");
		contenu.add(radio1);
		gr.add(radio1);
		radio1.addActionListener(this);
		radio2 = new JRadioButton("Valeur lit.");
		contenu.add(radio2);
		gr.add(radio2);
		radio2.addActionListener(this);

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

			try {
				if (radio1.isSelected()) {
					valN = Float.parseFloat(val.getText());
					tab.afficheValeur(valN, coorX, coorY);
				}

				else {
					valL = val.getText();
					tab.afficheExpression(valL, coorX, coorY);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(contenu, "Mauvais format de valeur");
			}
		}
	}

	public void afficher() {
		fenetreM.setVisible(true);
	}

	private int coorX, coorY;
	private String valL;
	private float valN;

	private JLabel coor, valLab;
	private JTextField val, ligne, col;
	private JButton ok, canc;
	private JRadioButton radio1, radio2;
	private ButtonGroup gr;
}