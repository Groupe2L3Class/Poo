package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

 class TestConstanteSymbolique {

    @Test
     void test() {
        assertEquals("π", ConstanteSymbolique.ConstantesSymboliqueConnues.PI.getStrPresentation());
        assertEquals("e", ConstanteSymbolique.ConstantesSymboliqueConnues.EXPONENTIELLE.getStrPresentation());
        assertEquals("π", new ConstanteSymbolique(ConstanteSymbolique.ConstantesSymboliqueConnues.PI,false).simplifier().afficher());
        assertEquals("-e", new ConstanteSymbolique(ConstanteSymbolique.ConstantesSymboliqueConnues.EXPONENTIELLE,true).simplifier().afficher());
        assertEquals("3.1416", new ConstanteSymbolique(ConstanteSymbolique.ConstantesSymboliqueConnues.PI,false).calculer().afficher());
        
        ConstanteSymbolique pi = new ConstanteSymbolique(ConstanteSymbolique.ConstantesSymboliqueConnues.PI,false);
        ConstanteSymbolique piNegative = new ConstanteSymbolique(ConstanteSymbolique.ConstantesSymboliqueConnues.PI,true);
		assertEquals(false, pi.isEqual(piNegative));
    }

}
