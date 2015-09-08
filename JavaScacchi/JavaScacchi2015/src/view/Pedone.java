package view;

import javax.swing.ImageIcon;

/**
 * Pedone(Class) 
 * @author Stefano91, Università degli Studi di Verona
 */
public final class Pedone extends Pezzo {

/**
 * Costruttore Pedone
 * @param colore
 */
	public Pedone(ColorePezzo colore) {
		super(
			new ImageIcon(colore == ColorePezzo.BIANCO ? Pedone.class.getResource("/pb.gif") : Pedone.class.getResource("/pn.gif")), 
			colore
		);
	}

/**
 * Istanza a Pedone copia costruttore
 * @param pezzo riferimento vecchio per farne una copia
 */
	public Pedone(Pezzo pezzo) {
		super(pezzo);
	}
	
	@Override
	public TipoPezzo ottieneTipoPezzo() {
		return TipoPezzo.PEDONE;
	}
}