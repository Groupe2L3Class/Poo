package fr.pantheonsorbonne.cri;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



 class TestMatrice {

	@Test
	 void test() {
		ExpressionArithmetique[][] vals= {{new ConstanteN(1),new ConstanteQ(4,8)},{new ConstanteN(9), ConstanteSymbolique.pi}};
		ExpressionArithmetique mat = new Matrice(vals);
		assertEquals("[[1,(4/8)],[9,π]]", mat.afficher());
		assertEquals("[[1,(1/2)],[9,π]]", mat.simplifier().afficher());
		
		Multiplication multi = new Multiplication(new ConstanteN(2),mat);
		assertEquals("(2*[[1,(1/2)],[9,π]])", multi.afficher());
		assertEquals("[[2,1],[18,(2π)]]", multi.simplifier().afficher());
		
		Multiplication multi2 = new Multiplication(mat,new ConstanteN(3));
		assertEquals("([[1,(1/2)],[9,π]]*3)", multi2.afficher());
		assertEquals("[[2,1],[18,(2π)]]", multi.simplifier().afficher());
		
		Soustraction sous = new Soustraction(new ConstanteN(5),mat);
		assertEquals("(5-[[1,(1/2)],[9,π]])", sous.afficher());
		assertEquals("[[4,(9/2)],[-4,(5-π)]]", sous.simplifier().afficher());
		
		Soustraction sous2= new Soustraction(mat, ConstanteSymbolique.pi);
		assertEquals("([[1,(1/2)],[9,π]]-π)", sous2.afficher());
		assertEquals("[[(π-1),(π-(1/2))],[(π-9),0]]", sous2.simplifier().afficher());
		
		ExpressionArithmetique[][] vals2= {{new ConstanteN(1),new ConstanteQ(1,2)},{new ConstanteN(9), ConstanteSymbolique.pi}};
		ExpressionArithmetique mat2 = new Matrice(vals2);
		assertEquals(true, mat2.isEqual(mat));
		
		
		ExpressionArithmetique[][] vals3= {{new ConstanteN(-12),new ConstanteQ(-7,2)},{new ConstanteN(0), new ConstanteN(1)}};
		ExpressionArithmetique mat3 = new Matrice(vals3);
		ExpressionArithmetique addMat = new Addition(mat,mat3);
		assertEquals("([[1,(1/2)],[9,π]]+[[-12,(-7/2)],[0,1]])", addMat.afficher());	
		assertEquals("[[-11,-3],[9,(π+1)]]", addMat.simplifier().afficher());	
		
		ExpressionArithmetique mutliplicationMat = new Multiplication(mat,mat3);
		assertEquals("([[1,(1/2)],[9,π]]*[[-12,(-7/2)],[0,1]])", mutliplicationMat.afficher());	
		assertEquals("[[-12,-3],[-108,((-63/2)+π)]]", mutliplicationMat.simplifier().afficher());	
		
		
		
		ExpressionArithmetique addMatCons = new Addition(new ConstanteQ(2,3),mat3);
		assertEquals("((2/3)+[[-12,(-7/2)],[0,1]])", addMatCons.afficher());	
		assertEquals("[[(-34/3),(-17/6)],[(2/3),(5/3)]]", addMatCons.simplifier().afficher());	
		
		ExpressionArithmetique addMatCons2 = new Addition(mat3,ConstanteSymbolique.pi);
		assertEquals("([[-12,(-7/2)],[0,1]]+π)", addMatCons2.afficher());	
		assertEquals("[[(π-12),(π-(7/2))],[π,(π+1)]]", addMatCons2.simplifier().afficher());	
	}

}
