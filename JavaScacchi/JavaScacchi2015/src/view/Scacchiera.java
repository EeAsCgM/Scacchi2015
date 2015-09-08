 package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import controller.ScacchiController;
import model.Model.Messaggio;
import model.Model.TipoPezzo;
import model.ScacchiModel;


public final class Scacchiera extends JFrame {
	private ScacchiModel scacchiModel;
	private Controller scacchiController;	
	private JPanel panel;

	public final static int FINESTRAX = 560;
	public final static int FINESTRAY = 606;
	public final static String CELLACHIARA = "#CD853F";
	public final static String CELLASCURO = "#8B4513";
	public final static String SELEZIONE = "#F5DF16";
	public final static String PERCORSOC = "#FF670F";
	public final static String PERCORSOS = "#FF670F";
	public final static String CATTURAC = "#FF1919";
	public final static String CATTURAS = "#FF1919";
	

	public static void main(String[] args) {
        new Scacchiera().setVisible(true);
	}

	public Scacchiera() {
		super("Gioco degli Scacchi");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				if (JOptionPane.showConfirmDialog(null, 
            			"Clicca SÌ per iniziare una nuova partita, altrimenti clicca NO o la X per uscire", 
            			"Nuova Partita?", 
            			JOptionPane.YES_NO_OPTION, 
            			JOptionPane.QUESTION_MESSAGE
            		) == JOptionPane.YES_OPTION)
				{
                	scacchiModel.resetModel();
                	designChessBoard(true);
            	}
				else
					System.exit(0);
			}
		});
		setSize(new Dimension(FINESTRAX, FINESTRAY)); 
		setLocationRelativeTo(null);
		setResizable(false);
		scacchiModel = new ScacchiModel();
		scacchiController = new ScacchiController(scacchiModel);
		inizializzazioneScacchiera(true);
		setTitle("Gioco degli Scacchi (" + scacchiModel.ottengoMessaggio().toString().toUpperCase().replace('_', ' ') + ")");
	}

/**
 * Mostra una finestra di dialogo chiedendo all'utente quale pezzo vorrebbe per promuovere
 * @return il TipoPezzo da promuovere.
 */
	public TipoPezzo pedonePromozionale() {
		Object[] scelte = { "Alfiere", "Cavallo", "Regina", "Torre" };
		String messaggioPromozionale = (String)JOptionPane.showInputDialog(this, "Scegliere un pezzo da scambiare con il pedone:", "Attenzione!", JOptionPane.PLAIN_MESSAGE, null, scelte, "Alfiere");
		if (messaggioPromozionale != null)
			return TipoPezzo.valueOf(messaggioPromozionale.toUpperCase());
		else
			return TipoPezzo.REGINA;
	}

/**
 * Avvisa al giocatore opposto, dopo la fine di una mossa, se il re è sotto scacco.
 */	
	public void avvisoScacco() {
		JOptionPane.showMessageDialog(null, scacchiModel.ottengoMessaggio() == Messaggio.TURNO_DEI_NERI ?
			"RE NERO è sotto scacco!" : "RE BIANCO è sotto scacco!!", 
			"Re sotto scacco!", JOptionPane.WARNING_MESSAGE
		);
	}
	
/**
 * Avvisa al giocatore opposto, dopo la fine di una mossa, se il re è in scacco matto.
 */	
	public void avvisoScaccoMatto() {
		JOptionPane.showMessageDialog(null, scacchiModel.ottengoMessaggio() == Messaggio.VITTORIA_BIANCHI ?
			"SCACCOMATTO!\nI BIANCHI vincono la partita!" : "SCACCOMATTO!\nI NERI vincono la partita!", 
			"SCACCOMATTO!", JOptionPane.INFORMATION_MESSAGE
		);
	}
	
/**
 * Ridisegna la scacchiera
 * @param resetColori se la combinazione dei colori della scacchiera deve essere resettato
 */
	public void designChessBoard(boolean resetColori) {
		remove(panel);
		inizializzazioneScacchiera(resetColori);
		revalidate();
	}

/**
 * Ridisegna la scacchiera senza ripristinare lo schema dei colori
 */
	public void designChessBoard() {
		designChessBoard(false);
	}
	
	private void inizializzazioneScacchiera(boolean resetColori) {
		panel = new JPanel(new GridLayout(8, 8)) { public final Dimension getPreferredSize() {
			if (getParent().getWidth() > getParent().getHeight())
				return new Dimension(getParent().getHeight(), getParent().getHeight());
			else
				return new Dimension(getParent().getWidth(), getParent().getWidth());
		}};
		int i = 0;
		for (Pezzo[] pezzi : scacchiModel.ottengoPezzi()) {
			int j = 0;
			for (Pezzo pezzo : pezzi) {
				if ((i + j) % 2 == 0)
					if (pezzo.pezzoDaMangiare() && !resetColori)
						pezzo.setBackground(Color.decode(CATTURAC));
					else if (pezzo.isWaitingMove() && !resetColori)
						pezzo.setBackground(Color.decode(SELEZIONE));
					else if (pezzo.PercorsoPezzo() && !resetColori)
						pezzo.setBackground(Color.decode(PERCORSOC));
					else
						pezzo.setBackground(Color.decode(CELLACHIARA));
				else
					if (pezzo.pezzoDaMangiare() && !resetColori)
						pezzo.setBackground(Color.decode(CATTURAS));
					else if (pezzo.isWaitingMove() && !resetColori)
						pezzo.setBackground(Color.decode(SELEZIONE));
					else if (pezzo.PercorsoPezzo() && !resetColori)
						pezzo.setBackground(Color.decode(PERCORSOS));
					else
						pezzo.setBackground(Color.decode(CELLASCURO));
				final Point chessBoardLocation = new Point(i, j);
				final Scacchiera caller = this;
				if (pezzo.getActionListeners().length > 0)
					pezzo.removeActionListener(pezzo.getActionListeners()[0]);
				pezzo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				        scacchiController.onClick(caller, chessBoardLocation);
					}
				});
				pezzo.setFocusable(false);
				panel.add(pezzo);
				j++;
			}
			i++;
		}
		setLayout(new GridBagLayout());
		add(panel, new GridBagConstraints());
	}
}