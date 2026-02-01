package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import moteur.Balle;
import moteur.Raquette;

import static config.ConfigJeu.LONGUEUR_BALLE;
import static config.ConfigJeu.LONGUEUR_RAQUETTE;
import static config.ConfigJeu.LARGEUR_BALLE;
import static config.ConfigJeu.LARGEUR_RAQUETTE;

/**
 * Classe qui crée l'aire de jeu 
 * @author Felinew95
 * @version 1.0
 */
public class AireJeu extends JPanel {
    
    // Attributs 
    private Balle balle;
    private Raquette raquette1;
    private Raquette raquette2;

    /**
     * Constructeur de la classe AireJeu
     * @param balle : Une balle
     * @param raquette1 : Raquette du joueur 1
     * @param raquette2 : Raquette du joueur 2
     */
    public AireJeu(Balle balle, Raquette raquette1, Raquette raquette2) {
        this.balle = balle;
        this.raquette1 = raquette1;
        this.raquette2 = raquette2;
    }

    /**
     * Getter de balle
     * @return La balle
     */
    public Balle getBalle() {
        return balle;
    }

    /**
     * Getter de raquette1
     * @return La raquette du joueur 1
     */
    public Raquette getRaquette1() {
        return raquette1;
    }

    /**
     * Getter de raquette2
     * @return La raquette du joueur 2
     */
    public Raquette getRaquette2() {
        return raquette2;
    }

    /**
     * Dessine les éléments 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Composants 
        g.setColor(Color.ORANGE);
        if (balle != null) {
            g.fillOval(balle.getX(), balle.getY(), LONGUEUR_BALLE, LARGEUR_BALLE);
            g.setColor(Color.BLACK);
            g.drawOval(balle.getX(), balle.getY(), LONGUEUR_BALLE, LARGEUR_BALLE);
        }

        g.setColor(Color.RED); 
        if (raquette1 != null) {
            g.fillRect(raquette1.getX(), raquette1.getY(), LONGUEUR_RAQUETTE, LARGEUR_RAQUETTE);
            g.setColor(Color.BLACK);
            g.drawRect(raquette1.getX(), raquette1.getY(), LONGUEUR_RAQUETTE, LARGEUR_RAQUETTE);
        }

        g.setColor(Color.BLUE); 
        if (raquette2 != null) {
            g.fillRect(raquette2.getX(), raquette2.getY(), LONGUEUR_RAQUETTE, LARGEUR_RAQUETTE);
            g.setColor(Color.BLACK);
            g.drawRect(raquette2.getX(), raquette2.getY(), LONGUEUR_RAQUETTE, LARGEUR_RAQUETTE);
        }
    }

}
