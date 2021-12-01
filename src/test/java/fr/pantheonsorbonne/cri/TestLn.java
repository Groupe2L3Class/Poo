package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestLn {

	@Test
	void test() {
		
		ExpressionArithmetique lnUn = new Ln(new ConstanteN(1));
		assertEquals("(ln(1))", lnUn.afficher());
		assertEquals("0", lnUn.simplifier().afficher());
		assertEquals("0.0000", lnUn.calculer().afficher());
		
		ExpressionArithmetique lnE = new Ln(ConstanteSymbolique.e);
		assertEquals("(ln(e))", lnE.afficher());
		assertEquals("1", lnE.simplifier().afficher());
		assertEquals("1.0000",lnE.calculer().afficher());
		
		ExpressionArithmetique lnO = new Ln(new ConstanteQ(2, 4));
		assertEquals("(ln((2/4)))", lnO.afficher());
		assertEquals("(ln((1/2)))", lnO.simplifier().afficher());
		assertEquals("-0.6931",lnO.calculer().afficher());
		
		ExpressionArithmetique lnPi = new Ln(ConstanteSymbolique.pi);
		assertEquals("(ln(π))", lnPi.afficher());
		assertEquals("(ln(π))", lnPi.simplifier().afficher());
		assertEquals("1.1447",lnPi.calculer().afficher());
		
		assertEquals (false, lnPi.isEqual(lnUn));
		assertEquals(true, lnUn.isEqual(new ConstanteN(0)));
		
		ExpressionArithmetique lnd = new Ln(  new Puissance(new VariableSymbolique("x"), new ConstanteN(3)));
		assertEquals("(ln(((x)^3)))", lnd.afficher());
		assertEquals("((3*((x)^2))/((x)^3))", lnd.deriver().afficher());
		
		assertEquals(null, lnd.getVar());
		assertEquals(null, lnd.getVarSquared());
		assertEquals(null, lnd.getVarOnly());
		
		
	}

}
