/*
 * TP 3  : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Interface d'un joueur pour choisir le coup à jouer, dans une grille.
 * 
 */
public interface Joueur{

	/**
	 * Fonction qui indique dans quelle colonne de la grille jouer.
	 * @param grille : la grille de puissance 4.
	 * @param joueur : le joueur qui doit jouer le coup.
	 * @return l'indice de la colonne dans laquelle poser le pion 
	 * ainsi que la valeur associée à la nouvelle grille.
	 */
	public Resultat coup(Grille grille, int joueur);
}
