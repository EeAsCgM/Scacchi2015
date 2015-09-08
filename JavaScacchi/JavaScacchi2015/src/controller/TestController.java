package controller;



import java.awt.Point;

import model.Model.ColorePezzo;
import model.Model.TipoPezzo;
import model.ScacchiModel;

import org.junit.Assert;
import org.junit.Test;

import view.CellaVuota;

public class TestController {
	
	ScacchiModel sM = new ScacchiModel();
	ScacchiModel scMo = new ScacchiModel();
	ScacchiController chessController = new ScacchiController(sM);

	/**
	 * TestSwapAndKinkUnderCheck tests of method of swap and if the king is
	 * under check
	 *
	 */
	@Test
	public void testSwapAndKinkUnderCheck() {
		// ARFA 8/4/2015
		// Standardly a method name should start with lower case char
		ScacchiModel chessKUC = new ScacchiModel();
		ScacchiController chessController = new ScacchiController(chessKUC);

		Assert.assertTrue(!chessController.reSottoScacco(ColorePezzo.NERO, sM));

		chessKUC.scambioPezzi(new Point(6, 2), new Point(4, 2));

		Assert.assertEquals(TipoPezzo.PEDONE, chessKUC.ottengoPezzo(new Point(4, 2)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, chessKUC.ottengoPezzo(new Point(1, 3)).ottieneTipoPezzo());

		chessKUC.scambioPezzi(new Point(1, 3), new Point(3, 3));

		Assert.assertEquals(TipoPezzo.PEDONE, chessKUC.ottengoPezzo(new Point(3, 3)).ottieneTipoPezzo());

		chessKUC.scambioPezzi(new Point(7, 3), new Point(0, 4));

		Assert.assertEquals(TipoPezzo.REGINA, chessKUC.ottengoPezzo(new Point(0, 4)).ottieneTipoPezzo());

		Assert.assertTrue(chessController.reSottoScacco(ColorePezzo.NERO, chessKUC));
	}

	/**
	 * TestCheckmateAndResetModel tests the checkmate and the method of reset
	 * model
	 *
	 */
	@Test
	public void testCheckmateAndResetModel() {
		// ARFA 8/4/2015
		// Standardly a method name should start with lower case char
		Assert.assertTrue(!chessController.scaccoMatto(ColorePezzo.NERO, scMo));
		Assert.assertTrue(!chessController.scaccoMatto(ColorePezzo.BIANCO, scMo));

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!((i == 0 && j == 4) || (i == 7 && j == 4) || (i == 7 && j == 7))) {
					scMo.impostoPezzo(new Point(i, j), new CellaVuota());
				}
			}
		}

		scMo.scambioPezzi(new Point(7, 7), new Point(0, 5));
		scMo.scambioPezzi(new Point(7, 4), new Point(2, 4));

		Assert.assertEquals(TipoPezzo.RE, scMo.ottengoPezzo(new Point(2, 4)).ottieneTipoPezzo());
		Assert.assertEquals(ColorePezzo.BIANCO, scMo.ottengoPezzo(new Point(2, 4)).ottieneColorePezzo());
		Assert.assertEquals(TipoPezzo.TORRE, scMo.ottengoPezzo(new Point(0, 5)).ottieneTipoPezzo());
		Assert.assertEquals(ColorePezzo.BIANCO, scMo.ottengoPezzo(new Point(0, 5)).ottieneColorePezzo());
		Assert.assertEquals(TipoPezzo.RE, scMo.ottengoPezzo(new Point(0, 4)).ottieneTipoPezzo());
		Assert.assertEquals(ColorePezzo.NERO, scMo.ottengoPezzo(new Point(0, 4)).ottieneColorePezzo());

		Assert.assertTrue(chessController.scaccoMatto(ColorePezzo.NERO, scMo));

		scMo.resetModel();

		Assert.assertEquals(TipoPezzo.RE, scMo.ottengoPezzo(new Point(0, 4)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, scMo.ottengoPezzo(new Point(1, 4)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.CELLA_VUOTA, scMo.ottengoPezzo(new Point(4, 5)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.PEDONE, scMo.ottengoPezzo(new Point(6, 2)).ottieneTipoPezzo());
		Assert.assertEquals(TipoPezzo.CAVALLO, scMo.ottengoPezzo(new Point(7, 6)).ottieneTipoPezzo());
	}

	/**
	 * TestCheckmate tests the checkmate
	 *
	 */
	@Test
	public void testCheckmate() {
		// ARFA 8/4/2015
		// Standardly a method name should start with lower case char
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!((i == 7 && j == 4) || (i == 0 && j == 4) || (i == 0 && j == 2) || (i == 0 && j == 5))) {
					scMo.impostoPezzo(new Point(i, j), new CellaVuota());
				}
			}
		}

		scMo.scambioPezzi(new Point(7, 4), new Point(7, 7));
		scMo.scambioPezzi(new Point(0, 4), new Point(5, 7));
		scMo.scambioPezzi(new Point(0, 2), new Point(5, 5));
		scMo.scambioPezzi(new Point(0, 5), new Point(4, 3));

		Assert.assertEquals(TipoPezzo.RE, scMo.ottengoPezzo(new Point(7, 7)).ottieneTipoPezzo());
		Assert.assertEquals(ColorePezzo.BIANCO, scMo.ottengoPezzo(new Point(7, 7)).ottieneColorePezzo());

		Assert.assertEquals(TipoPezzo.RE, scMo.ottengoPezzo(new Point(5, 7)).ottieneTipoPezzo());
		Assert.assertEquals(ColorePezzo.NERO, scMo.ottengoPezzo(new Point(5, 7)).ottieneColorePezzo());

		Assert.assertEquals(TipoPezzo.ALFIERE, scMo.ottengoPezzo(new Point(5, 5)).ottieneTipoPezzo());
		Assert.assertEquals(ColorePezzo.NERO, scMo.ottengoPezzo(new Point(5, 5)).ottieneColorePezzo());

		Assert.assertEquals(TipoPezzo.ALFIERE, scMo.ottengoPezzo(new Point(4, 3)).ottieneTipoPezzo());
		Assert.assertEquals(ColorePezzo.NERO, scMo.ottengoPezzo(new Point(4, 3)).ottieneColorePezzo());

		Assert.assertTrue(chessController.scaccoMatto(ColorePezzo.BIANCO, scMo));
	}
}

