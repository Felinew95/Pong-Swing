package moteur;

public class Point {
    
    // Attributs 
    private int x;
    private int y;

    public Point(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Getter de x
     * @return L'abscisse 
     */
    public int getX() {
        return x;
    }

    /**
     * Getter de y
     * @return L'ordonnée
     */
    public int getY() {
        return y;
    }

    /**
     * Setter de x
     * @param x : Nouvelle valeur de l'abscisse
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter de y
     * @param y : Nouvelle valeur de l'ordonnée
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Affiche les coordonnées de la balle
     * @return l'affichage 
     */
    @Override
    public String toString() {
        return "Coordonnées : ("+this.getX()+", "+this.getY()+")";
    }

}
