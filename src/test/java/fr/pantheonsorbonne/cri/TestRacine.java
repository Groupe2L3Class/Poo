package fr.pantheonsorbonne.cri;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 class TestRacine {
	
	@Test
	 void test() {
		ExpressionArithmetique RacN = new Racine(new ConstanteN(4));
		assertEquals("2",RacN.simplifier().afficher());
		assertEquals("2.0000", RacN.calculer().afficher());
		
		
		ExpressionArithmetique RacNM = new Racine(new ConstanteN(32));
		assertEquals("(sqrt(32))",RacNM.afficher());
		assertEquals("(4*(sqrt(2)))", RacNM.simplifier().afficher());
		assertEquals("5.6569",RacNM.calculer().afficher());
		
		ExpressionArithmetique RacQ = new Racine(new ConstanteQ(25,10));
		
		assertEquals("(sqrt((25/10)))",RacQ.afficher());
		assertEquals("(sqrt((5/2)))", RacQ.simplifier().afficher());
		assertEquals("1.5811",RacQ.calculer().afficher());
		
		ExpressionArithmetique RacPi = new Racine(ConstanteSymbolique.pi);
		
		assertEquals("(sqrt(π))",RacPi.afficher());
		assertEquals("(sqrt(π))", RacPi.simplifier().afficher());
		assertEquals("1.7725",RacPi.calculer().afficher());
		
		
		ConstanteN consta = new ConstanteN(2);
		assertEquals(true, RacN.isEqual(consta));
		assertEquals(false, RacPi.isEqual(consta));
		
		ExpressionArithmetique RacZero = new Racine(new ConstanteN(0));
		assertEquals("(sqrt(0))",RacZero.afficher());
		assertEquals("0", RacZero.simplifier().afficher());
		assertEquals("0.0000",RacZero.calculer().afficher());
		
		ExpressionArithmetique RacUn = new Racine(new ConstanteN(1));
		assertEquals("(sqrt(1))",RacUn.afficher());
		assertEquals("1", RacUn.simplifier().afficher());
		assertEquals("1.0000",RacUn.calculer().afficher());
		
		ExpressionArithmetique Racd = new Racine(new VariableSymbolique("x"));
		assertEquals("(sqrt((x)))",Racd.afficher());
		assertEquals("(1/(2*(sqrt((x)))))",Racd.deriver().afficher());
		
		
		ConstanteN consta2 = new ConstanteN(1);
		assertEquals(true, RacUn.isEqual(consta2));
	}
	
}