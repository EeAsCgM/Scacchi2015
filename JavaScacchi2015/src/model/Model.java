package model;

import java.awt.Point;

/**
 * Model (Interface)
 * @author Stefano91, Università degli Studi di Verona
 */
public interface Model {

/**
 * ColorePezzo (enum)
 */
	public enum ColorePezzo {
		BIANCO, NERO, VUOTO
	}

/**
 * Messaggio (enum)
 */
	public enum Messaggio {
		ASPETTANDO_MOSSA_DEI_BIANCHI, ASPETTANDO_MOSSA_DEI_NERI, VITTORIA_BIANCHI, VITTORIA_NERI, TURNO_DEI_BIANCHI, TURNO_DEI_NERI
	}

/**
 * TipoPezzo (enum)
 */
	public enum TipoPezzo {
		ALFIERE, CAVALLO, CELLA_VUOTA, PEDONE, RE, REGINA, TORRE
	}

/**
 * ottieneTipoPezzo è una funzione dove mi ritorna il tipo 
 * del pezzo che ho dichiarato nella classe enum TipoPezzo. 
 * @return tipo del pezzo.
 */
    public TipoPezzo ottieneTipoPezzo();

/**
 * ottieneColorePezzo è una funzione dove mi ritorna il colore 
 * del pezzo che ho dichiarato nella classe enum ColorePezzo. 
 * @return colore del pezzo.
 */
    public ColorePezzo ottieneColorePezzo();

/**
 * pezzoSpostato è una funzione booleana che mi indica se il 
 * pezzo è stato spostato o no.
 * @return se il pezzo è stato spostato.  
 */
    public boolean pezzoSpostato();

/**
 * impostatoComeSpostato è una funzione dove imposto il pezzo come
 * spostato con proprietà vero.
 */
    public void impostatoComeSpostato();
    
/**
 * PercorsoPezzo è una funzione booleana che mi indica se il 
 * pezzo è nel giusto percorso.
 * @return se è nel giusto percorso.
 */
    public boolean PercorsoPezzo();

/**
 * impostoPercorsoPezzo è una funzione dove imposto il percorso del pezzo.
 * @param percorsoPezzo il dominio del pezzo.
 */
    public void impostoPercorsoPezzo(boolean percorsoPezzo);

/**
 * pezzoDaMangiare è una funzione booleana dove mi ritorna 
 * se il pezzo è da mangiare o no. 
 * @return se il pezzo è catturabile.
 */
    public boolean pezzoDaMangiare();

/**
 * impostoPezzoDaMangiare è una funzioe dove imposto il dominio di cattura  
 * del pezzo.
 * @param pezzoDaMangiare dominio di cattura del pezzo.
 */
    public void impostoPezzoDaMangiare(boolean pezzoDaMangiare);

/**
 * E' una funzione in cui mi ritorna la posizione del pezzo nella scacchiera. 
 * @return posizione del pezzo nella scacchiera.
 */
    public Point ricevePosizionePezzoScacchiera();

/**
 * E' una funzione in cui imposto la posizione del pezzo nella scacchiera. 
 * @param posizionePezzo indica la nuova posizione del pezzo nella scacchiera. 
 */
    public void impostoPosizionePezzoScacchiera(Point posizionePezzo);
}
