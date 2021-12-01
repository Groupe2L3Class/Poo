package fr.pantheonsorbonne.cri;

public class VariableSymbolique extends ConstanteExpressionArithmetique {
	private String symbole;
	
	
		public VariableSymbolique(String symbole) {
			this.symbole=symbole;
		}
		

		
	public String getSymbole() {
		return this.symbole;
	}
	@Override
	public String afficher() {
	
		return "("+symbole+")";
		
	}
	
	@Override
	public ExpressionArithmetique getVar() {
		return this;
	}
	
	@Override
	public boolean isNegative() {
		
	   	return (this.symbole.length()>1);
	}

	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.simplifier().afficher().equals(ea.simplifier().afficher());
	}
	
	
	@Override
	public ExpressionArithmetique setToOpposite(){
		if(this.symbole.length()>1) {
			return new VariableSymbolique(this.symbole.substring(1));
		}
		return new VariableSymbolique("-"+this.symbole);
		
		
	}


	


	
	
	
	@Override
	public ExpressionArithmetique affecter(String v, ExpressionArithmetique e) {
		if(this.symbole.equals(v)) {
			return e;
		}
		return this;
		
	}


	


	@Override
	public ExpressionArithmetique deriver() {
		
		return new ConstanteN(1) ;
	}


	@Override
	public ConstanteD calculer() {
		return null;
	}


	@Override
	public ExpressionArithmetique getVarOnly() {
		return this;
	}





	
	
	 
}
