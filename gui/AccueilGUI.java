package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static config.ConfigJeu.LONGUEUR_FENETRE_TITRE;
import static config.ConfigJeu.LARGEUR_FENETRE_TITRE;
import static config.ConfigJeu.NOM_JEU;

import moteur.Balle;

/**
 * Affichage de départ
 * @author Felinew95
 * @version 1.0
 */
public class AccueilGUI extends JFrame implements Runnable {
    
    // Attributs 
    private AccueilGUI instance = this;

    private JPanel panelTitre;
    private JPanel panelStart;
    private AireJeuTitre panelBalle;

    private Balle balle;
    private boolean lancerJeu = false;
    private Random generateur = new Random();
    
    /**
     * Constructeur de la classe AccueilGUI
     * @param titre : Titre de la fenêtre 
     */
    public AccueilGUI(String titre) {
        super(titre);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        
        // Affichage du titre
        this.affichageTitre();

        // Affichage du bouton start 
        this.affichageStart();

        // Affichage de la zone de la balle
        this.affichageZoneBalle();

        // Affichage 
        this.affichage();
    }

    /**
     * Affichage du titre
     */
    private void affichageTitre() {
        this.panelTitre = new JPanel();
        this.panelTitre.setLayout(new FlowLayout());
        this.panelTitre.setBackground(Color.LIGHT_GRAY);
        this.panelTitre.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK));

        JLabel labelNomJeu = new JLabel(NOM_JEU);
        labelNomJeu.setFont(new Font(Font.SERIF, Font.CENTER_BASELINE, 80));
        this.panelTitre.add(labelNomJeu);
    }

    /**
     * Affichage de la zone de la balle 
     */
    private void affichageZoneBalle() {
        this.balle = new Balle(200, 200);
        this.balle.setDx(this.generateur.nextInt(1, 5) * (this.generateur.nextBoolean() ? 1 : -1));
        this.balle.setDy(this.generateur.nextInt(1, 5) * (this.generateur.nextBoolean() ? 1 : -1));

        this.panelBalle = new AireJeuTitre(balle);
        this.panelBalle.setBackground(Color.WHITE);
        this.panelBalle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Affichage du bouton start
     */
    private void affichageStart() {
        this.panelStart = new JPanel();
        this.panelStart.setLayout(new FlowLayout());
        this.panelStart.setBackground(Color.LIGHT_GRAY);
        this.panelStart.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK));
        
        JButton boutonStart = new JButton("Start");
        boutonStart.setFont(new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 30));
        boutonStart.addActionListener(new LancerJeu());
        this.panelStart.add(boutonStart);
    }

    /**
     * Affichage graphique
     */
    private void affichage() {
        this.add(panelTitre, BorderLayout.NORTH);
        this.add(panelStart, BorderLayout.SOUTH);
        this.add(panelBalle, BorderLayout.CENTER);

        this.setSize(600, 600);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * La balle rebondit lorsque le jeu se lance
     */
    @Override
    public void run() {
        
        while (!lancerJeu) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (balle.getX() >= LONGUEUR_FENETRE_TITRE) {
                this.balle.setDx(this.generateur.nextInt(-10, -1));
            }

            if (balle.getX() <= 0) {
                this.balle.setDx(this.generateur.nextInt(1, 10));
            }

            if (balle.getY() >= LARGEUR_FENETRE_TITRE) {
                this.balle.setDy(this.generateur.nextInt(-10, -1));
            } 

            if (balle.getY() <= 0) {
                this.balle.setDy(this.generateur.nextInt(1, 10));
            }

            this.majValeurs();
            this.panelBalle.repaint();
        }

    }

    /**
     * Mise à jour des valeurs
     */
    private void majValeurs() {
        balle.setX(balle.getX() + balle.getDx());
        balle.setY(balle.getY() + balle.getDy());
    }

    /**
     * Classe interne qui permet de lancer le jeu 
     * @author Felinew95
     * @version 1.0
     */
    class LancerJeu implements ActionListener {

        /**
         * Lance le jeu 
         * @param e : Action déclenché par le joueur 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            lancerJeu = true;
            JeuGUI jeu = new JeuGUI(NOM_JEU);

            Thread threadJeu = new Thread(jeu);
            
            instance.dispose();
            threadJeu.start();
        }
        
    }

}
