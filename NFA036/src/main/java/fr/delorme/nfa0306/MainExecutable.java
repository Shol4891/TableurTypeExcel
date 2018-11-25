package fr.delorme.nfa0306;

public class MainExecutable {

	public static void main(String[] args) {

		int l = 11;
		int c = 7;

		TableauGraphique fenetre = new TableauGraphique(l, c);
		Menu menu = new Menu(fenetre);

		// TESTS FONCTIONNELS

		// Tests d'entrée de valeurs numériques:
		fenetre.afficheValeur(145, 4, 4);
		fenetre.afficheValeur(3, 1, 1);
		fenetre.afficheValeur(1, 1, 2);
		fenetre.afficheValeur(4, 2, 1);
		fenetre.afficheValeur(1, 2, 2);

		// Tests d'entrée de chaque type de valeurs littérales:
		fenetre.afficheExpression("(2,1)+2", 5, 2);
		fenetre.afficheExpression("Moy((1,1),(9,2))", 4, 3); // cette formule tient compte des zeros.
		fenetre.afficheExpression("(1,1)+(2,2)", 6, 6);
		fenetre.afficheExpression("6+(2,2)", 8, 6);
		fenetre.afficheExpression("Somme((1,1),(8,2))", 9, 1);

		// Tests de référencement:
		fenetre.afficheExpression("6+(9,1)", 9, 2);
		fenetre.afficheExpression("12+(9,2)", 9, 3);
		fenetre.afficheExpression("25+(9,2)", 8, 3);
		fenetre.afficheExpression("6+(9,3)", 9, 4);
		fenetre.afficheExpression("20+(9,3)", 8, 4);
		fenetre.afficheExpression("30+(9,3)", 7, 4);
		fenetre.afficheExpression("6+(9,4)", 9, 5);

		// Test de suppression au sein d'une chaine de referencement: (changements de
		// valeurs numériques des cases qui référencient (9,3) attendus)
		Terminal.lireString();
		fenetre.effaceCase(9, 3);

		// Test (avec échec attendu) d'auto-referencement des cellules: (la cellule
		// (9,4) référencie déja la cellule (9,3))
		Terminal.lireString();
		fenetre.afficheExpression("12+(9,4)", 9, 3);

	}

}
