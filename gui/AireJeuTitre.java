package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import moteur.Balle;

import static config.ConfigJeu.LONGUEUR_BALLE;
import static config.ConfigJeu.LARGEUR_BALLE;

/**
 * Classe qui représente la zone où la balle rebondit dans l'écran titre
 * @author Felinew95
 * @version 1.0
 */
public class AireJeuTitre extends JPanel {

    // Attributs
    private Balle balle; 

    /**
     * Constructeur de la classe AireJeuTitre
     * @param balle : Une balle 
     */
    public AireJeuTitre(Balle balle) {
        this.setBalle(balle);
    }

    /**
     * Getter de balle
     * @return La balle 
     */
    public Balle getBalle() {
        return balle;
    }

    /**
     * Setter de balle 
     * @param balle : Nouvelle balle
     */
    public void setBalle(Balle balle) {
        this.balle = balle;
    }

    /**
     * Affiche la balle à son emplacement 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.ORANGE);
        
        if (balle != null) {
            g.fillOval(balle.getX(), balle.getY(), LONGUEUR_BALLE, LARGEUR_BALLE);
            g.setColor(Color.BLACK);
            g.drawOval(balle.getX(), balle.getY(), LONGUEUR_BALLE, LARGEUR_BALLE);
        }
    }

}
