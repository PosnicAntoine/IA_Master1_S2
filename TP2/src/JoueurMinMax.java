import java.util.ArrayList;

public class JoueurMinMax implements Joueur {
	private static FonctionEvaluationProf EVAL = new FonctionEvaluationProf();
	private static int PROFONDEUR = 2; // PROFONDEUR>0
	private static int nbJoueur;
	private static int nbJoueurAdversaire;

	private static class Noeud {
		Grille grille;
		int coup;
		Double score;
		int profondeur;
		Noeud parent;
		ArrayList<Noeud> enfants;

		// /////////////////////////////////////////////////////////////////////
		Noeud(Grille grille) {
			this.grille = grille;
			if (this.profondeur == JoueurMinMax.PROFONDEUR) {
				this.score = EVAL.evaluation(this.grille, nbJoueur);
			}
			this.enfants = new ArrayList<Noeud>();
		}

		Noeud(Grille grille, int coup, Noeud parent) {
			this.grille = grille;
			this.coup = coup;
			this.parent = parent;
			if (this.profondeur == JoueurMinMax.PROFONDEUR) {
				this.score = EVAL.evaluation(this.grille, nbJoueur);
			}
			this.enfants = new ArrayList<Noeud>();
			this.profondeur = this.parent.profondeur + 1;
			this.parent.addEnfant(this);
		}

		Noeud(Grille grille, int coup, Noeud parent, Double score) {
			this.grille = grille;
			this.coup = coup;
			this.parent = parent;
			this.score = score;
			this.enfants = new ArrayList<Noeud>();
			this.profondeur = this.parent.profondeur + 1;
			this.parent.addEnfant(this);
		}

		// //////////////////////////////////////////////////////////////////
		public static int getBestCoup(Noeud root) throws Exception {
			updateScoreMinMax(root);
			java.util.Iterator<Noeud> it = root.enfants.iterator();
			if (!it.hasNext()) {
				throw new Exception(root + ": Doesn't have childs");
			}
			boolean found = false;
			Noeud currit = it.next();
			while (!found) {
				if (currit.score == root.score)
					return currit.coup;
				currit = it.next();
			}
			throw new Exception();
		}

		private static void updateScoreMinMax(Noeud root) throws Exception {
			updateScoreRec(root);
		}

		private static Double updateScoreRec(Noeud curr) throws Exception {

//			System.out.println(curr.profondeur);
			if (curr.profondeur != PROFONDEUR) {
				// SI max
				if (curr.profondeur % 2 == 0) {
					curr.score = getMAXscoreEnfants(curr);
				}
				// SINON min
				else {
					curr.score = getMINscoreEnfants(curr);
				}
			}
			return curr.score;
		}

		// /////////////////////////////////////////////////////////////////////////
		private static Double getMAXscoreEnfants(Noeud curr) throws Exception {
			if (curr.enfants.isEmpty()) {
				throw new Exception(curr + ": Doesn't have childs");
			}

			java.util.Iterator<Noeud> itverif = curr.enfants.iterator();
			while (itverif.hasNext()) {
				Noeud currverif = itverif.next();
				if (currverif.score == null) {
					updateScoreRec(currverif);
				}
			}

			// Les enfants ont des scores
			java.util.Iterator<Noeud> it = curr.enfants.iterator();
			Double res = it.next().score;

			while (it.hasNext()) {
				Double currd = it.next().score;
				if (currd > res)
					res = currd;
			}
			return res;
		}

		private static Double getMINscoreEnfants(Noeud curr) throws Exception {
			if (curr.enfants.isEmpty()) {
				throw new Exception(curr + ": Doesn't have childs");
			}

			java.util.Iterator<Noeud> itverif = curr.enfants.iterator();
			while (itverif.hasNext()) {
				Noeud currverif = itverif.next();
				if (currverif.score == null) {
					updateScoreRec(currverif);
				}
			}

			// Les enfants ont des scores
			java.util.Iterator<Noeud> it = curr.enfants.iterator();
			double res = it.next().score;

			while (it.hasNext()) {
				double currd = it.next().score;
				if (currd < res)
					res = currd;
			}
			return res;
		}

		// ///////////////////////////////////////////////////////////////////
		private void addEnfant(Noeud newEnfants) {
			this.enfants.add(newEnfants);
		}
	}

	@Override
	public Resultat coup(Grille grille, int joueur) {
		System.out.println("entrée");
		Grille currGrille = grille.copie();
		nbJoueur = joueur;
		if (nbJoueur == 1) {
			nbJoueurAdversaire = -1;
		} else {
			nbJoueurAdversaire = 1;
		}
		Noeud root = buildTree(currGrille);
		System.out.println("tree built");
		int col;

		try {
			col = Noeud.getBestCoup(root);
			System.out.println("got best move");
			return new Resultat(col, 0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Noeud buildTree(Grille grille) {
		System.out.println("building root");
		Noeud root = new Noeud(grille);
		int currProf = 0;
		buildTreeRec(root, currProf);

		return root;

	}

	private void buildTreeRec(Noeud parent, int currProf) {
		if (currProf != PROFONDEUR) {
			// System.out.println("Profondeur +1");
			int[] posibl = parent.grille.generateurCoups();
			for (int i = 0; i < posibl.length; i++) {
				Grille currGrille = parent.grille.copie();
				int currJoueur;
				if (currProf % 2 == 0) {
					currGrille.joueEn(nbJoueur, posibl[i]);
					currJoueur = nbJoueur;
				} else {
					currGrille.joueEn(nbJoueurAdversaire, posibl[i]);
					currJoueur = nbJoueurAdversaire;
				}
//				System.out.println("enfant +1");
				if (currGrille.generateurCoups().length == 0) {
					// System.out.println("enfent final");
					buildTreeRec(
							new Noeud(currGrille, posibl[i], parent,
									EVAL.evaluation(currGrille, currJoueur)),
							currProf + 1);
				} else {
					// System.out.println("enfant nonfinal");
					buildTreeRec(new Noeud(currGrille, posibl[i], parent),
							currProf + 1);
				}
			}
		}
	}

}
