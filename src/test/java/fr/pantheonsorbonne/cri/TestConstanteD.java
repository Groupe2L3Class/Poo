package fr.pantheonsorbonne.cri;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class TestConstanteD {
	@Test
	 void test() {
		
		ConstanteD consD = new ConstanteD(2.5);
		assertEquals("2.5000",consD.afficher());
		assertEquals("-2.5000",consD.setToOpposite().afficher());
		assertEquals("2.5000",consD.setToOpposite().setToOpposite().afficher());
		
		ConstanteD consD2 = new ConstanteD(250);
		assertEquals(consD2,consD2.calculer());
		ConstanteD consD3 = new ConstanteD(250);
		assertEquals("250.0000",consD2.afficher());
		
		
		assertEquals("0",consD2.deriver().afficher());
		
		
		assertEquals("0",consD2.deriverN(3).afficher());
	
		assertEquals(true,consD2.isEqual(consD3));
		
		ConstanteD consDnegatif = new ConstanteD(-2.5);
		assertEquals(true,consDnegatif.isNegative());
		

		
	}
	
}