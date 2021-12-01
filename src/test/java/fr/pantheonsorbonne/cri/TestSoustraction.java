package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSoustraction {

	@Test
	void test() {
		
		ExpressionArithmetique sousNN = new Soustraction(new ConstanteN(8), new ConstanteN(2));
		assertEquals("(8-2)", sousNN.afficher());
		assertEquals("6", sousNN.simplifier().afficher());
		assertEquals("6.0000", sousNN.calculer().afficher());
		
		 ExpressionArithmetique sousQQ = new Soustraction(new ConstanteQ(2, 7), new ConstanteQ(4, 7));
		assertEquals("((2/7)-(4/7))", sousQQ.afficher());
		assertEquals("(-2/7)", sousQQ.simplifier().afficher());
	
		
		ExpressionArithmetique sousNQ = new Soustraction(new ConstanteN(2), new ConstanteQ(4, 7));
		assertEquals("(2-(4/7))", sousNQ.afficher());
		assertEquals("(10/7)", sousNQ.simplifier().afficher()); 
		
		ExpressionArithmetique sousNQ2 = new Soustraction(new ConstanteN(2), new ConstanteQ(0, 7));
		assertEquals("(2-(0/7))", sousNQ2.afficher());
		assertEquals("2", sousNQ2.simplifier().afficher()); 
		
		
		ExpressionArithmetique sousQN = new Soustraction(new ConstanteQ(4, 7), new ConstanteN(2));
		assertEquals("((4/7)-2)", sousQN.afficher());
		assertEquals("(-10/7)", sousQN.simplifier().afficher());
		assertEquals("-1.4286", sousQN.calculer().afficher());
		
		ExpressionArithmetique sousNeutre = new Soustraction(new ConstanteQ(5,7), new ConstanteN(0));
		ConstanteQ consta = new ConstanteQ(5,7);
		assertEquals(true,sousNeutre.isEqual(consta));
		assertEquals(false,sousNeutre.isEqual(sousQQ));
		
		ExpressionArithmetique sousNC = new Soustraction(new ConstanteN(0),ConstanteSymbolique.pi);
		assertEquals("(0-π)", sousNC.afficher());
		assertEquals("-π", sousNC.simplifier().afficher());
		assertEquals("-3.1416", sousNC.calculer().afficher());
		
		ExpressionArithmetique sousVN = new Soustraction(new VariableSymbolique("x"),new ConstanteN(0));
		assertEquals("((x)-0)", sousVN.afficher());
		assertEquals("(x)", sousVN.simplifier().afficher());
		
		ExpressionArithmetique sousNegative = new Soustraction(new ConstanteN(0),ConstanteSymbolique.pi);
		ConstanteSymbolique piNegatif = new ConstanteSymbolique(ConstanteSymbolique.ConstantesSymboliqueConnues.PI,true);
		assertEquals(true,sousNegative.isEqual(piNegatif));
		
		ExpressionArithmetique sousAssociation = new Soustraction(new Addition(new VariableSymbolique("-y"),new ConstanteQ(5,7)),new ConstanteN(7));
		assertEquals("(((-y)+(5/7))-7)", sousAssociation.afficher());
		assertEquals("((-y)+((5/7)-7))", sousAssociation.associer().afficher());
		assertEquals("((-y)-(44/7))", sousAssociation.associer().simplifier().afficher());
		
		ExpressionArithmetique sousAssociation2 = new Soustraction(new ConstanteN(3),new Soustraction(new ConstanteN(-8),new VariableSymbolique("x")));
		assertEquals("(3-(-8-(x)))", sousAssociation2.afficher());
		assertEquals("((3--8)-(x))", sousAssociation2.associer().afficher());
		assertEquals("(11-(x))", sousAssociation2.associer().simplifier().afficher());
		assertEquals("0", sousAssociation2.deriverN(2).simplifier().afficher());
		
	
		
		ExpressionArithmetique sousAssociation3 = new Soustraction(new ConstanteN(7),new Addition(new VariableSymbolique("-y"),new ConstanteQ(5,7)));
		assertEquals("(7-((-y)+(5/7)))", sousAssociation3.afficher());
		assertEquals("((7-(-y))+(5/7))", sousAssociation3.associer().afficher());
		assertEquals("((7+(y))+(5/7))", sousAssociation3.associer().simplifier().afficher());
		
		ExpressionArithmetique sousAssociation4 = new Soustraction(new Soustraction(new ConstanteN(-8),new VariableSymbolique("x")),new ConstanteN(3));
		assertEquals("((-8-(x))-3)", sousAssociation4.afficher());
		assertEquals("(-8-((x)-3))", sousAssociation4.associer().afficher());
		assertEquals("(-8-((x)-3))", sousAssociation4.associer().simplifier().afficher());
		assertEquals("0", sousAssociation4.deriverN(2).simplifier().afficher());
		
		ExpressionArithmetique sousD = new Soustraction(new Sin( new VariableSymbolique("x")),new Multiplication(new ConstanteN(8),new VariableSymbolique("x")));
		assertEquals("((sin((x)))-(8(x)))", sousD.afficher());

		assertEquals("((cos((x)))-8)", sousD.deriver().afficher());
		
		ExpressionArithmetique sousM = new Soustraction(new Multiplication(new VariableSymbolique("-y"),new ConstanteQ(5,7)),new ConstanteN(7));
		assertEquals("(((5/7)(-y))-7)", sousM.afficher());
		assertEquals("(((5/7)(-y))-7)", sousM.associer().afficher());
		
		
		ExpressionArithmetique sousVN2 = new Soustraction(new ConstanteN(0),new VariableSymbolique("x"));
		assertEquals("(0-(x))", sousVN2.afficher());
		assertEquals("(-x)", sousVN2.simplifier().afficher());
		
		ExpressionArithmetique sousVN3 = new Soustraction(new ConstanteN(0),new VariableSymboliqueIndice("x", new ConstanteN(1)));
		assertEquals("(0-(x1))", sousVN3.afficher());
		assertEquals("(-x1)", sousVN3.simplifier().afficher());
		
		ExpressionArithmetique sousNQ3 = new Soustraction(new ConstanteN(0),new ConstanteQ(2,3));
		assertEquals("(0-(2/3))", sousNQ3.afficher());
		assertEquals("(-2/3)", sousNQ3.simplifier().afficher());
		
		ExpressionArithmetique sousNAdd = new Soustraction(new ConstanteN(0),new Addition( new ConstanteQ(2,3), ConstanteSymbolique.pi));
		assertEquals("(0-((2/3)+π))", sousNAdd.afficher());
		assertEquals("((-2/3)-π)", sousNAdd.simplifier().afficher());
		
		//9a^2-16b^2 = (3a-4b)(3a+4b)
		Soustraction sousFacto = new Soustraction(new Puissance(new Multiplication(new ConstanteN(9),new VariableSymbolique("a")),new ConstanteN(2)),new Puissance(new Multiplication(new ConstanteN(16),new VariableSymbolique("b")),new ConstanteN(2)));
		assertEquals("(((9(a))^2)-((16(b))^2))", sousFacto.afficher());
		assertEquals("(((3(a))+(4(b)))*((3(a))-(4(b))))", sousFacto.factoriser().afficher());
		
		//a^2-4b^2 = (a-2b)(a+2b)
		Soustraction sousFacto2 = new Soustraction(new Puissance(new VariableSymbolique("a"),new ConstanteN(2)),new Puissance(new Multiplication(new ConstanteN(4),new VariableSymbolique("b")),new ConstanteN(2)));
		assertEquals("(((a)^2)-((4(b))^2))", sousFacto2.afficher());
		assertEquals("(((a)+(2(b)))*((a)-(2(b))))", sousFacto2.factoriser().afficher());
		
		
		Soustraction sousFacto3 = new Soustraction(new Puissance(new Multiplication(new ConstanteN(9),new VariableSymbolique("a")),new ConstanteN(2)),new Puissance(new Multiplication(new ConstanteN(16),new VariableSymbolique("b")),new ConstanteN(2)));
		assertEquals("(((9(a))^2)-((16(b))^2))", sousFacto3.afficher());
		assertEquals("(((3(a))+(4(b)))*((3(a))-(4(b))))", sousFacto3 .factoriser().afficher());
		
		//(((7*((a)^2))-(14*((a)*(b))))+(7*((b)^2)))
		Soustraction sousFacto4 = new Soustraction(new Multiplication(new ConstanteN(7),new Puissance(new VariableSymbolique("a"),new ConstanteN(2))),
				new Addition(new Multiplication(new ConstanteN(14),new Multiplication(new VariableSymbolique("a"),new VariableSymbolique("b"))),
				new Multiplication(new ConstanteN(7),new Puissance(new VariableSymbolique("b"),new ConstanteN(2)))));
		assertEquals("((7*((a)^2))-((14*((a)*(b)))+(7*((b)^2))))",sousFacto4.afficher());
		assertEquals("((7(a))+(-7(b)))",sousFacto4.getVarSquared().afficher());
		assertEquals("(-14*((a)*(b)))",sousFacto4.getVar().afficher());
		assertEquals("(7*(((a)-(b))^2))",sousFacto4.factoriser().afficher());
	}
}