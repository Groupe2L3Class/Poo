package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class TestPuissance {

	@Test
	void test() {
		
		ExpressionArithmetique p0 = new Puissance(new ConstanteN(2),new ConstanteN(5));
		assertEquals("(2^5)", p0.afficher());
		assertEquals("32", p0.simplifier().afficher());
		assertEquals("32.0000", p0.calculer().afficher());
		
		ExpressionArithmetique p00 = new Puissance(new ConstanteN(2),new ConstanteN(0));
		assertEquals("(2^0)", p00.afficher());
		assertEquals("1", p00.simplifier().afficher());
		
		ExpressionArithmetique p1 = new Puissance(new ConstanteQ(2, 4),new ConstanteN(3));
		assertEquals("((2/4)^3)", p1.afficher());
		assertEquals("(1/8)", p1.simplifier().afficher());
		assertEquals("0.1250", p1.calculer().afficher());
		
		ExpressionArithmetique p2 = new Puissance(new ConstanteQ(3, 9), new ConstanteN(2));
		assertEquals("((3/9)^2)", p2.afficher());
		assertEquals("(1/9)", p2.simplifier().afficher());
		assertEquals("0.1111", p2.calculer().afficher());
		
		ExpressionArithmetique p3 = new Puissance(new ConstanteQ(8, 10), new ConstanteQ(1,3));
		assertEquals("((8/10)^(1/3))", p3.afficher());
		assertEquals("(sqrt((sqrt((4/5)))))", p3.simplifier().afficher());
		assertEquals("0.9283", p3.calculer().afficher());
		
		ExpressionArithmetique p4 = new Puissance(new ConstanteQ(20, 4), new ConstanteN(3));
		assertEquals("((20/4)^3)", p4.afficher());
		assertEquals("125", p4.simplifier().afficher());
		assertEquals("125.0000", p4.calculer().afficher());
		
		ExpressionArithmetique p5 = new Puissance(ConstanteSymbolique.pi, new ConstanteN(3));
		assertEquals("(π^3)", p5.afficher());
		assertEquals("(π^3)", p5.simplifier().afficher());
		assertEquals("31.0063", p5.calculer().afficher());
		
		ExpressionArithmetique p6 = new Puissance(ConstanteSymbolique.pi, new ConstanteN(1));
		assertEquals("(π^1)", p6.afficher());
		assertEquals("π", p6.simplifier().afficher());
		assertEquals("3.1416", p6.calculer().afficher());
		
		ExpressionArithmetique p8 = new Puissance(new ConstanteQ(9,5), new ConstanteN(1));
		assertEquals("((9/5)^1)", p8.afficher());
		assertEquals("(9/5)", p8.simplifier().afficher());
		assertEquals("1.8000", p8.calculer().afficher());
		
		ConstanteQ cons = new ConstanteQ(9,5);
		assertEquals(true, p8.isEqual(cons));
		
		ExpressionArithmetique p7 = new Puissance(ConstanteSymbolique.pi, new ConstanteN(0));
		assertEquals("(π^0)", p7.afficher());
		assertEquals("1", p7.simplifier().afficher());
		assertEquals("1.0000", p7.calculer().afficher());
		
		ExpressionArithmetique p9 = new Puissance(new ConstanteQ(3,2), new ConstanteN(0));
		assertEquals("((3/2)^0)", p9.afficher());
		assertEquals("1", p9.simplifier().afficher());
		assertEquals("1.0000", p9.calculer().afficher());
		
		Puissance p10 = new Puissance(new VariableSymbolique("x"), new ConstanteN(3));
		assertEquals("((x)^3)", p10.afficher());
		
		assertEquals("(3*((x)^2))", p10.deriver().afficher());
		
		Puissance p11 = new Puissance(new VariableSymbolique("x"), new ConstanteN(2));
		assertEquals("((x)^2)", p11.afficher());
		
		assertEquals("2", p11.deriverN(2).afficher());

		

		
		ExpressionArithmetique p12 = new Puissance(new ConstanteN(1), new VariableSymbolique("x"));
		assertEquals("(1^(x))", p12.afficher());
		assertEquals("1", p12.simplifier().afficher());
		assertEquals("1.0000", p12.simplifier().calculer().afficher());
		
		ExpressionArithmetique p13 = new Puissance(new ConstanteN(2), new VariableSymbolique("x"));
		assertEquals("(2^(x))", p13.afficher());
		assertEquals("(2^(x))", p13.simplifier().afficher());
		
		ExpressionArithmetique p20 = new Puissance(new ConstanteQ(8, 2), new ConstanteQ(6,3));
		assertEquals("((8/2)^(6/3))", p20.afficher() );
		assertEquals("16", p20.simplifier().afficher());
		
		ExpressionArithmetique p15 = new Puissance(new ConstanteQ(7,3),new ConstanteQ(8, 2));
		assertEquals("((7/3)^(8/2))", p15.afficher());
		assertEquals("(2401/81)", p15.simplifier().afficher());
		assertEquals("29.6420", p15.calculer().afficher());
		
		ExpressionArithmetique p16 = new Puissance(new ConstanteQ(7,3),new ConstanteQ(1, 2));
		assertEquals("((7/3)^(1/2))", p16.afficher());
		assertEquals("(sqrt((7/3)))", p16.simplifier().afficher());
		
		
		
		ExpressionArithmetique p14 = new Puissance(new ConstanteQ(8, 2),new ConstanteQ(7,3));
		assertEquals("((8/2)^(7/3))", p14.afficher());
		assertEquals("(4^(7/3))", p14.simplifier().afficher());
		assertEquals("25.3984", p14.calculer().afficher());
		
		
		
	}


}
																																																																						