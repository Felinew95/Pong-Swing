package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import moteur.Balle;
import moteur.Raquette;
import moteur.Score;

import static config.ConfigJeu.LONGUEUR_FENETRE_JEU;
import static config.ConfigJeu.NOM_JEU;
import static config.ConfigJeu.SCORE_MAX;
import static config.ConfigJeu.LARGEUR_FENETRE_JEU;
import static config.ConfigJeu.VITESSE_MIN_BALLE;
import static config.ConfigJeu.VITESSE_MAX_BALLE;
import static config.ConfigJeu.POSITION_R1_X_INIT;
import static config.ConfigJeu.POSITION_R2_X_INIT;
import static config.ConfigJeu.POSITION_R1_Y_INIT;
import static config.ConfigJeu.POSITION_R2_Y_INIT;

/**
 * Classe graphique du jeu principal 
 * @author Felinew95
 * @version 1.0
 */
public class JeuGUI extends JFrame implements Runnable {

    // Attributs 
    private Score score;
    private Raquette raquette1;
    private Raquette raquette2;
    private Balle balle;

    private JLabel scoreJoueur1;
    private JLabel scoreJoueur2;

    private JPanel panelScore1;
    private AireJeu panelJeu;
    private JPanel panelScore2;

    private Random generateur = new Random();
    private boolean finJeu = false;

    public JeuGUI(String titre) {
        super(titre);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        // Initialisation des raquettes et balle 
        this.initVariables();

        // Affichage de la zone de jeu
        this.affichageZoneJeu();

        // Affichage du score du joueur 1
        this.affichageScoreJoueur1();

        // Affichage du score du joueur 2
        this.affichageScoreJoueur2();

        // Affichage 
        this.affichageGraphique();
    }

    /**
     * Initialisation des variables 
     */
    private void initVariables() {
        this.balle = new Balle(220 , 325);
        this.balle.setDx(this.generateur.nextInt(VITESSE_MIN_BALLE, VITESSE_MAX_BALLE) * (this.generateur.nextBoolean() ? 1 : -1));
        this.balle.setDy(this.generateur.nextInt(VITESSE_MIN_BALLE, VITESSE_MAX_BALLE) * (this.generateur.nextBoolean() ? 1 : -1));

        this.raquette1 = new Raquette(POSITION_R1_X_INIT, POSITION_R1_Y_INIT);
        this.raquette2 = new Raquette(POSITION_R2_X_INIT, POSITION_R2_Y_INIT);
        this.score = new Score(SCORE_MAX);

        KeyControlsJoueur1 keyControlsJ1 = new KeyControlsJoueur1();
        KeyControlsJoueur2 keyControlsJ2 = new KeyControlsJoueur2();
		this.addKeyListener(keyControlsJ1);
        this.addKeyListener(keyControlsJ2);
    }

    /**
     * Affichage de la zone de jeu
     */
    private void affichageZoneJeu() {
        this.panelJeu = new AireJeu(balle, raquette1, raquette2);
        this.panelJeu.setBackground(Color.DARK_GRAY);
        this.panelJeu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Affichage du score du joueur 1
     */
    private void affichageScoreJoueur1() {
        this.panelScore1 = new JPanel();
        this.panelScore1.setLayout(new FlowLayout());
        this.panelScore1.setBackground(Color.LIGHT_GRAY); 
        this.panelScore1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel labelScore1 = new JLabel("Score Joueur 1 : ");
        labelScore1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        this.panelScore1.add(labelScore1);

        this.scoreJoueur1 = new JLabel("0");
        this.scoreJoueur1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.panelScore1.add(scoreJoueur1);
    }

    /**
     * Affichage du score du joueur 2
     */
    private void affichageScoreJoueur2() {
        this.panelScore2 = new JPanel();
        this.panelScore1.setLayout(new FlowLayout());
        this.panelScore2.setBackground(Color.LIGHT_GRAY);
        this.panelScore2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel labelScore2 = new JLabel("Score Joueur 2 : ");
        labelScore2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        this.panelScore2.add(labelScore2);

        this.scoreJoueur2 = new JLabel("0");
        this.scoreJoueur2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.panelScore2.add(scoreJoueur2);
    }

    /**
     * Affichage graphique du jeu 
     */
    private void affichageGraphique() {
        this.add(this.panelScore1, BorderLayout.SOUTH);
        this.add(this.panelScore2, BorderLayout.NORTH);
        this.add(this.panelJeu, BorderLayout.CENTER);
        
        this.setSize(500, 800);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Lance le jeu 
     */
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!finJeu) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Rebond de la balle 
            if (balle.getX() >= LONGUEUR_FENETRE_JEU) {
                this.balle.setDx(this.generateur.nextInt(-VITESSE_MAX_BALLE, -VITESSE_MIN_BALLE));
            }

            if (balle.getX() <= 0) {
                this.balle.setDx(this.generateur.nextInt(VITESSE_MIN_BALLE, VITESSE_MAX_BALLE));
            }

            // Si le joueur 2 gagne un point 
            if (balle.getY() >= LARGEUR_FENETRE_JEU) {
                this.score.incrementerScoreJoueur2();
                this.scoreJoueur2.setText(String.valueOf(this.score.getScoreJoueur2()));
                this.majValeursZone();

                this.reinitValBalle();
            } 

            // Si le joueur 1 gagne un point 
            if (balle.getY() <= 0) { 
                this.score.incrementerScoreJoueur1();
                this.scoreJoueur1.setText(String.valueOf(this.score.getScoreJoueur1()));
                this.majValeursZone();

                this.reinitValBalle();
            }

            // Rebond de la balle sur les raquettes 
            if (balle.getX() >= raquette1.getX() && balle.getX() <= raquette1.getX()+90 
                && balle.getY() >= raquette1.getY()-20  && balle.getY() <= raquette1.getY() + 20) {
                this.balle.setDy(this.generateur.nextInt(-VITESSE_MAX_BALLE, -VITESSE_MIN_BALLE));
            }

            if (balle.getX() >= raquette2.getX() && balle.getX() <= raquette2.getX()+90 
                && balle.getY() <= raquette2.getY() + 30 && balle.getY() >= raquette2.getY() - 30) {
                this.balle.setDy(this.generateur.nextInt(VITESSE_MIN_BALLE, VITESSE_MAX_BALLE));
            }

            // Vérifications des scores
            this.verifScore();
        
            this.majValeurs();
            this.panelJeu.repaint();
        }
    }

    /**
     * Vérifie le score des joueurs 
     */
    private void verifScore() {
        int choix_rejouer;

        if (this.score.joueur1AGagne()) {
            this.jeuTermine();

            choix_rejouer = JOptionPane.showConfirmDialog(this, "Le joueur 1 a gagné. Voulez vous continuez ?", "Quitter", JOptionPane.YES_NO_OPTION);
            this.choixContinue(choix_rejouer);
        }

        if (this.score.joueur2AGagne()) {
            this.jeuTermine();

            choix_rejouer = JOptionPane.showConfirmDialog(this, "Le joueur 2 a gagné. Voulez  vous continuez ?", "Quitter", JOptionPane.YES_NO_OPTION);
            this.choixContinue(choix_rejouer);
        }
    }

    /**
     * Fin du jeu 
     */
    private void jeuTermine() {
        this.finJeu = true;
    }

    /**
     * Choix de continuer ou non 
     * @param choix : Choix du joueur 
     */
    private void choixContinue(int choix) {
        this.dispose();
        if (choix==JOptionPane.YES_OPTION) {
            Thread nouveau_jeu = new Thread(new AccueilGUI(NOM_JEU));
            nouveau_jeu.start();
        } else {
            JOptionPane.showMessageDialog(null, "Merci d'avoir joué");
            System.exit(0);
        }
    }

    /**
     * Reinitialise la vitesse de la balle 
     */
    private void reinitValBalle() {
        this.balle.setDy(this.generateur.nextInt(VITESSE_MIN_BALLE, VITESSE_MAX_BALLE));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mise à jour des valeurs de la zone 
     */
    private void majValeursZone() {
        this.balle.setX(220);
        this.balle.setY(325);

        this.raquette1.setX(POSITION_R1_X_INIT);
        this.raquette1.setY(POSITION_R1_Y_INIT);

        this.raquette2.setX(POSITION_R2_X_INIT);
        this.raquette2.setY(POSITION_R2_Y_INIT);

        this.balle.setDx(this.generateur.nextInt(1, 5) * (this.generateur.nextBoolean() ? 1 : -1));
        this.balle.setDy(this.generateur.nextInt(1, 5) * (this.generateur.nextBoolean() ? 1 : -1));
    }

    /**
     * Mise à jour des valeurs
     */
    private void majValeurs() {
        balle.setX(balle.getX() + balle.getDx());
        balle.setY(balle.getY() + balle.getDy());
    }
    
    /**
     * Classe qui permet de changer l'emplacement de la raquette du joueur 1 en fonction de la touche
     * @author Felinew95
     * @version 1.0
     */
    private class KeyControlsJoueur1 implements KeyListener {

        /**
         * Change l'emplacement de la raquette du joueur 1 en fonction de la touche
         * @param event : Evénement déclenché par le joueur
         */
		@Override
		public void keyPressed(KeyEvent event) {
			char keyChar = event.getKeyChar();
			switch (keyChar) {
                case 'q':
                    if (raquette1.getX() > 0) {
                        raquette1.setX(raquette1.getX() - raquette1.getDx());
                    }
                    break;
                case 'd':
                    if (raquette1.getX() < 390) {
                        raquette1.setX(raquette1.getX() + raquette1.getDx());
                    }
                    break;
                default:
                    break;
			}
		}

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
	}

    /**
     * Classe qui permet de changer l'emplacement de la raquette du joueur 2 en fonction de la touche
     * @author Felinew95
     * @version 1.0
     */
    private class KeyControlsJoueur2 implements KeyListener {

        /**
         * Change l'emplacement de la raquette du joueur 2 en fonction de la touche
         * @param event : Evénement déclenché par le joueur
         */
        @Override
        public void keyPressed(KeyEvent event) {
            char keyChar = event.getKeyChar();
			switch (keyChar) {
                case 'k':
                    if (raquette2.getX() > 0) {
                        raquette2.setX(raquette2.getX() - raquette2.getDx());
                    }
                    break;
                case 'm':
                    if (raquette2.getX() < 390) {
                        raquette2.setX(raquette2.getX() + raquette2.getDx());
                    }
                    break;
                default:
                    break;
			}
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }        
    }

}
