package fr.pantheonsorbonne.cri;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

 class TestSomme {
	@Test
	 void test() {
		
		
		ExpressionArithmetique som0 = new Somme(null,7,14,new VariableSymbolique("i"));
		assertEquals("(7Σ14(i))", som0.afficher());
		assertEquals("((i)+((i)+((i)+((i)+((i)+((i)+((i)+(i))))))))",som0.developper().afficher());
		
		ExpressionArithmetique somOpposite = new Somme(null,0,3,new VariableSymbolique("i"));
		assertEquals("(0Σ3(i))", somOpposite.afficher());
		assertEquals("(0Σ3(-i))", somOpposite.setToOpposite().afficher());
		assertEquals("((i)+((i)+((i)+(i))))",somOpposite.setToOpposite().developper().afficher());
		assertEquals("((-i)+((-i)+((-i)+(-i))))",somOpposite.setToOpposite().developper().afficher());
		
		
		Somme som91 = new Somme(new VariableSymbolique("i"),0,10,new Racine(new VariableSymbolique("i")));
		assertEquals("((i)=0Σ10(sqrt((i))))", som91.afficher());
		assertEquals("((sqrt(0))+((sqrt(1))+((sqrt(2))+((sqrt(3))+((sqrt(4))+((sqrt(5))+((sqrt(6))+((sqrt(7))+((sqrt(8))+((sqrt(9))+(sqrt(10))))))))))))",som91.developper().afficher());
		
		Somme som = new Somme(new VariableSymbolique("i"),10,14,new Multiplication(new Sin(new VariableSymbolique("i")),new ConstanteN(3)));
		assertEquals("((i)=10Σ14((sin((i)))*3))", som.afficher());
		assertEquals("(((sin(10))*3)+(((sin(11))*3)+(((sin(12))*3)+(((sin(13))*3)+((sin(14))*3)))))",som.developper().afficher());
		VariableSymbolique a = new VariableSymbolique("i");
		VariableSymbolique b = new VariableSymbolique("i");
		assertEquals(true, a.isEqual(b));
		
		
		
		ExpressionArithmetique som2 = new Somme(new VariableSymbolique("i"),7,14,new VariableSymbolique("i"));
		assertEquals("((i)=7Σ14(i))", som2.afficher());
		assertEquals("(7+(8+(9+(10+(11+(12+(13+14)))))))",som2.developper().afficher());
		assertEquals("84",som2.developper().simplifier().afficher());
		
		
		ExpressionArithmetique som3 = new Somme(new VariableSymbolique("i"),0,3,new Soustraction(new VariableSymbolique("i"), new ConstanteQ(3,4)));
		assertEquals("((i)=0Σ3((i)-(3/4)))", som3.afficher());
		assertEquals("((0-(3/4))+((1-(3/4))+((2-(3/4))+(3-(3/4)))))",som3.developper().afficher());
		assertEquals("3.0000",som3.developper().simplifier().calculer().afficher());
		
		ExpressionArithmetique som4 = new Somme(new VariableSymbolique("i"),1,6,new Soustraction(new Ln(new VariableSymbolique("i")), ConstanteSymbolique.pi));
		assertEquals("((i)=1Σ6((ln((i)))-π))", som4.afficher());
		assertEquals("(((ln(1))-π)+(((ln(2))-π)+(((ln(3))-π)+(((ln(4))-π)+(((ln(5))-π)+((ln(6))-π))))))",som4.developper().afficher());
		assertEquals("-12.2703",som4.developper().simplifier().calculer().afficher());
		assertEquals("-12.2703",som4.calculer().afficher());
		
		ExpressionArithmetique som5 = new Somme(new VariableSymbolique("i"),-7,6,new Addition( new Cos(new VariableSymbolique("i")), ConstanteSymbolique.pi));
		assertEquals("((i)=-7Σ6((cos((i)))+π))", som5.afficher());
		assertEquals("(((cos(-7))+π)+(((cos(-6))+π)+(((cos(-5))+π)+(((cos(-4))+π)+(((cos(-3))+π)+(((cos(-2))+π)+(((cos(-1))+π)+(((cos(0))+π)+(((cos(1))+π)+(((cos(2))+π)+(((cos(3))+π)+(((cos(4))+π)+(((cos(5))+π)+((cos(6))+π))))))))))))))",som5.developper().afficher());
		assertEquals("45.1849",som5.developper().simplifier().calculer().afficher());
		
		ExpressionArithmetique som6 = new Somme(new VariableSymbolique("i"),-2,2,new Cos(new VariableSymbolique("i")));
		assertEquals("((i)=-2Σ2(cos((i))))", som6.afficher());
		assertEquals("((cos(-2))+((cos(-1))+((cos(0))+((cos(1))+(cos(2))))))",som6.developper().afficher());
		assertEquals("1.2483",som6.developper().simplifier().calculer().afficher());
		
		ExpressionArithmetique som7 = new Somme(new VariableSymbolique("i"),1,5,new Multiplication(new VariableSymboliqueIndice("a",new VariableSymbolique("i")),new Puissance(new VariableSymbolique("x"),new VariableSymbolique("i"))));
		assertEquals(som7.simplifier(), som7);
		assertEquals("((i)=1Σ5((a(i))*((x)^(i))))", som7.afficher());
		assertEquals("(((a1)*((x)^1))+(((a2)*((x)^2))+(((a3)*((x)^3))+(((a4)*((x)^4))+((a5)*((x)^5))))))",som7.developper().afficher());
		
		assertEquals(false, som7.isEqual(som6));
	
		
		ExpressionArithmetique som8 = new Somme(new VariableSymbolique("i"),1,5,new Sin(new Soustraction(new VariableSymbolique("i"), new Division(new ConstanteQ(1,2), new VariableSymboliqueIndice("x", new VariableSymbolique("i"))))));
		assertEquals("((i)=1Σ5(sin(((i)-((1/2)/(x(i)))))))", som8.afficher());
		assertEquals("((sin((1-((1/2)/(x1)))))+((sin((2-((1/2)/(x2)))))+((sin((3-((1/2)/(x3)))))+((sin((4-((1/2)/(x4)))))+(sin((5-((1/2)/(x5)))))))))",som8.developper().afficher());
	
		
		assertEquals(null, som8.getVar());
		assertEquals(null, som8.getVarSquared());
		assertEquals(null, som8.getVarOnly());
	
		
		
	}
	
}