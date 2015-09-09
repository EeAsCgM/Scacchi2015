package view;

import javax.swing.ImageIcon;

/**
 * Re(Class) 
 * @author Stefano91, Università degli Studi di Verona
 */
public final class Re extends Pezzo {

/**
 * Costruttore Re
 * @param colore
 */
	public Re(ColorePezzo colore) {
		super(
			new ImageIcon(colore == ColorePezzo.BIANCO ? Re.class.getResource("/reb.gif") : Re.class.getResource("/ren.gif")), 
			colore
		);
	}

/**
 * Istanza a Re copia costruttore
 * @param pezzo riferimento vecchio per farne una copia
 */
	public Re(Pezzo pezzo) {
		super(pezzo);
	}
	
	@Override
	public TipoPezzo ottieneTipoPezzo() {
		return TipoPezzo.RE;
	}
}