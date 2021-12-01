package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSin {

	@Test
	void test() {
		
		ExpressionArithmetique sinPi = new Sin(ConstanteSymbolique.pi);
		assertEquals("(sin(π))", sinPi.afficher());
		assertEquals("0", sinPi.simplifier().afficher());
		assertEquals("0.0000", sinPi.calculer().afficher());
		
		
		ExpressionArithmetique  sinUn = new Sin(new ConstanteN(0));
		assertEquals("(sin(0))", sinUn.afficher());
		assertEquals("0", sinUn.simplifier().afficher());
		assertEquals("0.0000", sinUn.calculer().afficher());
		
		ExpressionArithmetique sinO = new Sin(new ConstanteN(2));
		assertEquals("(sin(2))", sinO.afficher());
		assertEquals("(sin(2))", sinO.simplifier().afficher());
		assertEquals("0.9093",sinO.calculer().afficher());
		
		
		ExpressionArithmetique sinQ = new Sin(new ConstanteQ(1,2));
		assertEquals("(sin((1/2)))", sinQ.afficher());
		assertEquals("(sin((1/2)))", sinQ.simplifier().afficher());
		assertEquals("0.4794",sinQ.calculer().afficher());
		
		ExpressionArithmetique sinE = new Sin(ConstanteSymbolique.e);
		assertEquals("(sin(e))", sinE.afficher());
		assertEquals("(sin(e))", sinE.simplifier().afficher());
		assertEquals("0.4108", sinE.calculer().afficher());
		
		ExpressionArithmetique sin1 = new Sin(new Multiplication(new ConstanteN(8),ConstanteSymbolique.pi));
		assertEquals("(sin((8π)))", sin1.afficher());
		assertEquals("0", sin1.simplifier().afficher());
		assertEquals("0.0000",sin1.calculer().afficher());
		
		ExpressionArithmetique sin2 = new Sin(new Multiplication(new ConstanteN(-7),ConstanteSymbolique.pi));
		assertEquals("(sin((-7π)))", sin2.afficher());
		assertEquals("0", sin2.simplifier().afficher());
		assertEquals("0.0000",sin2.calculer().afficher());
		
		ExpressionArithmetique sin3 = new Sin(new Multiplication(new ConstanteQ(3,2),ConstanteSymbolique.pi));
		assertEquals("(sin(((3/2)π)))", sin3.afficher());
		assertEquals("-1", sin3.simplifier().afficher());
		assertEquals("-1.0000",sin3.calculer().afficher());
		
		ExpressionArithmetique cos5 = new Sin(new Multiplication(new ConstanteQ(-7,2),ConstanteSymbolique.pi));
		assertEquals("(sin(((-7/2)π)))", cos5.afficher());
		assertEquals("1", cos5.simplifier().afficher());
		
		ExpressionArithmetique cos6 = new Sin(new Multiplication(ConstanteSymbolique.pi,new ConstanteQ(-7,2)));
		assertEquals("(sin(((-7/2)π)))", cos6.afficher());
		assertEquals("1", cos6.simplifier().afficher());
		
		ExpressionArithmetique sin5 = new Sin(  new Puissance(new VariableSymbolique("x"), new ConstanteN(3)));
		assertEquals("(sin(((x)^3)))", sin5.afficher());
		
		assertEquals("((3*((x)^2))*(cos(((x)^3))))", sin5.deriver().afficher());
		
		ExpressionArithmetique sin6 = new Sin(  (new VariableSymbolique("x")));
		assertEquals("(sin((x)))", sin6.afficher());
		
		assertEquals("(-1*(sin((x))))", sin6.deriverN(2).afficher());
		
		
		
		
		ConstanteN consta2 = new ConstanteN(-1);
		assertEquals(true, sin3.isEqual(consta2));
	}
	
	

}
