package fr.pantheonsorbonne.cri;

public abstract class ConstanteExpressionArithmetique implements ExpressionArithmetique {


    @Override
    public ExpressionArithmetique simplifier() {
        return this;
    }
 
   
    
  
    public abstract boolean isNegative();
    
    
    public ExpressionArithmetique getVarOnly() {
    	return null;
    }
   
    @Override
	public ExpressionArithmetique getVarSquared() {
		return null;
	}
	@Override
	public ExpressionArithmetique getVar() {
		return null;
	}
	
    @Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.simplifier().afficher().equals(ea.simplifier().afficher());
	}
	
	@Override
	public ExpressionArithmetique distribuer() {
		return this;
	}
	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		return this;
	}
	@Override
	public ExpressionArithmetique associer() {
		return this;
	}
	@Override
	public ExpressionArithmetique deriver() {
		return new ConstanteN(0);
	}
	
	
	@Override
	public ExpressionArithmetique deriverN(int n) {
		
		return new ConstanteN(0);
	}
	@Override
	public ExpressionArithmetique developper() {
		return this;
	}
	
}
