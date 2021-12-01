package fr.pantheonsorbonne.cri;




public abstract class OpUnaire implements ExpressionArithmetique {

    protected ExpressionArithmetique value;
    protected String operationName;

    protected OpUnaire(ExpressionArithmetique op, String operationName) {
        this.operationName = operationName;
        this.value = op;
    }
    
    
    
    public ExpressionArithmetique getValue() {
    	return this.value;
    }
    
   
    

    @Override
    public String afficher() {

        return "(" + this.operationName + "(" + value.afficher() + "))";
    }
    
   
    
    @Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.simplifier().afficher().equals(ea.simplifier().afficher());
	}
    
    @Override
	public ExpressionArithmetique deriverN(int n) {
    	ExpressionArithmetique deriv = this;
		for (int i=0; i<n;i++) {
			deriv =deriv.deriver();
		}
		return deriv;
		
	}
   
    @Override
	public ExpressionArithmetique distribuer() {
		return null;
	}
    
    @Override
	public ExpressionArithmetique setToOpposite() {
		this.value.setToOpposite();
		return this;
	}


	@Override
	public ExpressionArithmetique associer() {
		return null;
	}
	
	@Override
	public ExpressionArithmetique developper() {
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
	public ExpressionArithmetique getVarOnly() {
		return null;
	}
    
    

}
