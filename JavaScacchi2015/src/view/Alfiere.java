package view;

import javax.swing.ImageIcon;

/**
 * Alfiere (Class) 
 * @author Stefano91, Università degli Studi di Verona
 */
public final class Alfiere extends Pezzo {

/**
 * Costruttore Alfiere
 * @param colore
 */
	public Alfiere(ColorePezzo colore) {
		super(
			new ImageIcon(colore == ColorePezzo.BIANCO ? Alfiere.class.getResource("/ab.gif") : Alfiere.class.getResource("/an.gif")), 
			colore
		);
	}

/**
 * Istanza a Alfiere copia costruttore
 * @param pezzo riferimento vecchio per farne una copia
 */
	public Alfiere(Pezzo pezzo) {
		super(pezzo);
	}
	
	@Override
	public TipoPezzo ottieneTipoPezzo() {
		return TipoPezzo.ALFIERE;
	}
}