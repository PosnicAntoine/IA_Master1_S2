/*
 * TP 3 : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Programme principal du jeu du puissance 4.
 * 
 */
public class Puissance4 {

	public static void main(String[] args) {
		//cr�ation des joueurs et appel de la fonction jouer
		Joueur joueur1 = new JoueurMinMax();
		Joueur joueur2 = new JoueurHumain();
		
		jouer(joueur1, joueur2);
	}
	
	
	/**
	 * Fonction qui effectue la boucle de jeu.
	 * 
	 * @param joueur1 : le premier joueur.
	 * @param joueur2 : le second joueur.
	 */
	public static void jouer(Joueur joueur1, Joueur joueur2){
		Resultat res;
		int coup;
		Grille grille = new Grille();
		
		Joueur joueurCour = joueur1;	
		int numJoueur = Grille.JOUEUR1; //le joueur 1 commence � jouer
		
		int vainqueur = 0;//match nul
		boolean jeuFini = false;
		
		
		//boucle de jeu
		while(!jeuFini){
			//affichage de la grille 
			System.out.println(grille);
			
			
			//faire jouer le joueur courant et passer au suivant
			

			if(grille.estPleine()){
				jeuFini = true;
				break;
			}
			
			res= joueurCour.coup(grille, numJoueur);
			if(grille.coupGagnant(numJoueur, res.getColonne())){
				vainqueur = numJoueur;
				grille.joueEn(numJoueur, res.getColonne());
				jeuFini = true;
				break;
			}
			
			grille.joueEn(numJoueur, res.getColonne());
			
			if(numJoueur == Grille.JOUEUR1){
				joueurCour = joueur2; 
				numJoueur = Grille.JOUEUR2;
			}else{
				joueurCour = joueur1; 
				numJoueur = Grille.JOUEUR1;
			}
			
		}//while - boucle de jeu
		
		//affichage de la grille 
		System.out.println(grille);
		
		
		//affichage du vainqueur
		switch(vainqueur){
			case Grille.JOUEUR1:
				System.out.println("Victoire du joueur 1");
				break;
			case Grille.JOUEUR2:
				System.out.println("Victoire du joueur 2");
				break;
			default:
				System.out.println("Match nul");
		}
	}

}
