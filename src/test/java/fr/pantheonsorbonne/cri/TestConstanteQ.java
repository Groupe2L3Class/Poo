package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



 class TestConstanteQ {

	@Test
	 void test() {
		ConstanteQ  cons = new ConstanteQ(6, 8);
		assertEquals(6, cons.getNum());
		assertEquals(8, cons.getDenum());
		assertEquals("(6/8)", cons.afficher());
		assertEquals("(-6/8)", cons.setToOpposite().afficher());
		assertEquals("(3/4)", cons.simplifier().afficher());
		assertEquals("0.7500", cons.calculer().afficher());
		
		ConstanteQ  cons1 = new ConstanteQ(4, 2);
		assertEquals("2", cons1.simplifier().afficher());
		assertEquals("2.0000",cons1.calculer().afficher());
		
		ConstanteQ  cons2 = new ConstanteQ(1, 3);
		assertEquals("(1/3)", cons2.simplifier().afficher());
		assertEquals("0.3333", cons2.calculer().afficher());
		
		ConstanteQ cons3 =  new ConstanteQ(4, 1);
		assertEquals("4", cons3.simplifier().afficher());
		
		ExpressionArithmetique add1 = new Addition(new ConstanteQ(1,2), new Multiplication(new ConstanteQ(1,2),new ConstanteQ(1,2)));
		assertEquals(true, add1.isEqual(cons));
		assertEquals(false, cons.isEqual(cons2));
	};

}
