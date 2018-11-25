package fr.delorme.nfa0306;

import java.io.*;

import javax.swing.JOptionPane;

public class EcritureTxt {
	TableauGraphique tab;
	private MyCellule t[][];
	String nomfichier = JOptionPane.showInputDialog("Entrez un repertoire d'enregistrement:");

	public EcritureTxt(TableauGraphique tab) {
		this.tab = tab;
		t = tab.getTft();
		nomfichier += "Ecriture.txt";

		try {
			FileWriter f = new FileWriter(nomfichier);
			BufferedWriter b = new BufferedWriter(f);
			PrintWriter p = new PrintWriter(b);
			for (int i = 1; i < tab.getI(); i++) {
				p.print(i + " ");
				for (int j = 1; j < tab.getJ(); j++) {
					p.print("&" + t[i][j].getText() + " ");
				}
				p.println();
			}
			p.close();
		} catch (IOException e) {
			Terminal.ecrireStringln("problème d'écriture");
		}
		JOptionPane.showMessageDialog(null, "Tableau sauvegardé en: " + nomfichier);
	}
}