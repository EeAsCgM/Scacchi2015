package view;

import javax.swing.ImageIcon;

/**
 * Regina(Class) 
 * @author Stefano91, Università degli Studi di Verona
 */
public final class Regina extends Pezzo {

/**
 * Costruttore Regina
 * @param colore
 */
	public Regina(ColorePezzo colore) {
		super(
			new ImageIcon(colore == ColorePezzo.BIANCO ? Regina.class.getResource("/regb.gif") : Regina.class.getResource("/regn.gif")), 
			colore
		);
	}

/**
 * Istanza a Regina copia costruttore
 * @param pezzo riferimento vecchio per farne una copia
 */
	public Regina(Pezzo pezzo) {
		super(pezzo);
	}
	
    @Override
	public TipoPezzo ottieneTipoPezzo() {
		return TipoPezzo.REGINA;
	}
}