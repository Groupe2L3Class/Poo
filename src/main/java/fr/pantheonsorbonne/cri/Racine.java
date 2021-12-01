package fr.pantheonsorbonne.cri;
public class Racine extends OpUnaire {
	public Racine(ExpressionArithmetique op) {
		super(op, "sqrt");
	}
	@Override
	public ExpressionArithmetique simplifier() {
		ExpressionArithmetique val = this.value.simplifier();
		if (val instanceof ConstanteN) {
			ConstanteN cons = (ConstanteN) val;
			if (cons.getValue()==0) {
				return new ConstanteN(0);
			}
			if (cons.getValue()==1) {
				return new ConstanteN(1);
			}
			int a = 1;
			long n = cons.getValue();
			long b ;
			for (int i = (int) (Math.sqrt(n)); i >= 2; i--) {
				if (n % Math.pow(i, 2) == 0) {
					a *= i;
					b = (int) (n / (Math.pow(i, 2)));
					if (b == 1) {
						b = (int) Math.sqrt(n);
						return new ConstanteN(b);
					} else {
						return new Multiplication(new ConstanteN(a), new Racine(new ConstanteN(b)));
					}
				}
			}
		}
		return new Racine(val);
	}
	
	public ConstanteD calculer() {
    	
    		return new ConstanteD( Math.sqrt(this.value.calculer().getDb()) );
    	
    }
	
	
	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		
		ExpressionArithmetique val=this.value.affecter(string, i);
		return new Racine(val);
		
		
	}
	
	@Override
	public ExpressionArithmetique deriver() {
	
		return new Division (this.value.deriver(),new Multiplication (new ConstanteN(2),new Racine(this.value))).simplifier();
	}

}