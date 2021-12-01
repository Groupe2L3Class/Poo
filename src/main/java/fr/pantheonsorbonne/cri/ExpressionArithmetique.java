package fr.pantheonsorbonne.cri;

public interface ExpressionArithmetique {

	String afficher();
	ExpressionArithmetique simplifier();
	boolean isEqual(ExpressionArithmetique ea) ;
	ExpressionArithmetique distribuer();
	ExpressionArithmetique associer();
	ExpressionArithmetique developper();
	
	ExpressionArithmetique setToOpposite();


	ExpressionArithmetique getVarSquared();
	ExpressionArithmetique getVar();
	
	ExpressionArithmetique getVarOnly();

	
	abstract ConstanteD calculer();
	ExpressionArithmetique affecter(String string, ExpressionArithmetique i);
	ExpressionArithmetique deriver();
	ExpressionArithmetique deriverN(int n);
	
	



}
