package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class TestLatex {

	
	@Test
	void test() throws IOException {
		Latex fichier = new Latex(new File("src/main/resources/test1.txt"));
			String ligne1 = fichier.afficheLigne(0);
		assertEquals("\"3+3\\times 5\"", ligne1);
		assertEquals("(3+(3*5))", fichier.parcours(ligne1).afficher());
		assertEquals("18", fichier.parcours(ligne1).simplifier().afficher());

		
		Latex fichier2 = new Latex(new File("src/main/resources/test1.txt"));
		String ligne2 = fichier.afficheLigne(1);
		assertEquals("\"1+\\frac{3+x}{y-1}\"", ligne2);
		assertEquals("(1+((3+(x))/((y)-1)))", fichier2.parcours(ligne2).afficher());
	
		Latex fichier3 = new Latex(new File("src/main/resources/test1.txt"));
		String ligne3 = fichier.afficheLigne(2);
		assertEquals("\"\\sum{x=1}ˆ3 xˆ2\"", ligne3);
		assertEquals("((x)=1Σ3((x)^2))", fichier3.parcours(ligne3).afficher());
	
		Latex fichier4 = new Latex(new File("src/main/resources/test1.txt"));
		String ligne4 = fichier.afficheLigne(3);
		assertEquals("\"\\cos(3)\"", ligne4);
		assertEquals("(cos(3))", fichier4.parcours(ligne4).afficher());
		
		Latex fichier5 = new Latex(new File("src/main/resources/test1.txt"));
		String ligne5 = fichier.afficheLigne(4);
		assertEquals("\"1+\\sin(\\frac{\\pi}{2})\"", ligne5);
		assertEquals("(1+(sin((π/2))))", fichier5.parcours(ligne5).afficher());

		Latex fichier6 = new Latex(new File("src/main/resources/test1.txt"));
		String ligne6 = fichier.afficheLigne(5);
		assertEquals("\"\\sin((\\frac{3}{2})\\times \\pi)\"", ligne6);
		assertEquals("(sin(((3/2)*π)))", fichier6.parcours(ligne6).afficher());
		assertEquals("-1", fichier6.parcours(ligne6).simplifier().afficher());
		
		Latex fichier7 = new Latex(new File("src/main/resources/test1.txt"));
		String ligne7 = fichier.afficheLigne(6);
		assertEquals("\"\\sqrt{32}\"", ligne7);
		assertEquals("(sqrt(32))", fichier7.parcours(ligne7).afficher());
		assertEquals("(4*(sqrt(2)))", fichier7.parcours(ligne7).simplifier().afficher());
		
		Latex fichier8 = new Latex(new File("src/main/resources/test1.txt"));
		String ligne8 = fichier.afficheLigne(7);
		assertEquals("\"\\exp(\\frac{2}{4})\"", ligne8);
		assertEquals("(exp((2/4)))", fichier8.parcours(ligne8).afficher());
		assertEquals("(exp((1/2)))", fichier8.parcours(ligne8).simplifier().afficher());
		
		Latex fichier9 = new Latex(new File("src/main/resources/test1.txt"));
		String ligne9 = fichier.afficheLigne(8);
		assertEquals("\"\\ln(\\exp(3))\"", ligne9);
		assertEquals("(ln((exp(3))))", fichier9.parcours(ligne9).afficher());
		

	
	}

	
}
	
	

