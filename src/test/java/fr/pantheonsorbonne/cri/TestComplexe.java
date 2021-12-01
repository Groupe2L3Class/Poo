package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestComplexe {

	@Test
	void test() {
		Complexe c = new Complexe (new ConstanteN(3),new ConstanteN(5));
		assertEquals("(3+(5(i)))" ,c.afficher());
		assertEquals("(-3-(5(i)))" ,c.setToOpposite().afficher());
		assertEquals("(3+(5(i)))" ,c.afficher());
		assertEquals("(sqrt(((3^2)+(5^2))))" ,c.absolueZ().afficher());
		assertEquals("(sqrt(34))" ,c.absolueZ().simplifier().afficher());
		assertEquals("(5(i))" ,c.deriver().simplifier().afficher());
		assertEquals(true ,c.isEqual(c));
	
		
		
		Complexe c2 = new Complexe (new ConstanteN(2),new ConstanteN(1));
		assertEquals("(2+(i))" ,c2.simplifier().afficher());
	    assertEquals(false ,c.isEqual(c2));
	    System.out.println(c.afficher());
	    System.out.println(c2.afficher());
	    
		ExpressionArithmetique add = new Addition (c,c2);
		assertEquals("(5+(6(i)))" ,add.simplifier().afficher());
		ExpressionArithmetique mull = new Multiplication (c,c2);
		assertEquals("(1+(13(i)))" ,mull.simplifier().afficher());
		
		Complexe c3 = new Complexe (new VariableSymbolique("x"),new ConstanteN(1));
		assertEquals("(x)" ,c3.getReal().afficher());
		assertEquals("1" ,c3.getImaginary().simplifier().afficher());
		assertEquals("(3+(i))" ,c3.affecter("x", new ConstanteN(3)).simplifier().afficher());
		
		assertEquals("1.0304" ,c.argument().afficher());
		
		
	}
}