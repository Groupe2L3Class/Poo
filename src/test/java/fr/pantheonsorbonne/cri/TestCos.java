package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCos {

	@Test
	void test() {
		
		ExpressionArithmetique cosPi = new Cos(ConstanteSymbolique.pi);
		assertEquals("(cos(π))", cosPi.afficher());
		assertEquals("-1", cosPi.simplifier().afficher());
		assertEquals("-1.0000", cosPi.calculer().afficher());
		
		
		ExpressionArithmetique cosUn = new Cos(new ConstanteN(0));
		assertEquals("(cos(0))", cosUn.afficher());
		assertEquals("1", cosUn.simplifier().afficher());
		assertEquals("1.0000", cosUn.calculer().afficher());
		
		ExpressionArithmetique cosO = new Cos(new ConstanteN(2));
		assertEquals("(cos(2))", cosO.afficher());
		assertEquals("(cos(2))", cosO.simplifier().afficher());
		assertEquals("-0.4161",cosO.calculer().afficher());
		
		ExpressionArithmetique cosQ = new Cos(new ConstanteQ(1,2));
		assertEquals("(cos((1/2)))", cosQ.afficher());
		assertEquals("(cos((1/2)))", cosQ.simplifier().afficher());
		assertEquals("0.8776",cosQ.calculer().afficher());
		
		ExpressionArithmetique cosE = new Cos(ConstanteSymbolique.e);
		assertEquals("(cos(e))", cosE.afficher());
		assertEquals("(cos(e))", cosE.simplifier().afficher());
		assertEquals("-0.9117", cosE.calculer().afficher());
		
		ExpressionArithmetique cos1 = new Cos(new Multiplication(new ConstanteN(3),ConstanteSymbolique.pi));
		assertEquals("(cos((3π)))", cos1.afficher());
		assertEquals("-1", cos1.simplifier().afficher());
		assertEquals("-1.0000", cos1.calculer().afficher());
		
		ExpressionArithmetique cos2 = new Cos(new Multiplication(new ConstanteN(8),ConstanteSymbolique.pi));
		assertEquals("(cos((8π)))", cos2.afficher());
		assertEquals("1", cos2.simplifier().afficher());
		assertEquals("1.0000", cos2.calculer().afficher());
		
		ExpressionArithmetique cos3 = new Cos(new Multiplication(ConstanteSymbolique.pi,new ConstanteN(6)));
		assertEquals("(cos((6π)))", cos3.afficher());
		assertEquals("1", cos3.simplifier().afficher());
		assertEquals("1.0000", cos3.calculer().afficher());
		
		ExpressionArithmetique cos4 = new Cos(new Multiplication(ConstanteSymbolique.pi,new ConstanteQ(-7,2)));
		assertEquals("(cos(((-7/2)π)))", cos4.afficher());
		assertEquals("0", cos4.simplifier().afficher());
		assertEquals("0.0000", cos4.calculer().afficher());
		//renvoie "-0.0000" 
		
		ExpressionArithmetique cos5 = new Cos(new Multiplication(new ConstanteQ(-7,2),ConstanteSymbolique.pi));
		assertEquals("(cos(((-7/2)π)))", cos5.afficher());
		assertEquals("0", cos5.simplifier().afficher());
		
		ExpressionArithmetique cos6 = new Cos(  (new VariableSymbolique("x")));
		assertEquals("(cos((x)))", cos6.afficher());
		
		assertEquals("(-1*(sin((x))))", cos6.deriver().afficher());
		
		ConstanteN cons = new ConstanteN(1);
		assertEquals(true, cos3.isEqual(cons));
		
		assertEquals(null, cos6.associer());
		assertEquals(null, cos6.developper());
		assertEquals(null, cos6.distribuer());
	
	
	}
	
	

}
