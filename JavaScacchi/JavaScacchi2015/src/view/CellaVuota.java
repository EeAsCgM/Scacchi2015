package view;

import javax.swing.ImageIcon;

/**
 * CellaVuota(Class) 
 * @author Stefano91, Università degli Studi di Verona
 */
public final class CellaVuota extends Pezzo {

/**
 * Costruttore CellaVuota
 */
	public CellaVuota() {
		super(new ImageIcon());
	}
	
/**
 * Istanza a CellaVuota copia costruttore
 * @param pezzo riferimento vecchio per farne una copia
 */
	public CellaVuota(Pezzo pezzo) {
		super(pezzo);
	}
	
	@Override
	public TipoPezzo ottieneTipoPezzo() {
		return TipoPezzo.CELLA_VUOTA;
	}
}