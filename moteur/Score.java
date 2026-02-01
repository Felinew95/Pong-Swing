package moteur;

/**
 * Classe qui représente un score au jeu
 * @author Felinew95
 * @version 1.0
 */
public class Score {
    
    // Attributs 
    private int scoreJoueur1;
    private int scoreJoueur2;
    private int scoreMax;

    /**
     * Constructeur de la classe Score
     */
    public Score(int scoreMax) {
        this.setScoreJoueur1(0);
        this.setScoreJoueur2(0);
        this.setScoreMax(scoreMax);
    }

    /**
     * Incrémente le score du joueur 1
     */
    public void incrementerScoreJoueur1() {
        this.scoreJoueur1++;
    }

    /**
     * Incrémente le score du joueur 2
     */
    public void incrementerScoreJoueur2() {
        this.scoreJoueur2++;
    }

    /**
     * Reinitialise le score 
     */
    public void resetScore() {
        this.setScoreJoueur1(0);
        this.setScoreJoueur2(0);
    }

    /**
     * Getter de scoreJoueur1
     * @return Retourne le score du joueur 1
     */
    public int getScoreJoueur1() {
        return scoreJoueur1;
    }

    /**
     * Getter de scoreJoueur2
     * @return Retourne le score du joueur 2
     */
    public int getScoreJoueur2() {
        return scoreJoueur2;
    }

    /**
     * Getter de scoreMax
     * @return Retourne le score max
     */
    public int getScoreMax() {
        return scoreMax;
    }

    /**
     * Setter de scoreJoueur1
     * @param scoreJoueur1 : Nouveau score du joueur 1
     */
    public void setScoreJoueur1(int scoreJoueur1) {
        this.scoreJoueur1 = scoreJoueur1;
    }

    /**
     * Setter de scoreJoueur2
     * @param scoreJoueur2 : Nouveau score du joueur 2
     */
    public void setScoreJoueur2(int scoreJoueur2) {
        this.scoreJoueur2 = scoreJoueur2;
    }

    /**
     * Setter de scoreMax
     * @param scoreMax : Score maximum
     */
    private void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    /**
     * Vérifie si le joueur 1 a gagné
     * @return true si le joueur 1 a gagné, false sinon
     */
    public boolean joueur1AGagne() {
        return scoreJoueur1 >= scoreMax;
    }
    
    /**
     * Vérifie si le joueur 2 a gagné
     * @return true si le joueur 2 a gagné, false sinon
     */
    public boolean joueur2AGagne() {
        return scoreJoueur2 >= scoreMax;
    }

    /**
     * Affichage du score
     * @return L'affichage 
     */
    @Override
    public String toString() {
        return "Joueur 1 : "+this.getScoreJoueur1()+", Joueur 2 : "+this.getScoreJoueur2();
    }

}
