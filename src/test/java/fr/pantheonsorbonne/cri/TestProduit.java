package fr.pantheonsorbonne.cri;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 class TestProduit {
	@Test
	 void test() {
		
		
		Produit som0 = new Produit(new VariableSymbolique("i"),0,10,new Racine(new VariableSymbolique("i")));
		assertEquals("((i)=0𝚷10(sqrt((i))))", som0.afficher());
		assertEquals("((sqrt(0))*((sqrt(1))*((sqrt(2))*((sqrt(3))*((sqrt(4))*((sqrt(5))*((sqrt(6))*((sqrt(7))*((sqrt(8))*((sqrt(9))*(sqrt(10))))))))))))",som0.developper().afficher());
		
		Produit som = new Produit(new VariableSymbolique("i"),10,14,new Multiplication(new Sin(new VariableSymbolique("i")),new ConstanteN(3)));
		assertEquals("((i)=10𝚷14((sin((i)))*3))", som.afficher());
		assertEquals("(((sin(10))*3)*(((sin(11))*3)*(((sin(12))*3)*(((sin(13))*3)*((sin(14))*3)))))",som.developper().afficher());
		VariableSymbolique a = new VariableSymbolique("i");
		VariableSymbolique b = new VariableSymbolique("i");
		assertEquals(true, a.isEqual(b));
		
		
		
		ExpressionArithmetique som2 = new Produit(new VariableSymbolique("i"),7,14,new VariableSymbolique("i"));
		assertEquals("((i)=7𝚷14(i))", som2.afficher());
		assertEquals("(7*(8*(9*(10*(11*(12*(13*14)))))))",som2.developper().afficher());
		assertEquals("121080960",som2.developper().simplifier().afficher());
		
		
		ExpressionArithmetique som3 = new Produit(new VariableSymbolique("i"),1,4,new Soustraction(new VariableSymbolique("i"), new ConstanteQ(3,4)));
		assertEquals("((i)=1𝚷4((i)-(3/4)))", som3.afficher());
		assertEquals("((1-(3/4))*((2-(3/4))*((3-(3/4))*(4-(3/4)))))",som3.developper().afficher());
		assertEquals("(585/256)",som3.developper().simplifier().afficher());
		assertEquals("2.2852",som3.developper().simplifier().calculer().afficher());
		
		ExpressionArithmetique som4 = new Produit(new VariableSymbolique("i"),1,6,new Soustraction(new Ln(new VariableSymbolique("i")), ConstanteSymbolique.pi));
		assertEquals("((i)=1𝚷6((ln((i)))-π))", som4.afficher());
		assertEquals("(((ln(1))-π)*(((ln(2))-π)*(((ln(3))-π)*(((ln(4))-π)*(((ln(5))-π)*((ln(6))-π))))))",som4.developper().afficher());
		assertEquals("57.0477",som4.developper().simplifier().calculer().afficher());
		
		ExpressionArithmetique som5 = new Produit(new VariableSymbolique("i"),-7,6,new Addition( new Cos(new VariableSymbolique("i")), ConstanteSymbolique.pi));
		assertEquals("((i)=-7𝚷6((cos((i)))+π))", som5.afficher());
		assertEquals("(((cos(-7))+π)*(((cos(-6))+π)*(((cos(-5))+π)*(((cos(-4))+π)*(((cos(-3))+π)*(((cos(-2))+π)*(((cos(-1))+π)*(((cos(0))+π)*(((cos(1))+π)*(((cos(2))+π)*(((cos(3))+π)*(((cos(4))+π)*(((cos(5))+π)*((cos(6))+π))))))))))))))",som5.developper().afficher());
	
		
		ExpressionArithmetique som6 = new Produit(new VariableSymbolique("i"),-2,2,new Addition( new ConstanteQ(3,8), new VariableSymbolique("i")));
		assertEquals("((i)=-2𝚷2((3/8)+(i)))", som6.afficher());
		assertEquals("(((3/8)+-2)*(((3/8)+-1)*(((3/8)+0)*(((3/8)+1)*((3/8)+2)))))",som6.developper().afficher());
		
		ExpressionArithmetique som7 = new Produit(new VariableSymbolique("i"),0,4,new Addition(new VariableSymbolique("x"), new Ln(new VariableSymboliqueIndice("x",new VariableSymbolique("i")))));
		assertEquals("((i)=0𝚷4((x)+(ln((x(i))))))", som7.afficher());
		assertEquals("(((x)+(ln((x0))))*(((x)+(ln((x1))))*(((x)+(ln((x2))))*(((x)+(ln((x3))))*((x)+(ln((x4))))))))",som7.developper().afficher());
		assertEquals("(((x)+(ln((x0))))*(((x)+(ln((x1))))*(((x)+(ln((x2))))*(((x)+(ln((x3))))*((x)+(ln((x4))))))))",som7.developper().simplifier().afficher());
		
		ExpressionArithmetique som10 = new Produit(null,7,14,new VariableSymbolique("i"));
		assertEquals("(7𝚷14(i))", som10.afficher());
		assertEquals("((i)*((i)*((i)*((i)*((i)*((i)*((i)*(i))))))))",som10.developper().afficher());
		
	}
	
}