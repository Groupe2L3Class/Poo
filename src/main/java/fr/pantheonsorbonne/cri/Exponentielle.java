package fr.pantheonsorbonne.cri;

public class Exponentielle extends OpUnaire {

    public Exponentielle(ExpressionArithmetique op) {
        super(op, "exp");
    }

    @Override
    public ExpressionArithmetique simplifier() {
        this.value = this.value.simplifier();
        if (this.value instanceof ConstanteN) {
        	ConstanteN cons = (ConstanteN) this.value;
            if (cons.getValue() == 0) {
                return new ConstanteN(1);
            }
        }     
        return this;
    }
    
    public ConstanteD calculer() {
    	
    		return new ConstanteD( Math.exp(this.value.calculer().getDb()) );
    
    }
    @Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		
		ExpressionArithmetique val=this.value.affecter(string, i);
		return new Exponentielle(val);
		
		
	}
	

	@Override
	public ExpressionArithmetique deriver() {
		
		return new Multiplication(this.value.deriver(), this).simplifier();
	}

}
