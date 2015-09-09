package controller;

import java.awt.Point;
import view.Alfiere;
import view.Cavallo;
import view.CellaVuota;
import view.Pezzo;
import view.Regina;
import view.Scacchiera;
import view.Torre;
import model.Model.ColorePezzo;
import model.Model.Messaggio;
import model.Model.TipoPezzo;
import model.ScacchiModel;

/**
 * ScacchiController (Class)
 * @author Stefano91, Università degli Studi di Verona
 */
public final class ScacchiController implements Controller {

    private ScacchiModel scacchiModel;

    /**
     * ScacchiController (constructor)
     * @param scacchiModel puntatore all'istanza della classe ScacchiModel.
     */
    public ScacchiController(ScacchiModel scacchiModel) {
        this.scacchiModel = scacchiModel;
    }

    @Override
    public void onClick(Scacchiera chiamata, Point cliccato) {
        Pezzo pezzo = scacchiModel.ottengoPezzo(cliccato);
        TipoPezzo tipoPezzo = pezzo.ottieneTipoPezzo();
        ColorePezzo colorePezzo = pezzo.ottieneColorePezzo();
        Messaggio messaggi = scacchiModel.ottengoMessaggio();

        if (messaggi == Messaggio.VITTORIA_BIANCHI || messaggi == Messaggio.VITTORIA_NERI) {
            scacchiModel.resetModel();

            chiamata.designChessBoard(true);
        } else if (tipoPezzo == TipoPezzo.CELLA_VUOTA && !pezzo.PercorsoPezzo() || pezzo.isWaitingMove()
                || messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_BIANCHI && colorePezzo == ColorePezzo.NERO && !pezzo.PercorsoPezzo()
                || messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_NERI && colorePezzo == ColorePezzo.BIANCO && !pezzo.PercorsoPezzo()) 
        {
            //Ho bisogno di resettare il percorso per permettere altre mosse.
            scacchiModel.resetPercorso();
            scacchiModel.impostoMessaggio(messaggi.toString().contains("BIANCO") ? Messaggio.TURNO_DEI_BIANCHI : Messaggio.TURNO_DEI_NERI);

            chiamata.designChessBoard(true);
        } else if ((messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_BIANCHI || messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_NERI)
                && pezzo.PercorsoPezzo() && !scacchiModel.getAttesaMossa().equals(cliccato)) 
        {
            Pezzo spostato = scacchiModel.ottengoPezzo(scacchiModel.getAttesaMossa().ricevePosizionePezzoScacchiera());
            spostato.impostatoComeSpostato();
            if (!pezzo.pezzoDaMangiare()) {
                //Quando il pezzo di destinazione è una cella vuota, bisogna semplicemente scambiare due pezzi per eseguire il movimento.
                scacchiModel.scambioPezzi(scacchiModel.getAttesaMossa().ricevePosizionePezzoScacchiera(), cliccato);
            } else {
                //Altrimenti salvo il pezzo catturato in memoria e lo sostituisco con un nuovo pezzo cella vuota
                scacchiModel.addCatturaPezzo(pezzo);
                scacchiModel.impostoPezzo(cliccato, new CellaVuota());
                scacchiModel.scambioPezzi(scacchiModel.getAttesaMossa().ricevePosizionePezzoScacchiera(), cliccato);
            }
            //Promozione del pedone
            if (spostato.ottieneTipoPezzo() == TipoPezzo.PEDONE && cliccato.x == 0
                    && spostato.ottieneColorePezzo() == ColorePezzo.BIANCO || spostato.ottieneTipoPezzo() == TipoPezzo.PEDONE
                    && cliccato.x == 7 && spostato.ottieneColorePezzo() == ColorePezzo.NERO) 
            {
                switch (chiamata.pedonePromozionale()) {
                    case ALFIERE:
                        scacchiModel.impostoPezzo(cliccato, new Alfiere(spostato.ottieneColorePezzo()));
                        break;
                    case CAVALLO:
                        scacchiModel.impostoPezzo(cliccato, new Cavallo(spostato.ottieneColorePezzo()));
                        break;
                    case REGINA:
                        scacchiModel.impostoPezzo(cliccato, new Regina(spostato.ottieneColorePezzo()));
                        break;
                    default:
                        scacchiModel.impostoPezzo(cliccato, new Torre(spostato.ottieneColorePezzo()));
                }
            }

            //Ho bisogno di ripristinare il percorso per permettere altre mosse.
            scacchiModel.resetPercorso();
            scacchiModel.impostoMessaggio(messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_BIANCHI ? Messaggio.TURNO_DEI_NERI : Messaggio.TURNO_DEI_BIANCHI);

            //Controllo se c'è una condizione di scacco matto.
            if (scaccoMatto(messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_BIANCHI ? ColorePezzo.NERO : ColorePezzo.BIANCO, new ScacchiModel(scacchiModel))) {
                scacchiModel.impostoMessaggio(messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_BIANCHI ? Messaggio.VITTORIA_BIANCHI : Messaggio.VITTORIA_NERI);
                chiamata.avvisoScaccoMatto();
            } 
            //E controllo anche se il re opposto è sotto scacco dopo la mossa
            //con una chiamata d'avviso all'utente.
            else if (reSottoScacco(messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_BIANCHI ? ColorePezzo.NERO : ColorePezzo.BIANCO, new ScacchiModel(scacchiModel))) {
            	chiamata.avvisoScacco();
            }

            chiamata.designChessBoard(true);
        } else if (messaggi == Messaggio.TURNO_DEI_BIANCHI && colorePezzo == ColorePezzo.BIANCO
                || messaggi == Messaggio.TURNO_DEI_NERI && colorePezzo == ColorePezzo.NERO) 
        {
            switch (tipoPezzo) {
                case PEDONE:
                    pedone(cliccato);
                    break;
                case CAVALLO:
                    cavallo(cliccato);
                    break;
                case TORRE:
                    torre(cliccato);
                    break;
                case ALFIERE:
                    alfiere(cliccato);
                    break;
                case REGINA:
                    regina(cliccato);
                    break;
                //Il caso del re è come quello della regina, ma più vicino; di un solo passo.
                case RE:
                    regina(cliccato, 1);
            }

            //Ho bisogno di controllare che ogni qualvolta che scambio un pezzo, se il re			
            //diventa catturabile o no; se succede allora devo negare la operazione di scambio,
            //impostando il percorso del pezzo su falso.
            for (Pezzo pezzoRinato : scacchiModel.ottengoPercorsoPezzi(colorePezzo)) {
                if (!cliccato.equals(pezzoRinato.ricevePosizionePezzoScacchiera())) {
                    ScacchiModel modelCopia = new ScacchiModel(scacchiModel);
                    if (pezzoRinato.pezzoDaMangiare()) {
                        modelCopia.impostoPezzo(pezzoRinato.ricevePosizionePezzoScacchiera(), new CellaVuota());
                        modelCopia.scambioPezzi(cliccato, pezzoRinato.ricevePosizionePezzoScacchiera());
                    } else {
                        modelCopia.scambioPezzi(cliccato, pezzoRinato.ricevePosizionePezzoScacchiera());
                    }

                    if (reSottoScacco(colorePezzo, modelCopia)) {
                        pezzoRinato.impostoPercorsoPezzo(false);
                        pezzoRinato.impostoPezzoDaMangiare(false);
                    }
                }
            }

            scacchiModel.impostoMessaggio(messaggi == Messaggio.TURNO_DEI_BIANCHI ? Messaggio.ASPETTANDO_MOSSA_DEI_BIANCHI : Messaggio.ASPETTANDO_MOSSA_DEI_NERI);

            //Una volta che è cambiato il model della scacchiera devo progettarla
            chiamata.designChessBoard();
        } else if (messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_BIANCHI && colorePezzo == ColorePezzo.BIANCO
                || messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_NERI && colorePezzo == ColorePezzo.NERO) 
        {
            //Ho bisogno di ripristinare il percorso per permettere altre mosse.
            scacchiModel.resetPercorso();
            scacchiModel.impostoMessaggio(messaggi == Messaggio.ASPETTANDO_MOSSA_DEI_BIANCHI ? Messaggio.TURNO_DEI_BIANCHI : Messaggio.TURNO_DEI_NERI);

            //Una volta che è cambiato il model della scacchiera devo progettarla
            chiamata.designChessBoard(true);
            if (!scacchiModel.getAttesaMossa().equals(cliccato)) {
                onClick(chiamata, cliccato);
            }
        }

        chiamata.setTitle("Gioco degli Scacchi (" + scacchiModel.ottengoMessaggio().toString().toUpperCase().replace('_', ' ') + ")");
    }

    //<editor-fold defaultstate="collapsed" desc="Ereditati i componenti di ScacchiController usati internamente">
    public boolean scaccoMatto(ColorePezzo colore, ScacchiModel model) {
        for (Pezzo pezzo : model.ottengoPezzi(colore)) {
            switch (pezzo.ottieneTipoPezzo()) {
                case PEDONE:
                    pedone(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case CAVALLO:
                    cavallo(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case TORRE:
                    torre(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case ALFIERE:
                    alfiere(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case REGINA:
                    regina(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case RE:
                    regina(pezzo.ricevePosizionePezzoScacchiera(), model, 1);
            }

            for (Pezzo pezzoRinato : model.ottengoPercorsoPezzi(colore)) {
                if (!pezzo.ricevePosizionePezzoScacchiera().equals(pezzoRinato.ricevePosizionePezzoScacchiera())) {
                    ScacchiModel modelCopia = new ScacchiModel(model);
                    if (pezzoRinato.pezzoDaMangiare()) {
                        modelCopia.impostoPezzo(pezzoRinato.ricevePosizionePezzoScacchiera(), new CellaVuota());
                        modelCopia.scambioPezzi(pezzo.ricevePosizionePezzoScacchiera(), pezzoRinato.ricevePosizionePezzoScacchiera());
                    } else {
                        modelCopia.scambioPezzi(pezzo.ricevePosizionePezzoScacchiera(), pezzoRinato.ricevePosizionePezzoScacchiera());
                    }

                    if (!reSottoScacco(colore, modelCopia)) {
                        return false;
                    }
                }
            }
            model.resetPercorso();
        }
        return true;
    }

    public boolean reSottoScacco(ColorePezzo coloreRE, ScacchiModel model) {
        Messaggio messaggi = model.ottengoMessaggio();
        Pezzo re = model.ottengoRe(coloreRE);
        boolean sottoScacco = false;
        for (Pezzo pezzo : model.ottengoPezzi(coloreRE == ColorePezzo.BIANCO ? ColorePezzo.NERO : ColorePezzo.BIANCO)) {
            model.impostoMessaggio(coloreRE == ColorePezzo.BIANCO ? Messaggio.TURNO_DEI_NERI : Messaggio.TURNO_DEI_BIANCHI);

            switch (pezzo.ottieneTipoPezzo()) {
                case PEDONE:
                    pedone(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case CAVALLO:
                    cavallo(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case TORRE:
                    torre(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case ALFIERE:
                    alfiere(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case REGINA:
                    regina(pezzo.ricevePosizionePezzoScacchiera(), model);
                    break;
                case RE:
                    regina(pezzo.ricevePosizionePezzoScacchiera(), model, 1);
            }

            if (re.pezzoDaMangiare()) {
                sottoScacco = true;
                break;
            }
        }
        model.impostoMessaggio(messaggi);
        return sottoScacco;
    }

    // Overload
    private void regina(Point cliccato) {
        regina(cliccato, this.scacchiModel);
    }

    // Overload
    private void regina(Point cliccato, ScacchiModel model) {
        regina(cliccato, model, 8);
    }

    // Overload
    private void regina(Point cliccato, int steps) {
        regina(cliccato, this.scacchiModel, steps);
    }

    private void regina(Point cliccato, ScacchiModel model, int steps) {
        Messaggio messaggi = model.ottengoMessaggio();

        model.ottengoPezzo(cliccato).impostoPercorsoPezzo(true);
        model.setAttesaMossa(model.ottengoPezzo(cliccato));

        //Cerco le possibili mosse dalla scansione di possibilità passo dopo passo
        stepDomainScanner(new Point(cliccato), model, messaggi, 1, 1, steps);
        stepDomainScanner(new Point(cliccato), model, messaggi, -1, -1, steps);
        stepDomainScanner(new Point(cliccato), model, messaggi, -1, 1, steps);
        stepDomainScanner(new Point(cliccato), model, messaggi, 1, -1, steps);
        stepDomainScanner(new Point(cliccato), model, messaggi, 1, 0, steps);
        stepDomainScanner(new Point(cliccato), model, messaggi, -1, 0, steps);
        stepDomainScanner(new Point(cliccato), model, messaggi, 0, 1, steps);
        stepDomainScanner(new Point(cliccato), model, messaggi, 0, -1, steps);
    }

    // Overload
    private void alfiere(Point cliccato) {
        alfiere(cliccato, this.scacchiModel);
    }

    private void alfiere(Point cliccato, ScacchiModel model) {
        Messaggio messaggi = model.ottengoMessaggio();

        model.ottengoPezzo(cliccato).impostoPercorsoPezzo(true);
        model.setAttesaMossa(model.ottengoPezzo(cliccato));

        //Cerco le possibili mosse dalla scansione di possibilità passo dopo passo
        stepDomainScanner(new Point(cliccato), model, messaggi, 1, 1);
        stepDomainScanner(new Point(cliccato), model, messaggi, -1, -1);
        stepDomainScanner(new Point(cliccato), model, messaggi, -1, 1);
        stepDomainScanner(new Point(cliccato), model, messaggi, 1, -1);
    }

    // Overload
    private void torre(Point cliccato) {
        torre(cliccato, this.scacchiModel);
    }

    private void torre(Point cliccato, ScacchiModel model) {
        Messaggio messaggi = model.ottengoMessaggio();

        model.ottengoPezzo(cliccato).impostoPercorsoPezzo(true);
        model.setAttesaMossa(model.ottengoPezzo(cliccato));

        //Cerco le possibili mosse dalla scansione di possibilità passo dopo passo
        stepDomainScanner(new Point(cliccato), model, messaggi, 1, 0);
        stepDomainScanner(new Point(cliccato), model, messaggi, -1, 0);
        stepDomainScanner(new Point(cliccato), model, messaggi, 0, 1);
        stepDomainScanner(new Point(cliccato), model, messaggi, 0, -1);
    }

    // Overload
    private void cavallo(Point cliccato) {
        cavallo(cliccato, this.scacchiModel);
    }

    private void cavallo(Point cliccato, ScacchiModel model) {
        Messaggio messaggi = model.ottengoMessaggio();

        //Facendo una copia di cliccato non cambierò la versione del chiamante
        cliccato = new Point(cliccato);

        model.ottengoPezzo(cliccato).impostoPercorsoPezzo(true);
        model.setAttesaMossa(model.ottengoPezzo(cliccato));

        //Cerco le possibili mosse traducendo le posizioni in sequenza
        int[] possibiliMosse = new int[]{-2, 1, 1, 1, 2, 0, 1, -1, 0, -2, -1, -1, -2, 0, -1, 1};
        for (int i = 0; i < possibiliMosse.length; i += 2) {
            stepDomainScanner(cliccato, model, messaggi, possibiliMosse[i], possibiliMosse[i + 1], 1);
        }
    }

    // Overload
    private void pedone(Point cliccato) {
        pedone(cliccato, this.scacchiModel);
    }

    private void pedone(Point cliccato, ScacchiModel model) {
        Pezzo pezzo = model.ottengoPezzo(cliccato);
        Messaggio messaggi = model.ottengoMessaggio();

        //Facendo una copia di cliccato non cambierò la versione del chiamante
        cliccato = new Point(cliccato);

        model.ottengoPezzo(cliccato).impostoPercorsoPezzo(true);
        model.setAttesaMossa(model.ottengoPezzo(cliccato));

        int costantePedone = messaggi == Messaggio.TURNO_DEI_BIANCHI ? -1 : 1;

        if (stepDomainScanner(cliccato, model, messaggi, costantePedone, 0, 1, true, false) == 0 && !pezzo.pezzoSpostato()) {
            stepDomainScanner(new Point(cliccato), model, messaggi, costantePedone, 0, 1, true, false);
        }
        stepDomainScanner(cliccato, model, messaggi, 0, -1, 1, false, true);
        stepDomainScanner(cliccato, model, messaggi, 0, 2, 1, false, true);
    }

    // Overload
    private int stepDomainScanner(Point cliccato, ScacchiModel model, Messaggio messaggi, int passoVerticale,
            int passoOrizzontale) {
        return stepDomainScanner(cliccato, model, messaggi, passoVerticale, passoOrizzontale, 8, true, true);
    }

    // Overload
    private int stepDomainScanner(Point cliccato, ScacchiModel model, Messaggio messaggi, int passoVerticale,
            int passoOrizzontale, int steps) {
        return stepDomainScanner(cliccato, model, messaggi, passoVerticale, passoOrizzontale, steps, true, true);
    }

    //Il valore di ritorno:
    // 0 	- qualunque direzione
    // 1 	- qualcuno dello stesso colore nella direzione 
    // -1 	- qualcuno catturabile del colore opposto
    private int stepDomainScanner(Point cliccato, ScacchiModel model, Messaggio messaggi, int passoVerticale,
            int passoOrizzontale, int steps, boolean percorsoImpostato, boolean cellaDiCattura) {

        for (int i = 0; i < steps; i++) {
            cliccato.translate(passoVerticale, passoOrizzontale);
            Pezzo catturato = model.ottengoPezzo(cliccato);
            if (cliccato.x > 7 || cliccato.x < 0 || cliccato.y > 7 || cliccato.y < 0) {
            	 //Fuori limite, raggiunto il limite della scacchiera, ritornando 0 (vero) precisa che 
            	 //tutto il percorso dal punto di partenza al punto di arrivo era vuoto;
                return 0;
            }
            if (catturato.ottieneTipoPezzo() == TipoPezzo.CELLA_VUOTA && percorsoImpostato) {
                //Se la via è libera, lo segnalo;
                catturato.impostoPercorsoPezzo(true);
            } else if (catturato.ottieneTipoPezzo() != TipoPezzo.CELLA_VUOTA && cellaDiCattura && (catturato.ottieneColorePezzo() != ColorePezzo.BIANCO && messaggi == Messaggio.TURNO_DEI_BIANCHI
                    || catturato.ottieneColorePezzo() != ColorePezzo.NERO && messaggi == Messaggio.TURNO_DEI_NERI)) 
            {
                catturato.impostoPercorsoPezzo(true);
                catturato.impostoPezzoDaMangiare(true);

                //Una volta che sono arrivato qui, do la possibilità al giocatore di catturare
                //il pezzo del colore opposto. Restituendo falso, il chiamante saprà che c'è un pezzo da mangiare.
                return -1;
            } else {
            	//Qualcuno dello stesso colore nella direzione 
                return 1;
            }
        }
        return 0;
    }
    //</editor-fold>
}
