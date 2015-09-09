package view;

import javax.swing.ImageIcon;

/**
 * Cavallo(Class) 
 * @author Stefano91, Università degli Studi di Verona
 */
public final class Cavallo extends Pezzo {

/**
 * Costruttore Cavallo
 * @param colore
 */
	public Cavallo(ColorePezzo colore) {
		super(
			new ImageIcon(colore == ColorePezzo.BIANCO ? Cavallo.class.getResource("/cb.gif") : Cavallo.class.getResource("/cn.gif")), 
			colore
		);
	}

/**
 * Istanza a Cavallo copia costruttore
 * @param pezzo riferimento vecchio per farne una copia
 */
	public Cavallo(Pezzo pezzo) {
		super(pezzo);
	}
	
	@Override
	public TipoPezzo ottieneTipoPezzo() {
		return TipoPezzo.CAVALLO;
	}
}