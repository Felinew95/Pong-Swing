package moteur;

/**
 * Classe qui représente une balle 
 * @author Felinew95
 * @version 1.0
 */
public class Balle extends Point {
    
    // Attributs 
    private int dx;
    private int dy;

    /**
     * Constructeur de la classe Balle
     * @param x : position en abscisse 
     * @param y : position en ordonnée
     */
    public Balle(int x, int y) {
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
     * Getter de dy
     * @return La vitesse en y
     */
    public int getDy() {
        return dy;
    }

    /**
     * Setter de dx
     * @param dx : Nouvelle vitesse en x
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * Setter de dy
     * @param dy : Nouvelle valeur en y
     */
    public void setDy(int dy) {
        this.dy = dy;
    }
    
}

