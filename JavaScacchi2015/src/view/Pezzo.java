package view;

import java.awt.Point;
import javax.swing.Icon;
import javax.swing.JButton;
import model.Model;



public abstract class Pezzo extends JButton implements Model {
	private final ColorePezzo colore;
	private Point posizionePezzo;
	private boolean spostato;
	private boolean percorsoPezzo;
	private boolean pezzoDaMangiare;
	private boolean attesa;

	protected Pezzo(Icon immagine) {
		this(immagine, ColorePezzo.VUOTO);
	}

	protected Pezzo(Icon immagine, ColorePezzo colore) {
		super(immagine);
		setBorderPainted(false);
		this.colore = colore;
	}

	protected Pezzo(Pezzo pezzo) {
		this.colore = pezzo.colore;
		this.spostato = pezzo.spostato;
		this.percorsoPezzo = pezzo.percorsoPezzo;
		this.pezzoDaMangiare = pezzo.pezzoDaMangiare;
	}
	
    @Override	
	public final Point ricevePosizionePezzoScacchiera() {
		return posizionePezzo;
	}
	
    @Override
	public final void impostoPosizionePezzoScacchiera(Point posizionePezzo) {
		this.posizionePezzo = posizionePezzo;
	}
	
    @Override
	public final ColorePezzo ottieneColorePezzo() {
		return this.colore;
	}
	
    @Override
	public final boolean pezzoSpostato() {
		return spostato;
	}
	
    @Override
	public final void impostatoComeSpostato() {
		spostato = true;
	}
	
    @Override
	public final boolean PercorsoPezzo() {
		return percorsoPezzo;
	}
	
    @Override
	public final void impostoPercorsoPezzo(boolean percorsoPezzo) {
		this.percorsoPezzo = percorsoPezzo;
			
	}
	
    @Override
	public final boolean pezzoDaMangiare() {
		return pezzoDaMangiare;
	}
	
    @Override
	public final void impostoPezzoDaMangiare(boolean pezzoDaMangiare) {
		this.pezzoDaMangiare = pezzoDaMangiare;
	}
	
	/**
	 * Restituisce se l'istanza corrente del pezzo è focalizzata dai giocatori.
	 * @return se l'istanza corrente del pezzo è focalizzata dai giocatori.
	 */
	public final boolean isWaitingMove() {
		return attesa;
	}
	
	/**
	 * Imposta se l'istanza corrente del pezzo è focalizzata dai giocatori
	 * 
	 * @param attesa specifica se l'istanza corrente del pezzo è focalizzata dai giocatori
	 */
	public final void setWaitingMove(boolean attesa) {
		this.attesa = attesa;
	}
	
    @Override
	public boolean equals(Object other) {
		if (other instanceof Pezzo && ((Pezzo)other).ricevePosizionePezzoScacchiera().equals(this))
			return true;
		return false;
	}
}