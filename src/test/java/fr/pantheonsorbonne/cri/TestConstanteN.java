package fr.pantheonsorbonne.cri;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



 class TestConstanteN {

	@Test
	 void test() {
		ConstanteN  cons = new ConstanteN(6);
		assertEquals(6, cons.getValue());
		assertEquals("6", cons.afficher());
		assertEquals("6", cons.simplifier().afficher());
		assertEquals(false, cons.isNegative());
		assertEquals("-6", cons.setToOpposite().afficher());
		assertEquals("6", cons.distribuer().afficher());
		assertEquals("6", cons.associer().afficher());
		assertEquals("6", cons.developper().afficher());

		
	}

}
