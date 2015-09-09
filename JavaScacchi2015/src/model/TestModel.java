package model;

import java.awt.Point;

import model.Model.ColorePezzo;
import model.Model.Messaggio;
import model.Model.TipoPezzo;

import org.junit.Assert;
import org.junit.Test;

/**
 * TestModel (JUnit Test Case) 
 * @author Stefano91, Università degli Studi di Verona
 */
public class TestModel {
	
	ScacchiModel sM = new ScacchiModel();
	
	@Test
	public void testPosizione() {
		Assert.assertEquals(TipoPezzo.TORRE, sM.ottengoPezzo(new Point(0, 0)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.CAVALLO, sM.ottengoPezzo(new Point(0, 1)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.ALFIERE, sM.ottengoPezzo(new Point(0, 2)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.REGINA, sM.ottengoPezzo(new Point(0, 3)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.RE, sM.ottengoPezzo(new Point(0, 4)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.ALFIERE, sM.ottengoPezzo(new Point(0, 5)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.CAVALLO, sM.ottengoPezzo(new Point(0, 6)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.TORRE, sM.ottengoPezzo(new Point(0, 7)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(1, 0)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(1, 1)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(1, 2)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(1, 3)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(1, 4)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(1, 5)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(1, 6)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(1, 7)).ottieneTipoPezzo());

		Assert.assertEquals(TipoPezzo.CELLA_VUOTA, sM.ottengoPezzo(new Point(2, 0)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.CELLA_VUOTA, sM.ottengoPezzo(new Point(3, 3)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.CELLA_VUOTA, sM.ottengoPezzo(new Point(4, 5)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.CELLA_VUOTA, sM.ottengoPezzo(new Point(5, 7)).ottieneTipoPezzo());

		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(6, 0)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(6, 1)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(6, 2)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(6, 3)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(6, 4)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(6, 5)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(6, 6)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, sM.ottengoPezzo(new Point(6, 7)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.TORRE, sM.ottengoPezzo(new Point(7, 0)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.CAVALLO, sM.ottengoPezzo(new Point(7, 1)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.ALFIERE, sM.ottengoPezzo(new Point(7, 2)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.REGINA, sM.ottengoPezzo(new Point(7, 3)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.RE, sM.ottengoPezzo(new Point(7, 4)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.ALFIERE, sM.ottengoPezzo(new Point(7, 5)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.CAVALLO, sM.ottengoPezzo(new Point(7, 6)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.TORRE, sM.ottengoPezzo(new Point(7, 7)).ottieneTipoPezzo());
	}

	/**
	 * La funzione testMessaggio testa il messaggio di inizio sulla scacchiera
	 */
	@Test
	public void testMessaggio() {
		Assert.assertEquals(sM.ottengoMessaggio(), Messaggio.TURNO_DEI_BIANCHI);
	}
	
	/**
	 * La funzione testColore testa il colore dei pezzi a scacchiera inizializzata.
	 */
	@Test
	public void testColore() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				Assert.assertEquals(ColorePezzo.NERO, sM.ottengoPezzo(new Point(i, j)).ottieneColorePezzo());
			}
		}

		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				Assert.assertEquals(ColorePezzo.VUOTO, sM.ottengoPezzo(new Point(i, j)).ottieneColorePezzo());
			}
		}

		for (int i = 6; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Assert.assertEquals(ColorePezzo.BIANCO, sM.ottengoPezzo(new Point(i, j)).ottieneColorePezzo());
			}
		}
	}

}
