/*
 * TP 1 - IA Informatique : Sudoku
 * 
 * @author Tassadit BOUADI.
 */
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;

public class Sudoku {
	public static int TAILLE = 9;

	public static void main(String[] args) {
		int[][] grille1 = { 
				{ 0, 8, 0, 4, 0, 2, 0, 6, 0 },
				{ 0, 3, 4, 0, 0, 0, 9, 1, 0 }, 
				{ 9, 6, 0, 0, 0, 0, 0, 8, 4 },
				{ 0, 0, 0, 2, 1, 6, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, 5, 7, 0, 0, 0 }, 
				{ 8, 4, 0, 0, 0, 0, 0, 7, 5 },
				{ 0, 2, 6, 0, 0, 0, 1, 3, 0 }, 
				{ 0, 9, 0, 7, 0, 1, 0, 4, 0 } };

		// initialisation
		Grille grilleInit1 = new Grille(TAILLE, grille1);
		grilleInit1.afficheGrille();

		Stack<Grille> pile = new Stack<Grille>();
		pile.push(grilleInit1);

		boolean resul = resoudreSudoku(pile);

		if (resul) {
			System.out.println("La grille a �t� r�solue");
			Grille grilleResul = pile.peek();
			grilleResul.afficheGrille();
		} else {
			System.out.println("Aucune solution n'a �t� trouv�e");
			Grille grilleResul = pile.peek();
			grilleResul.afficheGrille();
		}
	}// main
	
	public static boolean resoudreSudokuRec(Stack<Grille> pileGrilles){
		Stack<Grille> nextGrilles = new Stack<Grille>();
		while(!pileGrilles.isEmpty()){
			Grille curr = pileGrilles.pop();
			if(curr.isFinished()){
				pileGrilles.add(curr);
				return true;
			}else{
				nextGrilles.addAll(curr.possibleGrilles());
			}
		}
		if(nextGrilles.isEmpty()) return false;
		return resoudreSudokuRec(nextGrilles);
	}
	
	/*
	 * Fonction r�cursive qui recherche la solution, en utilisant �ventuellement
	 * des retours-arri�re.
	 */
	public static boolean resoudreSudoku(Stack<Grille> pileGrilles) {
		return resoudreSudokuRec(pileGrilles);
	}// resoudreSudoku

}
