package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDivision {

	@Test
	void test() {
		
		ExpressionArithmetique divNN = new Division(new ConstanteN(8), new ConstanteN(2));
		assertEquals("(8/2)", divNN.afficher());
		assertEquals("4", divNN.simplifier().afficher());
		assertEquals("4.0000", divNN.calculer().afficher());
		
		ExpressionArithmetique divNN2 = new Division(new ConstanteN(1), ConstanteSymbolique.pi);
		assertEquals("(1/π)", divNN2.afficher());
		assertEquals("(1/π)", divNN2.simplifier().afficher());
		
		
		
		ExpressionArithmetique divQQ = new Division(new ConstanteQ(2, 7), new ConstanteQ(4, 7));
		assertEquals("((2/7)/(4/7))", divQQ.afficher());
		assertEquals("(1/2)", divQQ.simplifier().afficher());
		assertEquals("0.5000", divQQ.calculer().afficher());
		
		ExpressionArithmetique divNQ = new Division(new ConstanteN(2), new ConstanteQ(4, 7));
		assertEquals("(2/(4/7))", divNQ.afficher());
		assertEquals("(7/2)", divNQ.simplifier().afficher());
		assertEquals("3.5000", divNQ.calculer().afficher());
		
		ExpressionArithmetique divQN = new Division(new ConstanteQ(4, 7), new ConstanteN(2));
		assertEquals("((4/7)/2)", divQN.afficher());
		assertEquals("(2/7)", divQN.simplifier().afficher());
		assertEquals("0.2857", divQN.calculer().afficher());
		
		ExpressionArithmetique divON = new Division(ConstanteSymbolique.pi, new ConstanteN(5));
		assertEquals("(π/5)", divON.afficher());
		assertEquals("(π/5)", divON.simplifier().afficher());
		assertEquals("0.6283", divON.calculer().afficher());
		
		ExpressionArithmetique divEE = new Division (new Sin(new ConstanteN(2)),new Puissance(new ConstanteN(2),new ConstanteN(5)));
		assertEquals("((sin(2))/(2^5))", divEE.afficher());
		assertEquals("((sin(2))/32)", divEE.simplifier().afficher());
		assertEquals("0.6283", divON.calculer().afficher());
		
		ExpressionArithmetique divNeutre = new Division (new ConstanteQ(3,2),new ConstanteN(1));
		assertEquals("((3/2)/1)", divNeutre.afficher());
		assertEquals("(3/2)", divNeutre.simplifier().afficher());
		assertEquals("1.5000", divNeutre.calculer().afficher());
		
		ExpressionArithmetique divCN = new Division(ConstanteSymbolique.pi, new ConstanteN(1));
		assertEquals("(π/1)", divCN.afficher());
		assertEquals("π", divCN.simplifier().afficher());
		assertEquals("3.1416", divCN.calculer().afficher());
		
		ConstanteQ cons = new ConstanteQ(3,2);
		assertEquals(true, divNeutre.isEqual(cons));
		
		ExpressionArithmetique div1 = new Division(new Multiplication(new VariableSymbolique("x"), new ConstanteN(4)),new ConstanteQ(3,4));
		assertEquals("((4(x))/(3/4))",div1.afficher());
		assertEquals("((x)/(4*(3/4)))",div1.associer().afficher());
		assertEquals("((x)/3)",div1.associer().simplifier().afficher());
		assertEquals("(1/3)",div1.associer().simplifier().deriver().afficher());
		
		ExpressionArithmetique div5 = new Division(new ConstanteQ(3,4),new Multiplication(new VariableSymbolique("x"), new ConstanteN(4)));
		assertEquals("((3/4)/(4(x)))",div5.afficher());
		assertEquals("(((3/4)/(x))*4)",div5.associer().afficher());
		assertEquals("(((3/4)/(x))*4)",div5.associer().simplifier().afficher());
		
		
		ExpressionArithmetique div2 = new Division(new ConstanteN(1),new Division(new ConstanteN(4),new VariableSymbolique("t")));
		assertEquals("(1/(4/(t)))",div2.afficher());
		assertEquals("((1/4)/(t))",div2.associer().afficher());
		assertEquals("((1/4)/(t))",div2.associer().simplifier().afficher());
	    assertEquals("((-1/4)/((t)^2))",div2.associer().simplifier().deriver().afficher()); 
		

	}

}
