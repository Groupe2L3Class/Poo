package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAddition {

	@Test
	void test() {
		
		
		
		
		ExpressionArithmetique addNN = new Addition(new ConstanteN(2), new ConstanteN(8));
		assertEquals("(2+8)", addNN.afficher());
		assertEquals("10", addNN.simplifier().afficher());
		
		ExpressionArithmetique addTest = new Addition(new Multiplication(ConstanteSymbolique.pi, new ConstanteN(3)),ConstanteSymbolique.pi);
		assertEquals("((3π)+π)", addTest.afficher());
		assertEquals("(4π)", addTest.simplifier().afficher());
		
		
		ExpressionArithmetique addTest2 = new Addition(new Multiplication(ConstanteSymbolique.pi, new ConstanteN(3)),new Multiplication(ConstanteSymbolique.pi, new ConstanteN(7)));
		assertEquals("((3π)+(7π))", addTest2.afficher());
		assertEquals("(10π)", addTest2.simplifier().afficher());
		
		ExpressionArithmetique addTest3 = new Addition(new Multiplication(new VariableSymbolique("x"), new ConstanteN(3)),new Multiplication(new VariableSymbolique("x"), new ConstanteN(7)));
		assertEquals("((3(x))+(7(x)))", addTest3.afficher());
		assertEquals("(10(x))", addTest3.simplifier().afficher());
		
	
		
		ExpressionArithmetique addNA = new Addition(new ConstanteQ(8,2), new Addition(new ConstanteN(8), new ConstanteN(10)));
		assertEquals("((8/2)+(8+10))", addNA.afficher());
		assertEquals("22", addNA.simplifier().afficher());
		assertEquals("22.0000", addNA.calculer().afficher());
		
		ExpressionArithmetique addNR = new Addition(new ConstanteQ(8,2), new Racine(new ConstanteN(4)));
		assertEquals("((8/2)+(sqrt(4)))", addNR.afficher());
		assertEquals("6", addNR.simplifier().afficher());
		assertEquals("6.0000", addNR.calculer().afficher());
		
		
		
		ExpressionArithmetique addQQ = new Addition(new ConstanteQ(2, 7), new ConstanteQ(4, 7));
		assertEquals("((2/7)+(4/7))", addQQ.afficher());
		assertEquals("(6/7)", addQQ.simplifier().afficher());
		assertEquals("0.8571", addQQ.calculer().afficher());
		
		ExpressionArithmetique addNQ = new Addition(new ConstanteN(2), new ConstanteQ(4, 7));
		assertEquals("(2+(4/7))", addNQ.afficher());
		assertEquals("(18/7)", addNQ.simplifier().afficher());
		
		ExpressionArithmetique addQN = new Addition(new ConstanteQ(4, 7), new ConstanteN(2));
		assertEquals("((4/7)+2)", addQN.afficher());
		assertEquals("(18/7)", addQN.simplifier().afficher());
		assertEquals("2.5714", addQN.calculer().afficher());
		
		ExpressionArithmetique addON = new Addition(ConstanteSymbolique.pi, new ConstanteN(5));
		assertEquals("(π+5)", addON.afficher());
		assertEquals("(π+5)", addON.simplifier().afficher());
		assertEquals("8.1416", addON.calculer().afficher());
		
		ExpressionArithmetique addOON = new Addition(ConstanteSymbolique.pi, new ConstanteN(0));
		assertEquals("(π+0)", addOON.afficher());
		assertEquals("π", addOON.simplifier().afficher());
		assertEquals("3.1416", addOON.calculer().afficher());
		
		ExpressionArithmetique addOON2 = new Addition( new ConstanteN(0),ConstanteSymbolique.pi);
		assertEquals("(0+π)", addOON2.afficher());
		assertEquals("π", addOON2.simplifier().afficher());
		assertEquals("3.1416", addOON2.calculer().afficher());
		
		ExpressionArithmetique addPQ = new Addition(new ConstanteQ(2,4), ConstanteSymbolique.pi);
		assertEquals("((2/4)+π)", addPQ.afficher());
		assertEquals("((1/2)+π)", addPQ.simplifier().afficher());
		assertEquals("3.6416",addPQ.calculer().afficher());
		
		ExpressionArithmetique addER = new Addition(new Ln(new ConstanteN(1)), new Racine(new ConstanteN(4)));
		assertEquals("((ln(1))+(sqrt(4)))", addER.afficher());
		assertEquals("2", addER.simplifier().afficher());
		assertEquals("2.0000", addER.calculer().afficher());
		
		ExpressionArithmetique add1 = new Addition(new Ln(new ConstanteN(1)), new Racine(new ConstanteN(9)));
		ExpressionArithmetique add2 = new Addition(new ConstanteQ(5,2),new ConstanteQ(1,2));
		assertEquals(true, add1.isEqual(add2));
		assertEquals(false, add2.isEqual(addER));
		
		ExpressionArithmetique addV0 = new Addition(new VariableSymbolique("x"),new ConstanteN(0));
		assertEquals("((x)+0)", addV0.afficher());
		assertEquals("(x)", addV0.simplifier().afficher());
		
		Addition add4 = new Addition(new ConstanteN(3),new Addition(new ConstanteN(1),new VariableSymbolique("x")));
		assertEquals("(3+(1+(x)))",add4.afficher());
		assertEquals("((3+1)+(x))",add4.associer().afficher());
		assertEquals("(4+(x))",add4.associer().simplifier().afficher());
		assertEquals("0",add4.deriverN(2).simplifier().afficher());
		
		Addition add6 = new Addition(new Addition(new ConstanteN(1),new VariableSymbolique("x")),new ConstanteN(3));
		assertEquals("((1+(x))+3)",add6.afficher());
		assertEquals("((1+3)+(x))",add6.associer().afficher());
		assertEquals("(4+(x))",add6.associer().simplifier().afficher());
		assertEquals("0",add6.deriverN(2).simplifier().afficher());
		
		Addition add7 = new Addition(new ConstanteN(1), new Addition(new VariableSymbolique("x"),new ConstanteN(3)));
		assertEquals("(1+((x)+3))",add7.afficher());
		assertEquals("((3+1)+(x))",add7.associer().afficher());
		assertEquals("(4+(x))",add7.associer().simplifier().afficher());
		assertEquals("0",add7.deriverN(2).simplifier().afficher());
		
		Addition add8 = new Addition(new Addition(new VariableSymbolique("x"), new ConstanteN(1)),new ConstanteN(3));
		assertEquals("(((x)+1)+3)",add8.afficher());
		assertEquals("((x)+(1+3))",add8.associer().afficher());
		assertEquals("((x)+4)",add8.associer().simplifier().afficher());
		assertEquals("0",add8.deriverN(2).simplifier().afficher());
		
		Addition add9 = new Addition(new ConstanteN(3),new Soustraction(new ConstanteN(1),new VariableSymbolique("x")));
		assertEquals("(3+(1-(x)))",add9.afficher());
		assertEquals("((3+1)-(x))",add9.associer().afficher());
		assertEquals("(4-(x))",add9.associer().simplifier().afficher());
		assertEquals("0",add9.deriverN(2).simplifier().afficher());
		
		Addition add10 = new Addition(new Soustraction(new ConstanteN(1),new VariableSymbolique("x")),new ConstanteN(3));
		assertEquals("((1-(x))+3)",add10.afficher());
		assertEquals("((1+3)-(x))",add10.associer().afficher());
		assertEquals("(4-(x))",add10.associer().simplifier().afficher());
		assertEquals("0",add10.deriverN(2).simplifier().afficher());
		
		Addition add11 = new Addition(new ConstanteN(1), new Soustraction(new VariableSymbolique("x"),new ConstanteN(3)));
		assertEquals("(1+((x)-3))",add11.afficher());
		assertEquals("((1-3)+(x))",add11.associer().afficher());
		assertEquals("(-2+(x))",add11.associer().simplifier().afficher());
		assertEquals("0",add11.deriverN(2).simplifier().afficher());
		
		Addition add12 = new Addition(new Soustraction(new VariableSymbolique("x"), new ConstanteN(1)),new ConstanteN(3));
		assertEquals("(((x)-1)+3)",add12.afficher());
		assertEquals("((x)+(3-1))",add12.associer().afficher());
		assertEquals("((x)+2)",add12.associer().simplifier().afficher());
		assertEquals("0",add12.deriverN(2).simplifier().afficher());
		
		
		Addition add5 = new Addition(new Soustraction(new VariableSymbolique("t"),new ConstanteN(4)),new ConstanteN(1));
		assertEquals("(((t)-4)+1)",add5.afficher());
		assertEquals("((t)+(1-4))",add5.associer().afficher());
		assertEquals("((t)-3)",add5.associer().simplifier().afficher());
	
		assertEquals("1",add5.associer().deriver().afficher());
		
		Addition add25 = new Addition(new Puissance(new VariableSymbolique("x"),new ConstanteN(2)),new ConstanteN(1));
		assertEquals("(((x)^2)+1)",add25.afficher());
		assertEquals("(x)",add25.getVarSquared().afficher());
		
		Addition add26 = new Addition(new Soustraction(new VariableSymbolique("x"),ConstanteSymbolique.pi),new ConstanteN(1));
		assertEquals("(((x)-π)+1)",add26.afficher());
		assertEquals("(x)",add26.getVar().afficher());
		//assertEquals("(-π+1)",add26.getConstants().afficher());
		
		
		
		Addition add27 = new Addition(new Puissance(new VariableSymbolique("a"),new ConstanteN(2)),new Addition(new Multiplication(new ConstanteN(2),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b"))),new Puissance(new VariableSymbolique("b"),new ConstanteN(2))));
		assertEquals("(((a)^2)+((2*((a)*(b)))+((b)^2)))",add27.afficher());
		assertEquals("((a)+(b))",add27.getVarSquared().afficher());
		assertEquals("(2*((a)*(b)))",add27.getVar().afficher());
		assertEquals("(((a)+(b))^2)",add27.factoriser().afficher());
		
		//assertEquals(null,add27.getConstants());
		
		Addition add28 = new Addition(new Soustraction(new Addition(new Multiplication(new ConstanteN(2),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b"))),new Puissance(new VariableSymbolique("b"),new ConstanteN(2))),new Puissance(new VariableSymbolique("a"),new ConstanteN(2))),new ConstanteN(1));
		assertEquals("((((2*((a)*(b)))+((b)^2))-((a)^2))+1)",add28.afficher());
		assertEquals("((b)+(-a))",add28.getVarSquared().afficher());
		assertEquals("((b)-(a))",add28.getVarSquared().simplifier().afficher());
		assertEquals("(2*((a)*(b)))",add28.getVar().afficher());
		//assertEquals("1",add28.getConstants().afficher());
		
		
		//(2⋅b)2+2⋅2⋅b⋅3⋅a+(3⋅a)2
		//(3a)^2+12ab+(2b)^2
		
		Addition add29 = new Addition(new Puissance(new Multiplication(new ConstanteN(3),new VariableSymbolique("a")),new ConstanteN(2))
				,new Addition(new Multiplication(new ConstanteN(12),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b")))
				,new Puissance(new Multiplication(new ConstanteN(2),new VariableSymbolique("b")),new ConstanteN(2))));
		assertEquals("(((3(a))^2)+((12*((a)*(b)))+((2(b))^2)))",add29.afficher());
		assertEquals("((3(a))+(2(b)))",add29.getVarSquared().afficher());
		assertEquals("(12*((a)*(b)))",add29.getVar().afficher());
		assertEquals("(((3(a))+(2(b)))^2)",add29.factoriser().afficher());
		
		//(-2a)^2+-4πab+(πb)^2 =(-2a+bπ)^2
		
		Addition add30 = new Addition(new Puissance(new Multiplication(new ConstanteN(-2),new VariableSymbolique("a")),new ConstanteN(2))
				,new Addition(new Multiplication(new ConstanteN(-4),new Multiplication(ConstanteSymbolique.pi,new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b"))))
				,new Puissance(new Multiplication(ConstanteSymbolique.pi,new VariableSymbolique("b")),new ConstanteN(2))));
		assertEquals("(((-2(a))^2)+((-4*(π*((a)*(b))))+((π*(b))^2)))",add30.afficher());
		assertEquals("((-2(a))+(π*(b)))",add30.getVarSquared().afficher());
		assertEquals("(-4*(π*((a)*(b))))",add30.getVar().afficher());
		assertEquals("(((-2(a))+(π*(b)))^2)",add30.factoriser().afficher());
		
		//(1/5a)^2+4/25ab+(2/5b)^2 =1/5(a+2b)^2=(1/5a+2/5b)^2
		
		Addition add31 = new Addition(new Puissance(new Multiplication(new ConstanteQ(1,5),new VariableSymbolique("a")),new ConstanteN(2))
				,new Addition(new Multiplication(new ConstanteQ(4,25),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b")))
				,new Puissance(new Multiplication(new ConstanteQ(2,5),new VariableSymbolique("b")),new ConstanteN(2))));
		assertEquals("((((1/5)(a))^2)+(((4/25)*((a)*(b)))+(((2/5)(b))^2)))",add31.afficher());
		assertEquals("(((1/5)(a))+((2/5)(b)))",add31.getVarSquared().afficher());
		assertEquals("((4/25)*((a)*(b)))",add31.getVar().afficher());
		assertEquals("((((1/5)(a))+((2/5)(b)))^2)",add31.factoriser().afficher());
		
	
		//a^2-(2ab)+b^2 = (a-b)^2
		
		Addition add32 = new Addition(new Soustraction(new Puissance(new VariableSymbolique("a"),new ConstanteN(2)),
				new Multiplication(new ConstanteN(2),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b"))))
				,new Puissance(new VariableSymbolique("b"),new ConstanteN(2)));
		assertEquals("((((a)^2)-(2*((a)*(b))))+((b)^2))",add32.afficher());
		assertEquals("((a)-(b))",add32.getVarSquared().afficher());
		assertEquals("(-2*((a)*(b)))",add32.getVar().afficher());
		assertEquals("(((a)-(b))^2)",add32.factoriser().afficher());
		
		
		
		
		
	
		
		//(3a)^2-(12ab)+(2b)^2 = (3a-2*b)^2
		Addition add34 = new Addition(new Soustraction(new Puissance(new Multiplication(new ConstanteN(3),new VariableSymbolique("a"))
				,new ConstanteN(2)),
				new Multiplication(new ConstanteN(12),new Multiplication(new VariableSymbolique("a")
				,new VariableSymbolique("b")))),new Puissance(new Multiplication(new ConstanteN(2),new  VariableSymbolique("b")),new ConstanteN(2)));
		assertEquals("((((3(a))^2)-(12*((a)*(b))))+((2(b))^2))",add34.afficher());
		assertEquals("((3(a))-(2(b)))",add34.getVarSquared().afficher());
		assertEquals("(-12*((a)*(b)))",add34.getVar().afficher());
		assertEquals("(((3(a))-(2(b)))^2)",add34.factoriser().afficher());
			
		
		
		
		Addition add35 = new Addition(new Multiplication(new ConstanteN(3), new Puissance(new VariableSymbolique("a"),new ConstanteN(2))),
				new Addition(new Multiplication(new ConstanteN(6),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b"))),
						new Multiplication(new ConstanteN(3),new Puissance(new VariableSymbolique("b"),new ConstanteN(2)))));
		assertEquals("((3*((a)^2))+((6*((a)*(b)))+(3*((b)^2))))",add35.afficher());
		assertEquals("((3(a))+(3(b)))",add35.getVarSquared().afficher());
		assertEquals("(6*((a)*(b)))",add35.getVar().afficher());
		assertEquals("(3*(((a)+(b))^2))",add35.factoriser().afficher());
		
		
		Addition add36 = new Addition(new Multiplication(new ConstanteN(11), new Puissance(new VariableSymbolique("a"),new ConstanteN(2))),
				new Addition(new Multiplication(new ConstanteN(22),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b"))),
						new Multiplication(new ConstanteN(11),new Puissance(new VariableSymbolique("b"),new ConstanteN(2)))));
		assertEquals("((11*((a)^2))+((22*((a)*(b)))+(11*((b)^2))))",add36.afficher());
		assertEquals("((11(a))+(11(b)))",add36.getVarSquared().afficher());
		assertEquals("(22*((a)*(b)))",add36.getVar().afficher());
		assertEquals("(11*(((a)+(b))^2))",add36.factoriser().afficher());
		
		Addition add37 = new Addition(new Multiplication(new ConstanteN(7), new Puissance(new VariableSymbolique("a"),new ConstanteN(2))),
				new Addition(new Multiplication(new ConstanteN(14),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b"))),
						new Multiplication(new ConstanteN(7),new Puissance(new VariableSymbolique("b"),new ConstanteN(2)))));
		assertEquals("((7*((a)^2))+((14*((a)*(b)))+(7*((b)^2))))",add37.afficher());
		assertEquals("((7(a))+(7(b)))",add37.getVarSquared().afficher());
		assertEquals("(14*((a)*(b)))",add37.getVar().afficher());
		assertEquals("(7*(((a)+(b))^2))",add37.factoriser().afficher());
		
		Addition add38 = new Addition(new Multiplication(new ConstanteN(-3), new Puissance(new VariableSymbolique("a"),new ConstanteN(2))),
				new Addition(new Multiplication(new ConstanteN(-6),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b"))),
						new Multiplication(new ConstanteN(-3),new Puissance(new VariableSymbolique("b"),new ConstanteN(2)))));
		assertEquals("((-3*((a)^2))+((-6*((a)*(b)))+(-3*((b)^2))))",add38.afficher());
		assertEquals("((-3(a))+(-3(b)))",add38.getVarSquared().afficher());
		assertEquals("(-6*((a)*(b)))",add38.getVar().afficher());
		assertEquals("(-3*(((a)+(b))^2))",add38.factoriser().afficher());
		
		Addition add39 = new Addition(new Soustraction(new Multiplication(new ConstanteN(7),new Puissance(new VariableSymbolique("a"),new ConstanteN(2))),
				new Multiplication(new ConstanteN(14),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b")))),new Multiplication(new ConstanteN(7),
						new Puissance(new VariableSymbolique("b"),new ConstanteN(2))));
		assertEquals("(((7*((a)^2))-(14*((a)*(b))))+(7*((b)^2)))",add39.afficher());
		assertEquals("((7(a))-(7(b)))",add39.getVarSquared().afficher());
		assertEquals("(-14*((a)*(b)))",add39.getVar().afficher());
		assertEquals("(7*(((a)-(b))^2))",add39.factoriser().afficher());
		
		Addition add40 = new Addition(new Soustraction(new Multiplication(new ConstanteN(-7),new Puissance(new VariableSymbolique("a"),new ConstanteN(2))),
				new Multiplication(new ConstanteN(-14),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b")))),new Multiplication(new ConstanteN(-7),
						new Puissance(new VariableSymbolique("b"),new ConstanteN(2))));
		assertEquals("(((-7*((a)^2))-(-14*((a)*(b))))+(-7*((b)^2)))",add40.afficher());
		assertEquals("((-7(a))-(-7(b)))",add40.getVarSquared().afficher());
		assertEquals("(14*((a)*(b)))",add40.getVar().afficher());
		assertEquals("(-7*(((a)-(b))^2))",add40.factoriser().afficher());
		
		
		Addition addPi = new Addition(ConstanteSymbolique.pi,ConstanteSymbolique.pi);
		assertEquals("(((-7*((a)^2))-(-14*((a)*(b))))+(-7*((b)^2)))",add40.afficher());
		
		
	}
		
}
