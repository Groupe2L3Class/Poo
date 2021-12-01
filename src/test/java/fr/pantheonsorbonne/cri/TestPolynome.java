package fr.pantheonsorbonne.cri;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class TestPolynome {
	@Test
	 void test() {
		
		Polynome poly = new Polynome(new Multiplication(new ConstanteN(-3),new Puissance(new VariableSymbolique("x"),new ConstanteN(2))),
				new Multiplication(new ConstanteQ(2,5),new VariableSymbolique("x")),new ConstanteN(2));
		assertEquals("((-3*((x)^2))+(((2/5)(x))+2))", poly.afficher());
		assertEquals("Le Delta est Positif, l'equation admet deux solutions : x1= (((-2/5)-(sqrt((604/25))))/-6) x2= (((-2/5)+(sqrt((604/25))))/-6)", poly.resoudre());
	
		Polynome poly2 = new Polynome(new Multiplication(new ConstanteN(3),new Puissance(new VariableSymbolique("x"),new ConstanteN(2))),
				new Multiplication(new ConstanteN(-30),new VariableSymbolique("x")),new ConstanteN(75));
		assertEquals("((3*((x)^2))+((-30(x))+75))", poly2.afficher());
		assertEquals("Le Delta est égale à 0, l'equation admet une solution : x= 5", poly2.resoudre());
		
		
		Polynome poly3 = new Polynome(new Multiplication(new ConstanteN(-8),new Puissance(new VariableSymbolique("x"),new ConstanteN(2))),
				new Multiplication(new ConstanteN(2),new VariableSymbolique("x")),new ConstanteN(-4));
		assertEquals("((-8*((x)^2))+((2(x))-4))", poly3.afficher());
		assertEquals("Le Delta est Négatif, l'equation admet deux solutions complexes : x1= ((-2-((i)*(2*(sqrt(31)))))/-16) x2= ((-2+((i)*(2*(sqrt(31)))))/-16)", poly3.resoudre());

		
	}
	
}