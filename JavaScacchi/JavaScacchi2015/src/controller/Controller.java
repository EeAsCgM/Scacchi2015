package controller;

import java.awt.Point;
import view.Scacchiera;


/**
 * Controller (Interface)
 * @author Stefano91, Università degli Studi di Verona
 */
public interface Controller {
    /**
     * onClick è un costrutto che permette la gestione degli eventi
     * sulla Scacchiera e sui Pezzi appartenenti a essa. 
     * @param chiamata puntatore chiamante.
     * @param cliccato puntatore al pezzo cliccato, rappresentato come un punto di coordinate x e y nello spazio.
     */
    void onClick(Scacchiera chiamata, Point cliccato);
}
