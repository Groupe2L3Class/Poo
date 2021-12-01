package fr.pantheonsorbonne.cri;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.cri.ConstanteI;
class TestConstanteI {
	@Test
	void test() {
		
		ConstanteI consI = new ConstanteI();
		assertEquals("(i)",consI.afficher());
		
		ExpressionArithmetique p2= new Puissance(consI.setToOpposite(),new ConstanteN(2));
		assertEquals("(-i)",consI.setToOpposite().afficher());
		
		ExpressionArithmetique puissC = new Puissance(consI,new ConstanteN(2));
		assertEquals("-1",puissC.simplifier().afficher());
		
		ExpressionArithmetique addI = new Addition (new ConstanteN(2), puissC);
		assertEquals("(2+((i)^2))",addI.afficher());
		assertEquals("1",addI.simplifier().afficher());
		
		ExpressionArithmetique ex = new Multiplication(consI,consI);
		assertEquals("-1",ex.simplifier().afficher());
		
		ExpressionArithmetique ex2 = new Addition(consI,consI);
		assertEquals("(2(i))",ex2.simplifier().afficher());
		
		ExpressionArithmetique p3= new Puissance(consI,new ConstanteN(3));
		assertEquals("(i)",p3.simplifier().afficher());
		
		
		
		ExpressionArithmetique p4= new Puissance(consI,new ConstanteN(4));
		assertEquals("-1",p4.simplifier().afficher());
		
		ExpressionArithmetique p5= new Puissance(consI,new ConstanteN(5));
		assertEquals("(i)",p5.simplifier().afficher());
		
		ExpressionArithmetique ex3 = new Addition(new Puissance(consI, new ConstanteN(3)), new Puissance (consI, new ConstanteN(3)));
		assertEquals("(2(i))",ex3.simplifier().afficher());
		

		ExpressionArithmetique ex4 = new Multiplication(new Puissance(consI, new ConstanteN(3)), new Puissance (consI, new ConstanteN(2)));
		assertEquals("(-i)",ex4.simplifier().afficher());
		

		
	    
		
		
		
		
		

		
	}
	
}