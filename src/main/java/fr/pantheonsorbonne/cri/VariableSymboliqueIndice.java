package fr.pantheonsorbonne.cri;

public class VariableSymboliqueIndice extends ConstanteExpressionArithmetique {
private String symbole;
private ExpressionArithmetique indice;
	

	public VariableSymboliqueIndice(String symbole, ExpressionArithmetique indice) {
		this.symbole=symbole;
		this.indice=indice;
	}

	
	
	public String getSymbole() {
		return this.symbole;
	}
	public ExpressionArithmetique getIndice() {
		return this.indice;
	}
	
	
	
	
	@Override
	public String afficher() {
		return "("+symbole+indice.afficher()+")";
	}

	
	@Override
	public ExpressionArithmetique getVar() {
		return this;
	}
	
	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.simplifier().afficher().equals(ea.simplifier().afficher());
	}
	 @Override
	 public boolean isNegative() {
	    	return (this.symbole.length()>1);
	    }
	
	
	@Override
	public ExpressionArithmetique setToOpposite(){
		if(this.symbole.length()>1) {
			return new VariableSymboliqueIndice(this.symbole.substring(1),this.indice);
		}
		return new VariableSymboliqueIndice("-"+this.symbole,this.indice);
		
		
	}

	
	
	@Override
	public ExpressionArithmetique affecter(String str,  ExpressionArithmetique i) {
		if(this.afficher().equals(str)) {
			return i;
		}else if((this.indice instanceof VariableSymbolique)&&(((VariableSymbolique) this.indice).getSymbole().equals(str))) {
			
				return new VariableSymboliqueIndice(this.symbole,i);
		
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
