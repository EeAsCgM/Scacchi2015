package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import view.Alfiere;
import view.Cavallo;
import view.CellaVuota;
import view.Pedone;
import view.Pezzo;
import view.Re;
import view.Regina;
import view.Torre;
import model.Model.ColorePezzo;
import model.Model.Messaggio;


/**
 * ChessModel (Class)
 * @author Stefano91, Università degli Studi di Verona
 */
public final class ScacchiModel {

    private Pezzo[][] pezzi;
    private Pezzo attesaMossa;
    private ArrayList<Pezzo> catturato;
    private Pezzo reNero;
    private Pezzo reBianco;
	private Messaggio messaggi;

    /**
     * Costruttore di default
     */
    public ScacchiModel() {
        resetModel();
    }

    /**
     * Construttore che fa la stessa copia
     * @param scacchiModel origine istanza alla classe ScacchiModel
     */
    public ScacchiModel(ScacchiModel scacchiModel) {
        pezzi = new Pezzo[8][8];
        catturato = new ArrayList<Pezzo>();
        for (int i = 0; i < 8; i++) {
            int j = 0;
            for (Pezzo pezzo : scacchiModel.pezzi[i]) {
                switch (pezzo.ottieneTipoPezzo()) {
                    case ALFIERE:
                        pezzi[i][j] = new Alfiere(pezzo);
                        break;
                    case CELLA_VUOTA:
                        pezzi[i][j] = new CellaVuota(pezzo);
                        break;
                    case RE:
                        pezzi[i][j] = new Re(pezzo);
                        if (pezzo.ottieneColorePezzo() == ColorePezzo.BIANCO) {
                            reBianco = pezzi[i][j];
                        } else if (pezzo.ottieneColorePezzo() == ColorePezzo.NERO) {
                            reNero = pezzi[i][j];
                        }
                        break;
                    case CAVALLO:
                        pezzi[i][j] = new Cavallo(pezzo);
                        break;
                    case PEDONE:
                        pezzi[i][j] = new Pedone(pezzo);
                        break;
                    case REGINA:
                        pezzi[i][j] = new Regina(pezzo);
                        break;
                    case TORRE:
                        pezzi[i][j] = new Torre(pezzo);
                }

                pezzi[i][j].impostoPosizionePezzoScacchiera(new Point(i, j));
                j++;
            }
        }
        this.messaggi = scacchiModel.messaggi;
    }

    /**
     * Ogni set di pezzi di ScacchiModel
     * @return tutti i pezzi
     */
    public Pezzo[][] ottengoPezzi() {
        return pezzi;
    }

    /**
     * Pezzi di ScacchiModel come lista in un specifico set di colore
     * @param colore imposto il colore specifico da ritornare.
     * @return Pezzi di ScacchiModel come lista in un specifico set di colore
     */
    public List<Pezzo> ottengoPezzi(ColorePezzo colore) {
        ArrayList<Pezzo> colorePezzi = new ArrayList<Pezzo>();
        for (Pezzo[] pezzi : this.pezzi) {
            for (Pezzo pezzo : pezzi) {
                if (pezzo.ottieneColorePezzo() == colore) {
                    colorePezzi.add(pezzo);
                }
            }
        }
        return colorePezzi;
    }

    /**
     * Percorso dei pezzi di ScacchiModel come lista in uno specifico set di colore.
     * @param colore imposto il colore specifico da ritornare.
     * @return Percorso dei pezzi di ScacchiModel come lista in uno specifico set di colore.
     */
    public List<Pezzo> ottengoPercorsoPezzi(ColorePezzo colore) {
        ArrayList<Pezzo> percorsoPezzi = new ArrayList<Pezzo>();
        for (Pezzo[] pezzi : this.pezzi) {
            for (Pezzo pezzo : pezzi) {
                if (pezzo.PercorsoPezzo()) {
                    percorsoPezzi.add(pezzo);
                }
            }
        }
        return percorsoPezzi;
    }

    /**
     * Puntatore re del colore specificato.
     * @param colore il colore del re da ritornare come puntatore.
     * @return Puntatore re del colore specificato.
     */
    public Pezzo ottengoRe(ColorePezzo colore) {
        if (colore == ColorePezzo.BIANCO) {
            return reBianco;
        } else if (colore == ColorePezzo.NERO) {
            return reNero;
        }
        return new CellaVuota();
    }

    /**
     * Pezzo da punto specifico.
     * @param point Point puntatore  ScacchiModel coordinate.
     * @return Pezzo da punto specifico.
     */
    public Pezzo ottengoPezzo(Point point) {
        if (point.x < 8 && point.x >= 0 && point.y < 8 && point.y >= 0) {
            return pezzi[point.x][point.y];
        } else {
            return new CellaVuota();
        }
    }
    
    /**
     * Restituisce messaggi ScacchiModel.
     * @return messaggi ScacchiModel.
     */
    public Messaggio ottengoMessaggio() {
        return messaggi;
    }

    /**
     * Imposta messaggi ScacchiModel.
     * @param messaggi new Messaggio.
     */
    public void impostoMessaggio(Messaggio messaggi) {
        this.messaggi = messaggi;
    }

    /**
     * Imposta il pezzo dal punto specifico.
     * @param point Point puntatore  ScacchiModel coordinate.
     * @param nuovoPezzo puntatore a nuovoPezzo (Viene passato come riferimento e potrebbe evventualmente cambiare)
     */
    public void impostoPezzo(Point point, Pezzo nuovoPezzo) {
        pezzi[point.x][point.y] = nuovoPezzo;
        aggiornamentoPezzi();
    }

    /**
     * Scambia due pezzi da start e final point.
     * @param startPoint Puntatore point allo start point.
     * @param finalPoint Puntatore point allo final point.
     */
    public void scambioPezzi(Point startPoint, Point finalPoint) {
    	Pezzo pezzo = pezzi[startPoint.x][startPoint.y];
        pezzi[startPoint.x][startPoint.y] = pezzi[finalPoint.x][finalPoint.y];
        pezzi[finalPoint.x][finalPoint.y] = pezzo;
        aggiornamentoPezzi();
    }


    /**
     * Puntatore al pezzo incentrato su ScacchiModel.
     * @return Puntatore al pezzo incentrato su ScacchiModel.
     */
    public Pezzo getAttesaMossa() {
        return attesaMossa;
    }

    /**
     * Imposta il puntatore al pezzo incentrato su ScacchiModel
     * @param attesaMossa puntatore al pezzo incentrato su ScacchiModel(Viene passato come riferimento e potrebbe evventualmente cambiare)
     */
    public void setAttesaMossa(Pezzo attesaMossa) {
        this.attesaMossa = attesaMossa;
        attesaMossa.setWaitingMove(true);
    }

    /**
     * Resetta ScacchiModel completamente.(I dati riguardanti alla configurazione precedente vengono salvati)
     */
    public void resetModel() {
    	messaggi = Messaggio.TURNO_DEI_BIANCHI;
        catturato = new ArrayList<Pezzo>();
        pezzi = new Pezzo[][]{{
                new Torre(ColorePezzo.NERO), new Cavallo(ColorePezzo.NERO), new Alfiere(ColorePezzo.NERO), new Regina(ColorePezzo.NERO), new Re(ColorePezzo.NERO), new Alfiere(ColorePezzo.NERO), new Cavallo(ColorePezzo.NERO), new Torre(ColorePezzo.NERO)}, {
                new Pedone(ColorePezzo.NERO), new Pedone(ColorePezzo.NERO), new Pedone(ColorePezzo.NERO), new Pedone(ColorePezzo.NERO), new Pedone(ColorePezzo.NERO), new Pedone(ColorePezzo.NERO), new Pedone(ColorePezzo.NERO), new Pedone(ColorePezzo.NERO)}, {
                new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota()}, {
                new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota()}, {
                new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota()}, {
                new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota(), new CellaVuota()}, {
                new Pedone(ColorePezzo.BIANCO), new Pedone(ColorePezzo.BIANCO), new Pedone(ColorePezzo.BIANCO), new Pedone(ColorePezzo.BIANCO), new Pedone(ColorePezzo.BIANCO), new Pedone(ColorePezzo.BIANCO), new Pedone(ColorePezzo.BIANCO), new Pedone(ColorePezzo.BIANCO)}, {
                new Torre(ColorePezzo.BIANCO), new Cavallo(ColorePezzo.BIANCO), new Alfiere(ColorePezzo.BIANCO), new Regina(ColorePezzo.BIANCO), new Re(ColorePezzo.BIANCO), new Alfiere(ColorePezzo.BIANCO), new Cavallo(ColorePezzo.BIANCO), new Torre(ColorePezzo.BIANCO)
            }};
        reNero = pezzi[0][4];
        reBianco = pezzi[7][4];
        aggiornamentoPezzi();
    }

    /**
     * Aggiunge puntatore pezzo catturato nella collezione catturato.
     * @param pezzo pezzo catturato
     */
    public void addCatturaPezzo(Pezzo pezzo) {
        catturato.add(pezzo);
    }

    /**
     * Rimuove il puntatore pezzo catturato dalla collezione catturato.
     * @param pezzo pezzo catturato
     */
    public void removeCatturaPezzo(Pezzo pezzo) {
        catturato.remove(pezzo);

    }

    /**
     * Ripristina il percorso, il dominio di cattura, e il puntatore setWaitingMove 
     * dalla configurazione ScacchiModel corrente.
     */
    public void resetPercorso() {
        for (Pezzo[] pezzi : this.pezzi) {
            for (Pezzo pezzo : pezzi) {
                pezzo.impostoPercorsoPezzo(false);
                pezzo.impostoPezzoDaMangiare(false);
                pezzo.setWaitingMove(false);
            }
        }
    }

    private void aggiornamentoPezzi() {
        for (int i = 0; i < 8; i++) {
            int j = 0;
            for (Pezzo pezzo : pezzi[i]) {
                new Point(i, j);
                pezzo.impostoPosizionePezzoScacchiera(new Point(i, j));
                j++;
            }
        }
    }
}
