package fr.pantheonsorbonne.cri;

public class Ln extends OpUnaire {

	public Ln(ExpressionArithmetique op) {
		super(op, "ln");
	}

	@Override
	public String afficher() {
		return "(ln(" + value.afficher() + "))";
	}

	@Override
	public ExpressionArithmetique simplifier() {
		ExpressionArithmetique val = this.value.simplifier();
		if (val instanceof ConstanteN) {
			ConstanteN cons = (ConstanteN) val;
			if (cons.getValue() == 1)
				return new ConstanteN(0);
		}else if (val.equals(ConstanteSymbolique.e)) {
			return new ConstanteN(1);
		}
		return new Ln(val);
	}
	
	
	
	@Override
	public ConstanteD calculer() {
    		
    		return new ConstanteD( Math.log(this.value.calculer().getDb()) );
    	
    }

	
	
	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		
		ExpressionArithmetique val=this.value.affecter(string, i);
		return new Ln(val);
		
		
	}
	
	@Override
	public ExpressionArithmetique deriver() {
		return new Division (this.value.deriver(),this.value);
	}

	

	
	


}
