package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestMultiplication {

	@Test
	void test() {
		
		ExpressionArithmetique mulNN = new Multiplication(new ConstanteN(2), new ConstanteN(8));
		assertEquals("(2*8)",mulNN.afficher() );
		assertEquals("16", mulNN.simplifier().afficher() );
		assertEquals("16.0000",mulNN.calculer().afficher() );
		
		
		
		ExpressionArithmetique mulQQ = new Multiplication(new ConstanteQ(2, 7), new ConstanteQ(4, 7));
		assertEquals("((2/7)*(4/7))",mulQQ.afficher() );
		assertEquals("(8/49)",mulQQ.simplifier().afficher() );
		assertEquals("0.1633", mulQQ.calculer().afficher() );
		
		ExpressionArithmetique mulNQ = new Multiplication(new ConstanteN(2), new ConstanteQ(4, 7));
		assertEquals("(2*(4/7))",mulNQ.afficher());
		assertEquals( "(8/7)",mulNQ.simplifier().afficher());
		assertEquals("1.1429",mulNQ.calculer().afficher());
		
		ExpressionArithmetique mulQN = new Multiplication(new ConstanteQ(4, 7), new ConstanteN(2));
		assertEquals("((4/7)*2)",mulQN.afficher());
		assertEquals("(8/7)", mulQN.simplifier().afficher());
		
		ExpressionArithmetique mulON = new Multiplication(ConstanteSymbolique.pi, new ConstanteN(5));
		assertEquals("(5π)", mulON.afficher());
		assertEquals("(5π)",mulON.simplifier().afficher());
		assertEquals("15.7080", mulON.calculer().afficher());
		
		ExpressionArithmetique mulON2 = new Multiplication(ConstanteSymbolique.pi, new ConstanteN(1));
		assertEquals("(1π)", mulON2.afficher());
		assertEquals("π", mulON2.simplifier().afficher());
		assertEquals("3.1416", mulON2.calculer().afficher());
		
		ExpressionArithmetique mulNeutre = new Multiplication(new ConstanteQ(-4,3), new ConstanteN(1));
		ConstanteQ consta = new ConstanteQ(-4,3);
		assertEquals(true, mulNeutre.isEqual(consta));
		
		
		ConstanteSymbolique pi = new ConstanteSymbolique(ConstanteSymbolique.ConstantesSymboliqueConnues.PI,false);
		assertEquals(true, mulON2.isEqual(pi));
		assertEquals(false, mulON2.isEqual(mulON));
		
		Multiplication mulNA = new Multiplication(new ConstanteN(2), new Addition(new VariableSymbolique("x"),new ConstanteQ(1, 2)));
		assertEquals("(2*((x)+(1/2)))", mulNA.afficher());
		assertEquals("((2(x))+(2*(1/2)))", mulNA.distribuer().afficher());
		assertEquals("((2(x))+1)", mulNA.distribuer().simplifier().afficher());
		
		Multiplication mulSQ = new Multiplication(new Soustraction(new VariableSymbolique("x"),new ConstanteN(2)),new ConstanteQ(1,2) );
		assertEquals("(((x)-2)*(1/2))", mulSQ.afficher());
		assertEquals("(((1/2)(x))-((1/2)*2))", mulSQ.distribuer().afficher());
		assertEquals("(((1/2)(x))-1)", mulSQ.distribuer().simplifier().afficher());
		
		Multiplication mulNA2 = new Multiplication(new Addition(new VariableSymbolique("x"),new ConstanteQ(1, 2)), new ConstanteN(2));
		assertEquals("(((x)+(1/2))*2)", mulNA2.afficher());
		assertEquals("((2(x))+(2*(1/2)))", mulNA2.distribuer().afficher());
		assertEquals("((2(x))+1)", mulNA2.distribuer().simplifier().afficher());
		
		Multiplication mulSQ2 = new Multiplication(new ConstanteN(2),new Soustraction(new VariableSymbolique("x"),new ConstanteQ(1,2)) );
		assertEquals("(2*((x)-(1/2)))", mulSQ2.afficher());
		assertEquals("((2(x))-(2*(1/2)))", mulSQ2.distribuer().afficher());
		assertEquals("((2(x))-1)", mulSQ2.distribuer().simplifier().afficher());
		
		ExpressionArithmetique mul1 = new Multiplication(new Multiplication(new VariableSymbolique("x"), new ConstanteN(4)),new ConstanteQ(3,4));
		assertEquals("((4(x))*(3/4))",mul1.afficher());
		assertEquals("((x)*(4*(3/4)))",mul1.associer().afficher());
		assertEquals("(3(x))",mul1.associer().simplifier().afficher());
		
		ExpressionArithmetique mul2 = new Multiplication(new ConstanteN(1),new Division(new ConstanteN(4),new VariableSymbolique("t")));
		assertEquals("(1*(4/(t)))",mul2.afficher());
		assertEquals("((1*4)/(t))",mul2.associer().afficher());
		assertEquals("(4/(t))",mul2.associer().simplifier().afficher());
		
		
		
		ExpressionArithmetique mul5 = new Multiplication(new ConstanteQ(3,4),new Multiplication(new VariableSymbolique("x"), new ConstanteN(4)));
		assertEquals("((3/4)*(4(x)))",mul5.afficher());
		assertEquals("(((3/4)(x))*4)",mul5.associer().afficher());
		
		
		ExpressionArithmetique mul6 = new Multiplication(new Division(new ConstanteN(4),new VariableSymbolique("t")),new ConstanteN(1));
		assertEquals("((4/(t))*1)",mul6.afficher());
		assertEquals("(4*((t)/1))",mul6.associer().afficher());
		
		
		ExpressionArithmetique mul3 = new Multiplication(new ConstanteN(4),new VariableSymbolique("x"));
		assertEquals("(4(x))",mul3.afficher());
		assertEquals("4",mul3.deriver().afficher());
		
		ExpressionArithmetique mul4 = new Multiplication(new ConstanteQ(1,2),new VariableSymbolique("x"));
		assertEquals("(1/2)",mul4.deriver().afficher());
		

		ExpressionArithmetique mulTest = new Multiplication(new Puissance(new VariableSymbolique("x"),new ConstanteN(2)),new VariableSymbolique("x"));
		assertEquals("(((x)^2)*(x))",mulTest.afficher() );
		assertEquals("((x)^3)", mulTest.simplifier().afficher() );
		
		ExpressionArithmetique mulTest2 = new Multiplication(new Puissance(new VariableSymbolique("x"),new ConstanteN(2)),new Puissance(new VariableSymbolique("x"),new ConstanteN(2)));
		assertEquals("(((x)^2)*((x)^2))",mulTest2.afficher() );
		assertEquals("((x)^4)", mulTest2.simplifier().afficher() );
		
	}
	
	

}
