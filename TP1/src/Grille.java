/*
 * TP 1 - IA Informatique : Sudoku
 * 
 * @author Tassadit BOUADI.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import jdk.internal.util.xml.impl.Pair;

public class Grille {
	private Case[][] _cases;
	private int _taille;
	private int _nbCasesVides;
	
	
	public Grille(int n){
		_taille = n;
		_nbCasesVides = n*n;
		_cases = new Case[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				_cases[i][j] = new Case(i, j, n);
			}
		}
	}
	
	public Grille(int n, int[][] grille){
		_taille = n;
		_nbCasesVides = n*n;
		_cases = new Case[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				_cases[i][j] = new Case(i, j, grille[i][j], n);
				if(_cases[i][j].getVal() != 0){
					_nbCasesVides --;
				}
			}
		}
	}
	
	public Grille(Grille grille){
		_taille = grille._taille;
		_nbCasesVides = grille._nbCasesVides;
		_cases = new Case[_taille][_taille];
		for(int i=0; i<_taille; i++){
			for(int j=0; j<_taille; j++){
				_cases[i][j] = new Case(grille.getCase(i, j));
			}
		}
	}
	
	
	public Case getCase(int i, int j){
		return _cases[i][j];
	}
	
	public int getNbCasesVides(){
		return _nbCasesVides;
	}
	
	
	/*
	 * Fonction qui donne la liste des cases sans valeur (peut être vide)
	 */
	public List<Case> getCasePossible(){
		List<Case> casePoss = new ArrayList<Case>();
		
		return casePoss;
	}
	
	
	/*
	 * Fonction qui donne la valeur v � la case c.
	 */
	public void setCase(Case c, int v){
		//to do
	}
	
	
	/*
	 * Fonction qui rend vrai si la valeur v peut �tre donn�e � la case C
	 * c'est-�-dire si la grille respecte toujours les contraintes du Sudoku.
	 */
	public boolean casePossible(Case c, int v){
		
		return true;
	}
	
	
	public void afficheGrille(){
		int v;
		int dim = (int)Math.sqrt(_taille);
		for(int i=0; i<_taille; i++){
			if(i%dim == 0){
				System.out.print(" ");
				for(int k=0; k<=_taille; k++)
					System.out.print("--");
				System.out.println();
			}
			for(int j=0; j<_taille; j++){
				if(j%dim == 0){
					System.out.print("|");
				}
				v = _cases[i][j].getVal();
				if(v == 0){
					System.out.print("  ");					
				}
				else{
					System.out.print(v + " ");
				}
			}
			System.out.println("|");
		}
		System.out.print(" ");
		for(int k=0; k<=_taille; k++)
			System.out.print("--");
		System.out.println();		
	}

	public List<Integer> getNextCoord(){
		List<Integer> res = new Stack<Integer>();
		int i = 0, j = 0;// coord of next Case to try
		while(!this.getCase(i, j).equals(0)){
			if(i == _taille){
				i=0;j++;
			}else{
				i++;
			}
		}// i and j have the coordonates
		res.add(0,i); res.add(1,j);
		return res;
	}
	
	public boolean isFinished() {
		return this.getNbCasesVides() == 0;
	}

	public Stack<Grille> possibleGrilles() {
	
		return null;
	}

}
