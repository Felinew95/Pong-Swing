package moteur;

public class Raquette extends Point {
   
    // Attributs 
    private int dx = 10;

    /**
     * Constructeur de la classe Raquette
     * @param x : position en abscisse 
     * @param y : position en ordonnée
     */
    public Raquette(int x, int y) {
        super(x, y);
    }

    /**
     * Getter de dx
     * @return La vitesse en x
     */
    public int getDx() {
        return dx;
    }

    /**
     * Setter de dx
     * @param dx : Nouvelle vitesse en x
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

}
