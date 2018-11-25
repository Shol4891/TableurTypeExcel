package fr.delorme.nfa0306;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class TestExpressions {
	private boolean b = false;
	private float valNum, valTemp;

	private Pattern patternS, patternM, pattern1, pattern2, pattern3; // Somme, Moy, forme (1,1)+5, forme 5+(1,1), forme
																		// (1,1)+(2,2)
	private Matcher matcherS, matcherM, matcher1, matcher2, matcher3;
	private int x1, y1, x2, y2, n;
	private String op;
	private int compt = 0;

	public boolean getB() { // Renvoie un boolean indiquant l'aspect correct ou non de la syntaxe.
		return b;
	}

	public float getValNum() {
		return valNum;
	}

	public TestExpressions() {
	}

	public void test(MyCellule tab[][], String s, int coordX, int coordY) {

		patternS = Pattern.compile("Somme\\(\\((\\d+),([1-6])\\),\\((\\d+),([1-6])\\)\\)");
		patternM = Pattern.compile("Moy\\(\\((\\d+),([1-6])\\),\\((\\d+),([1-6])\\)\\)");
		pattern1 = Pattern.compile("\\((\\d+),([1-6])\\)(\\D)(\\d+)");
		pattern2 = Pattern.compile("(\\d+)(\\D)\\((\\d+),([1-6])\\)");
		pattern3 = Pattern.compile("\\((\\d+),([1-6])\\)(\\D)\\((\\d+),([1-6])\\)");

		matcherS = patternS.matcher(s);

		if (matcherS.find()) {
			b = true;
			x1 = Integer.parseInt(matcherS.group(1));
			y1 = Integer.parseInt(matcherS.group(2));
			x2 = Integer.parseInt(matcherS.group(3));
			y2 = Integer.parseInt(matcherS.group(4));

			if (x1 > 0 && x1 < 11 && y1 > 0 && y1 < 7 && x2 > 0 && x2 < 11 && y2 > 0 && y2 < 7 && x1 <= x2
					&& y1 <= y2) {
				for (int x = x1; x <= x2; x++) {
					for (int y = y1; y <= y2; y++) {
						if (tab[x][y].testAval(coordX, coordY)) {
							if (x != coordX || y != coordY) {
								valTemp += tab[x][y].getValN();
								tab[x][y].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x, y);
							} else {
								JOptionPane.showMessageDialog(tab[coordX][coordY], "Pas d'auto-referencement!");
								b = false;
							}
						} else {
							JOptionPane.showMessageDialog(tab[coordX][coordY], "Erreur referencement!");
							b = false;
						}
					}
				}
				valNum = valTemp;
				valTemp = 0;
			} else {
				JOptionPane.showMessageDialog(tab[coordX][coordY], "Coordonnées non valides!");
				b = false;
			}
		}

		matcherM = patternM.matcher(s);

		if (matcherM.find()) {
			b = true;
			x1 = Integer.parseInt(matcherM.group(1));
			y1 = Integer.parseInt(matcherM.group(2));
			x2 = Integer.parseInt(matcherM.group(3));
			y2 = Integer.parseInt(matcherM.group(4));
			if (x1 > 0 && x1 < 11 && y1 > 0 && y1 < 7 && x2 > 0 && x2 < 11 && y2 > 0 && y2 < 7 && x1 <= x2
					&& y1 <= y2) {
				for (int x = x1; x <= x2; x++) {
					for (int y = y1; y <= y2; y++) {
						if (tab[x][y].testAval(coordX, coordY)) {
							if (x != coordX || y != coordY) {
								valTemp += tab[x][y].getValN();
								compt++;
								tab[x][y].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x, y);
							} else {
								JOptionPane.showMessageDialog(tab[coordX][coordY], "Erreur referencement!");
								b = false;
							}
						} else {
							JOptionPane.showMessageDialog(tab[coordX][coordY], "Erreur referencement!");
							b = false;
						}
					}
				}
				valNum = valTemp / compt;
				compt = 0;
				valTemp = 0;
			} else {
				JOptionPane.showMessageDialog(tab[coordX][coordY], "Coordonnées non valides!");
				b = false;
			}
		}

		matcher1 = pattern1.matcher(s);

		if (matcher1.find()) {
			b = true;
			x1 = Integer.parseInt(matcher1.group(1));
			y1 = Integer.parseInt(matcher1.group(2));
			op = matcher1.group(3);
			n = Integer.parseInt(matcher1.group(4));
			if (tab[x1][y1].testAval(coordX, coordY)) {
				if (x1 != coordX || y1 != coordY) {
					if (x1 > 0 && x1 < 11 && y1 > 0 && y1 < 7) {
						if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
							switch (op) {
							case "+":
								valNum = tab[x1][y1].getValN() + n;
								tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x1, y1);
								break;
							case "-":
								valNum = tab[x1][y1].getValN() - n;
								tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x1, y1);
								break;
							case "*":
								valNum = tab[x1][y1].getValN() * n;
								tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x1, y1);
								break;
							case "/":
								valNum = tab[x1][y1].getValN() / n;
								tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x1, y1);
								break;
							}
						}
					} else {
						JOptionPane.showMessageDialog(tab[coordX][coordY], "Coordonnées non valides!");
						b = false;
					}
				} else {
					JOptionPane.showMessageDialog(tab[coordX][coordY], "Pas d'auto-referencement!");
					b = false;
				}
			} else {
				JOptionPane.showMessageDialog(tab[coordX][coordY], "Erreur referencement!");
				b = false;
			}
		}

		matcher2 = pattern2.matcher(s);

		if (matcher2.find()) {
			b = true;
			x1 = Integer.parseInt(matcher2.group(3));
			y1 = Integer.parseInt(matcher2.group(4));
			op = matcher2.group(2);
			n = Integer.parseInt(matcher2.group(1));
			if (tab[x1][y1].testAval(coordX, coordY)) {
				if (x1 != coordX || y1 != coordY) {
					if (x1 > 0 && x1 < 11 && y1 > 0 && y1 < 7) {
						if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
							switch (op) {
							case "+":
								valNum = tab[x1][y1].getValN() + n;
								tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x1, y1);
								break;
							case "-":
								valNum = n - tab[x1][y1].getValN();
								tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x1, y1);
								break;
							case "*":
								valNum = tab[x1][y1].getValN() * n;
								tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x1, y1);
								break;
							case "/":
								valNum = n / tab[x1][y1].getValN();
								tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
								tab[coordX][coordY].ajouterCelReferencee(x1, y1);
								break;
							}
						}
					} else {
						JOptionPane.showMessageDialog(tab[coordX][coordY], "Coordonnées non valides!");
						b = false;
					}
				} else {
					JOptionPane.showMessageDialog(tab[coordX][coordY], "Pas d'auto-referencement!");
					b = false;
				}
			} else {
				JOptionPane.showMessageDialog(tab[coordX][coordY], "Erreur referencement!");
				b = false;
			}
		}

		matcher3 = pattern3.matcher(s);

		if (matcher3.find() && s.charAt(0) == '(') {
			b = true;
			x1 = Integer.parseInt(matcher3.group(1));
			y1 = Integer.parseInt(matcher3.group(2));
			op = matcher3.group(3);
			x2 = Integer.parseInt(matcher3.group(4));
			y2 = Integer.parseInt(matcher3.group(5));
			if (tab[x1][y1].testAval(coordX, coordY) && tab[x2][y2].testAval(coordX, coordY)) {
				if (x1 != coordX || y1 != coordY) {
					if (x2 != coordX || y2 != coordY) {
						if (x1 > 0 && x1 < 11 && y1 > 0 && y1 < 7 && x2 > 0 && x2 < 11 && y2 > 0 && y2 < 7) {
							if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
								switch (op) {
								case "+":
									valNum = tab[x1][y1].getValN() + tab[x2][y2].getValN();
									tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
									tab[coordX][coordY].ajouterCelReferencee(x1, y1);
									tab[x2][y2].ajouterCelQuiReference(coordX, coordY);
									tab[coordX][coordY].ajouterCelReferencee(x2, y2);
									break;
								case "-":
									valNum = tab[x1][y1].getValN() - tab[x2][y2].getValN();
									tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
									tab[coordX][coordY].ajouterCelReferencee(x1, y1);
									tab[x2][y2].ajouterCelQuiReference(coordX, coordY);
									tab[coordX][coordY].ajouterCelReferencee(x2, y2);
									break;
								case "*":
									valNum = tab[x1][y1].getValN() * tab[x2][y2].getValN();
									tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
									tab[coordX][coordY].ajouterCelReferencee(x1, y1);
									tab[x2][y2].ajouterCelQuiReference(coordX, coordY);
									tab[coordX][coordY].ajouterCelReferencee(x2, y2);
									break;
								case "/":
									valNum = tab[x1][y1].getValN() / tab[x2][y2].getValN();
									tab[x1][y1].ajouterCelQuiReference(coordX, coordY);
									tab[coordX][coordY].ajouterCelReferencee(x1, y1);
									tab[x2][y2].ajouterCelQuiReference(coordX, coordY);
									tab[coordX][coordY].ajouterCelReferencee(x2, y2);
									break;
								}
							}
						} else {
							JOptionPane.showMessageDialog(tab[coordX][coordY], "Coordonnées non valides!");
							b = false;
						}
					} else {
						JOptionPane.showMessageDialog(tab[coordX][coordY], "Pas d'auto-referencement!");
						b = false;
					}
				} else {
					JOptionPane.showMessageDialog(tab[coordX][coordY], "Pas d'auto-referencement!");
					b = false;
				}
			} else {
				JOptionPane.showMessageDialog(tab[coordX][coordY], "Erreur referencement!");
				b = false;
			}
		}
	}
}