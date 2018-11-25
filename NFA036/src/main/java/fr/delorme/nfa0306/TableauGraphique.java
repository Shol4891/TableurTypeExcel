package fr.delorme.nfa0306;
import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.GridLayout;
	import java.awt.Label;
	import java.util.regex.*;

	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	
	public class TableauGraphique {
	    JPanel jpane;
	    JFrame fenetre;
	    MyCellule[][] tft;
	    int l,c;
	    
	    public MyCellule[][] getTft() {return tft;}  // retourne le tableau de tableau de type MyCellule
	    public int getI() {return l;} // retourne la ligne
		public int getJ() {return c;} // retourne la colonne
		
		// Constructeur créant une fenêtre graphique avec une feuille de calcul vierge de l lignes et c colonnes 
	    public TableauGraphique(int l, int c){
	    	this.l=l; this.c=c;
		    tft = new MyCellule[l][c];
		    	
		    fenetre = new JFrame();
			fenetre.setTitle("Tableur"); 
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jpane = new JPanel();
			fenetre.setContentPane(jpane);
			jpane.setLayout(new GridLayout(11,7));
			jpane.setPreferredSize(new Dimension(900, 400));
			jpane.setBackground(new Color(205,26,26));
			
			//Boucle qui instancie un objet MyCellule pour chaque future "case" du tableur
			for (int i=1; i<l; i++){
			    for (int j=1; j<c; j++){  
				tft[i][j]=new MyCellule(i,j,tft);
				tft[i][j].setHorizontalAlignment(JTextField.CENTER); 
				tft[i][j].setEditable(false);
				tft[i][j].setBackground(new Color(255,228,225));
			    }
			}
			
			
			jpane.setForeground(new Color(255,255,0));
			jpane.add(new Label("Tableau",Label.CENTER));
			//Boucle ajoutant les numeros de colonne
			for (int i=1; i<c; i++){
			    jpane.add(new MyJLabel("" + (i),JLabel.CENTER));
			}
			//Boucle ajoutant les numeros de lignes ainsi que les objets MyCellule respectivement dans chaque case.
			for (int i=1; i<l; i++){
			    jpane.add(new MyJLabel(""+ (i),JLabel.CENTER));
			    for (int j=1; j<c; j++){
				jpane.add(tft[i][j]);
			    }
			}
			fenetre.pack();
			fenetre.setVisible(true);
	    }
	    
		// méthode qui affiche la chaine s sur la case en ligne x et colonne y
	    public void afficheExpression(String s, int x, int y){
	    	if(x>0 && x<l && y>0 && y<c)
	    	tft[x][y].insererExpression(s);
	    	else JOptionPane.showMessageDialog(fenetre, "Coordonnées hors champ!");
	    }
	    
	   // méthode qui affiche la valeur numérique val sur la case en ligne x et colonne y 
	    public  void afficheValeur(float val, int x, int y){
	    	if(x>0 && x<l && y>0 && y<c)
	    	tft[x][y].insererValeur(val);
	    	else JOptionPane.showMessageDialog(fenetre, "Coordonnées hors champ!");
	    }
	    
	   // méthode qui efface la case en ligne x et colonne y 
	    public void effaceCase(int x, int y){
	    	if(x>0 && x<l && y>0 && y<c)
	    	tft[x][y].effacer();
	    	else JOptionPane.showMessageDialog(fenetre, "Coordonnées hors champ!");
	    }
	    
	    public void affichageNum(){
	    	for(int i = 1; i < tft.length; i++){
				for(int j = 1; j < tft[0].length; j++){
					tft[i][j].affichage("num");
				}
			}
	    }
	    
	    public void affichageLit(){
	    	for(int i = 1; i < tft.length; i++){
				for(int j = 1; j < tft[0].length; j++){
					tft[i][j].affichage("lit");
				}	
			}
	    }   
	}

	//====================================================================

	class MyJLabel extends JTextField{
	    MyJLabel(String s, int i){
		super(s,i);
		this.setForeground(new Color(255,255,0));
		this.setBackground(new Color(205,26,26));
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setEditable(false);
	    }
	} 

