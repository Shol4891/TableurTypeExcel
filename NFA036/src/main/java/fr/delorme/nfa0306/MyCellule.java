package fr.delorme.nfa0306;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyCellule extends JTextField {

	private int coordX;
	private int coordY;
	private MyCellule tab[][];
	private int etat = 1; // 1 : numerique 2 : litteral
	private float valNum;
	private String valLit = "";
	private TestExpressions t;

	// une cellule en aval d'une autre est référencée littéralement par cette
	// derniere
	// une cellule en amont d'une autre référencie littéralement cette derniere
	private ArrayList<MyCellule> celluleReferencee; // cellules en aval au premier degré seulement
	private ArrayList<MyCellule> celluleQuiReference;// cellules en amont au premier degré seulement
	private ArrayList<MyCellule> celluleQuiRefTemp;// Tableau temporaire pour éviter le dereglement des indices lors des
													// recalculs récursifs
	private ArrayList<MyCellule> listeRecup;
	private ArrayList<MyCellule> listeTemp;

	Color customColor = new Color(50, 150, 50);

	// ======================================================================================
	MyCellule(int i, int j, MyCellule tab[][]){
		coordX=i;
		coordY=j;
		this.tab=tab;
		celluleReferencee = new ArrayList<MyCellule>(59);
		celluleQuiReference = new ArrayList<MyCellule>(59);
		}

	public void affichage(String r){
		if (r == "lit" && etat == 2) this.setText(valLit); else this.setText("" + valNum);
	}
//======================================================================================
	public void insererValeur(float n){
		this.effacer();	
		valNum=n;
		if(n != 0) this.setForeground(Color.BLUE);
		this.setText("" + n);
		etat = 1;		
		recalculerAmont();
		}
	
	public void insererExpression(String s){
		String valLTemp = valLit;
		float valNTemp = valNum;
		int etatTemp = etat;
		this.effacer();
		
		t = new TestExpressions();
		t.test(tab, s, coordX, coordY);

		if(t.getB()){			
			etat = 2;
			valNum = t.getValNum();
			valLit=s;
			this.setForeground(customColor);
			if (etat == 1) this.setText("" + valNum);
					else this.setText(s);
			recalculerAmont();
			} else {
				JOptionPane.showMessageDialog(this, "Formule non valide!");
				if(etatTemp == 1) insererValeur(valNTemp);
				else	insererExpression(valLTemp);
					}
	}

	public void effacer(){
		if(etat == 2){ //Si notre cellule est de type litteral, on prévient les cellules en aval de cette suppression
			for(int n =0; n<celluleReferencee.size(); n++){
				for(int m=0; m<celluleReferencee.get(n).getCelluleQuiReference().size(); m++){
					if (celluleReferencee.get(n).getCelluleQuiReference().get(m).equals(tab[coordX][coordY])) celluleReferencee.get(n).getCelluleQuiReference().remove(m);
					}
				}
			}		
		valLit = null;
		valNum = 0;		
		recalculerAmont();		
		celluleReferencee = new ArrayList<MyCellule>(59); //on réinitialise le tableau contenant les cellules référencées (en aval)
		this.setText("" + valNum);
		this.setForeground(Color.BLACK);
	}
	
	private void recalculerAmont (){
		celluleQuiRefTemp = new ArrayList<MyCellule>(59);
		celluleQuiRefTemp.addAll(celluleQuiReference);
		for(int i =0; i<celluleQuiRefTemp.size(); i++){ //On recalcule les cellules en amont
			celluleQuiRefTemp.get(i).insererExpression(celluleQuiRefTemp.get(i).getValL());
			}
	}

	
//Les méthodes suivantes sont utilisées dans la classe TestExpressions
//======================================================================================
	
	//Teste des cellules en aval de la chaine de referencement pour éviter l'inter-dépendance entre cellules
	public boolean testAval(int i, int j){
		listeTemp = recupAval();
		for(int k = 0; k<listeTemp.size(); k++){
			if (tab[i][j].equals(listeTemp.get(k))) return false;
		}	
		return true;
	}
	//Méthode récursive qui récupere l'ensemble des cellules référencées par l'ensembles des cellules en aval
	public ArrayList<MyCellule> recupAval(){ 
		listeRecup = new ArrayList<MyCellule>();
		listeRecup.addAll(celluleReferencee);
		
			for(int m=0; m<celluleReferencee.size(); m++){
				listeRecup.addAll(celluleReferencee.get(m).recupAval());
			}
		return listeRecup;
	}
//======================================================================================
	public void ajouterCelReferencee (int i, int j){
		boolean y = true;
		for(MyCellule m : celluleReferencee){
			if(m.equals(tab[i][j])) y = false;
		}
		if (y==true) celluleReferencee.add(tab[i][j]);
	}	
	public void ajouterCelQuiReference (int i, int j){
		boolean y = true;
		for(MyCellule m : celluleQuiReference){
			if(m.equals(tab[i][j])) y = false;
		}
		if (y==true) celluleQuiReference.add(tab[i][j]);
	}
//======================================================================================
	public float getValN(){return valNum;}
	public String getValL(){return valLit;}
	public int getEtat(){return etat;}
	public int getCoordX() {return coordX;}
	public int getCoordY() {return coordY;}
	public ArrayList<MyCellule> getCelluleQuiReference() {return celluleQuiReference;}
}