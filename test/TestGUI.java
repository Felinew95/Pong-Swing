package test;

import static config.ConfigJeu.NOM_JEU;

import gui.AccueilGUI;

/**
 * Lance le jeu 
 * @author Felinew95
 * @version 1.0
 */
public class TestGUI {

    /**
     * Lance le jeu
     * @param args : Arguments 
     */
    public static void main(String[] args) {
        AccueilGUI affichage = new AccueilGUI(NOM_JEU);

        Thread runAffichage = new Thread(affichage);
        runAffichage.start();
    }

}
