package view;

import javax.swing.ImageIcon;

/**
 * Torre(Class) 
 * @author Stefano91, Università degli Studi di Verona
 */
public final class Torre extends Pezzo {
	
/**
 * Costruttore Torre
 * @param colore
 */
	public Torre(ColorePezzo colore) {
		super(
			new ImageIcon(colore == ColorePezzo.BIANCO ? Torre.class.getResource("/tb.gif") : Torre.class.getResource("/tn.gif")), 
			colore
		);
	}

/**
 * Istanza a Torre copia costruttore
 * @param pezzo riferimento vecchio per farne una copia
 */
	public Torre(Pezzo pezzo) {
		super(pezzo);
	}
	
	@Override
	public TipoPezzo ottieneTipoPezzo() {
		return TipoPezzo.TORRE;
	}
}