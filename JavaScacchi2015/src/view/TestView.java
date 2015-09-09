package view;


import java.awt.Point;

import model.ScacchiModel;

import org.junit.Assert;
import org.junit.Test;


/**
 * TestView (JUnit Test Case) 
 * @author Stefano91, Università degli Studi di Verona
 */
public class TestView {

	ScacchiModel scMo = new ScacchiModel();

	/**
	 * La funzione testImage testa se l'immagine corrisponde al pezzo assegnato.
	 */
	@Test
	public void testImmagine() {

		Assert.assertEquals("" + Torre.class.getResource("/tn.gif"), (scMo.ottengoPezzo(new Point(0, 0)).getIcon()).toString());
		Assert.assertEquals("" + Cavallo.class.getResource("/cn.gif"), (scMo.ottengoPezzo(new Point(0, 1)).getIcon()).toString());
		Assert.assertEquals("" + Alfiere.class.getResource("/an.gif"), (scMo.ottengoPezzo(new Point(0, 2)).getIcon()).toString());
		Assert.assertEquals("" + Regina.class.getResource("/regn.gif"), (scMo.ottengoPezzo(new Point(0, 3)).getIcon()).toString());
		Assert.assertEquals("" + Re.class.getResource("/ren.gif"), (scMo.ottengoPezzo(new Point(0, 4)).getIcon()).toString());
		Assert.assertEquals("" + Alfiere.class.getResource("/an.gif"), (scMo.ottengoPezzo(new Point(0, 5)).getIcon()).toString());
		Assert.assertEquals("" + Cavallo.class.getResource("/cn.gif"), (scMo.ottengoPezzo(new Point(0, 6)).getIcon()).toString());
		Assert.assertEquals("" + Torre.class.getResource("/tn.gif"), (scMo.ottengoPezzo(new Point(0, 7)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pn.gif"), (scMo.ottengoPezzo(new Point(1, 0)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pn.gif"), (scMo.ottengoPezzo(new Point(1, 1)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pn.gif"), (scMo.ottengoPezzo(new Point(1, 2)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pn.gif"), (scMo.ottengoPezzo(new Point(1, 3)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pn.gif"), (scMo.ottengoPezzo(new Point(1, 4)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pn.gif"), (scMo.ottengoPezzo(new Point(1, 5)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pn.gif"), (scMo.ottengoPezzo(new Point(1, 6)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pn.gif"), (scMo.ottengoPezzo(new Point(1, 7)).getIcon()).toString());

		Assert.assertEquals("" + Torre.class.getResource("/tb.gif"), (scMo.ottengoPezzo(new Point(7, 0)).getIcon()).toString());
		Assert.assertEquals("" + Cavallo.class.getResource("/cb.gif"), (scMo.ottengoPezzo(new Point(7, 1)).getIcon()).toString());
		Assert.assertEquals("" + Alfiere.class.getResource("/ab.gif"), (scMo.ottengoPezzo(new Point(7, 2)).getIcon()).toString());
		Assert.assertEquals("" + Regina.class.getResource("/regb.gif"), (scMo.ottengoPezzo(new Point(7, 3)).getIcon()).toString());
		Assert.assertEquals("" + Re.class.getResource("/reb.gif"), (scMo.ottengoPezzo(new Point(7, 4)).getIcon()).toString());
		Assert.assertEquals("" + Alfiere.class.getResource("/ab.gif"), (scMo.ottengoPezzo(new Point(7, 5)).getIcon()).toString());
		Assert.assertEquals("" + Cavallo.class.getResource("/cb.gif"), (scMo.ottengoPezzo(new Point(7, 6)).getIcon()).toString());
		Assert.assertEquals("" + Torre.class.getResource("/tb.gif"), (scMo.ottengoPezzo(new Point(7, 7)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pb.gif"), (scMo.ottengoPezzo(new Point(6, 0)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pb.gif"), (scMo.ottengoPezzo(new Point(6, 1)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pb.gif"), (scMo.ottengoPezzo(new Point(6, 2)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pb.gif"), (scMo.ottengoPezzo(new Point(6, 3)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pb.gif"), (scMo.ottengoPezzo(new Point(6, 4)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pb.gif"), (scMo.ottengoPezzo(new Point(6, 5)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pb.gif"), (scMo.ottengoPezzo(new Point(6, 6)).getIcon()).toString());
		Assert.assertEquals("" + Pedone.class.getResource("/pb.gif"), (scMo.ottengoPezzo(new Point(6, 7)).getIcon()).toString());
	}
}