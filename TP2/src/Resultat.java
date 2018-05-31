/*
 * TP 3  : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Résultat composé de la valeur de la grille 
 * si on joue dans la colonne donnée.
 * 
 */
public class Resultat{
	private int _colonne;
	private double _valeur;
	
	public Resultat(){
		_colonne = -1;
		_valeur = 0;
	}
	
	public Resultat(int c, double v){
		_colonne = c;
		_valeur = v;
	}
	
	public Resultat(Resultat r){
		_colonne = r._colonne;
		_valeur = r._valeur;
	}
	
	public Resultat copie(){
		return new Resultat(_colonne, _valeur);
	}
	
	public int getColonne(){
		return _colonne;
	}
	
	public double getValeur(){
		return _valeur;
	}
	
	public void setColonne(int c){
		_colonne = c;
	}
	
	public void setValeur(double v){
		_valeur = v;
	}
	
	public String toString(){
		return "(" + _colonne + ":" + _valeur + ")";
	}
}
