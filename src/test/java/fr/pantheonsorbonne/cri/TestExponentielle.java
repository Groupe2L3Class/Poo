package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class TestExponentielle {

	@Test
	void test() {
		
		ExpressionArithmetique expZero = new Exponentielle(new ConstanteN(0));
		assertEquals("(exp(0))", expZero.afficher());
		assertEquals("1", expZero.simplifier().afficher());
		assertEquals("1.0000",expZero.calculer().afficher());
		
		ExpressionArithmetique expO = new Exponentielle(new ConstanteQ(2, 4));
		assertEquals("(exp((2/4)))", expO.afficher());
		assertEquals("(exp((1/2)))", expO.simplifier().afficher());
		assertEquals("1.6487",expO.calculer().afficher());
		
		ExpressionArithmetique expPi = new Exponentielle(ConstanteSymbolique.pi);
		assertEquals("(exp(π))", expPi.afficher());
		assertEquals("(exp(π))", expPi.simplifier().afficher());
		assertEquals("23.1407",expPi.calculer().afficher());
		
		
		ExpressionArithmetique expUn = new Exponentielle(new ConstanteN(1));
		assertEquals("(exp(1))", expUn.afficher());
		assertEquals("(exp(1))", expUn.simplifier().afficher());
		assertEquals("2.7183",expUn.calculer().afficher());
		
		ExpressionArithmetique expd = new Exponentielle(new VariableSymbolique("x"));
		assertEquals("(exp((x)))", expd.afficher());
		assertEquals("(exp((x)))",expd.deriverN(3).afficher());
		
		ExpressionArithmetique consta = new ConstanteN(1);
		assertEquals(false,expO.isEqual(expUn));
		assertEquals(true,expZero.isEqual(consta));
		
		ExpressionArithmetique explnd = new Exponentielle (new Ln( new VariableSymbolique("x")));
		assertEquals("((1/(x))*(exp((ln((x))))))",explnd.deriver().afficher());
		
		ExpressionArithmetique explnd2 = new Exponentielle (new Ln( new VariableSymbolique("x")));
		assertEquals("0",explnd2.deriverN(2).afficher());
		
		ExpressionArithmetique expVar = new Exponentielle(new VariableSymbolique("x"));
		assertEquals("(exp((x)))", expVar.afficher());
		assertEquals("(exp(1))", expVar.affecter("x", new ConstanteN(1)).afficher());
		assertEquals("2.7183",expVar.affecter("x", new ConstanteN(1)).calculer().afficher());
		
	}

}
