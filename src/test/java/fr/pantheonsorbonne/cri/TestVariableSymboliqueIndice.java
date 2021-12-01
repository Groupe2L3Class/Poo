package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

 class TestVariableSymboliqueIndice {

	@Test
     void test() {
		
		VariableSymboliqueIndice vs = new VariableSymboliqueIndice("x",new ConstanteN(1));
		VariableSymboliqueIndice vs1 = new VariableSymboliqueIndice("-y",new ConstanteN(5));
		
		assertEquals("(x1)",vs.afficher());
		assertEquals(false,vs.isNegative());
		assertEquals("(-x1)",vs.setToOpposite().afficher());
		assertEquals("(y5)",vs1.setToOpposite().afficher());
		assertEquals(false,vs.isEqual(vs1));
		assertEquals("1", vs.affecter("(x1)",new ConstanteN(1)).afficher());
		
    }

}
