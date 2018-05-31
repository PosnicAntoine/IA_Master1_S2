/*
 * TP 3  : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Interface pour la définition d'une fonction d'évaluation d'une grille.
 * 
 */
public interface FonctionEvaluation{
	public static final double MIN = Double.NEGATIVE_INFINITY;
	public static final double MAX = Double.POSITIVE_INFINITY;
	

	/**
	 * Fonction qui donne une valeur à la grille, pour le joueur,
	 * de telle façon que la valeur soit égale à MAX si joueur gagne,
	 * égale à 0 s'il y a match nul et égale à MIN si joueur perd.
	 * @param grille : la grille de puissance 4.
	 * @param joueur : le joueur qui joue le coup.
	 * @return la valeur donnée à la grille, pour le joueur.
	 */
	public double evaluation(Grille grille, int joueur);
}
