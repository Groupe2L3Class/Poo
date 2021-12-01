package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

 class TestVariableSymbolique {

	@Test
     void test() {
		VariableSymbolique vs = new VariableSymbolique("x");
		VariableSymbolique vs1 = new VariableSymbolique("-y");
		
		assertEquals("(x)",vs.afficher());
		
		assertEquals("(-x)",vs.setToOpposite().afficher());
		assertEquals("(y)",vs1.setToOpposite().afficher());
		assertEquals(false,vs.isEqual(vs1));
		
		
		ExpressionArithmetique var = new VariableSymbolique("x");
		assertEquals("(x)", var.afficher());			
		
		ExpressionArithmetique vN0 = new Addition(new VariableSymbolique("x"), new ConstanteN(0));
		assertEquals("((x)+0)", vN0.afficher());
		assertEquals("(x)", vN0.simplifier().afficher());
	
		ExpressionArithmetique NOv = new Addition(new ConstanteN(0),new VariableSymbolique("x"));
		assertEquals("(0+(x))", NOv.afficher());
		assertEquals("(x)", NOv.simplifier().afficher());
		
		ExpressionArithmetique Sous = new Soustraction(new VariableSymbolique("x"), new Multiplication(new ConstanteN(2), new VariableSymbolique("x")));

		assertEquals("((x)-(2(x)))", Sous.afficher());
		
		VariableSymbolique afCN = new VariableSymbolique("x");
		assertEquals("(x)", afCN.afficher());
		assertEquals("1", afCN.affecter("x", new ConstanteN(1)).afficher());
		assertEquals("2", afCN.affecter("x", new ConstanteN(2)).afficher());
		
		
		VariableSymbolique afCD = new VariableSymbolique("x");
		assertEquals("(x)", afCD.afficher());
		assertEquals("(4/2)", afCD.affecter("x", new ConstanteQ(4, 2)).afficher());
		assertEquals( "2",afCD.affecter("x", new ConstanteQ(4, 2)).simplifier().afficher());
		
		ExpressionArithmetique add = new Addition(new VariableSymbolique("x"), new ConstanteN(1));
		assertEquals("((4/2)+1)", add.affecter("x", new ConstanteQ(4, 2)).afficher());
		assertEquals("3", add.affecter("x", new ConstanteQ(4, 2)).simplifier().afficher());
		
		ExpressionArithmetique divVN = new Division(new VariableSymbolique("x"), new ConstanteN(2));
		ExpressionArithmetique add1=new Addition(new VariableSymbolique("a"), new Multiplication(new ConstanteN(3), new VariableSymbolique("b")) );
		assertEquals("((x)/2)", divVN.afficher());
		assertEquals("(((a)+(3(b)))/2)", divVN.affecter("x", add1 ).simplifier().afficher());
		
		
		VariableSymbolique x = new VariableSymbolique("x");
		VariableSymbolique y = new VariableSymbolique("y");
		ExpressionArithmetique Affect = new Addition(new ConstanteN(1), new Division(x, new Addition(y, new ConstanteN(1) )));
		assertEquals("(1+((x)/((y)+1)))", Affect.afficher());
		assertEquals("(1+((x)/((y)+1)))", Affect.simplifier().afficher());
		assertEquals("(1+(1/(1+1)))", Affect.affecter("x", new ConstanteN(1)).affecter("y", new ConstanteN(1)).afficher());
		assertEquals("(3/2)", Affect.affecter("x", new ConstanteN(1)).affecter("y", new ConstanteN(1)).simplifier().afficher());
		
	
		
    }

}
