package fr.pantheonsorbonne.cri;

public class Complexe implements ExpressionArithmetique {
	
	private ExpressionArithmetique x;
	private ExpressionArithmetique y;
	private ExpressionArithmetique im;
	private ExpressionArithmetique comp;
	public Complexe ( ExpressionArithmetique x , ExpressionArithmetique y) {
    	this.x=x;
    	this.y=y;
    	
    	if((y instanceof ConstanteExpressionArithmetique)&&(((ConstanteExpressionArithmetique) y).isNegative())) {
    		this.im =new Multiplication ( new ConstanteI(),y.setToOpposite());
    		this.comp= new Soustraction (x ,im);
    	}else {
    		this.im =new Multiplication ( new ConstanteI(),y);
    		this.comp= new Addition (x ,im);
    	}
    	
    }
    
     public ExpressionArithmetique getReal() {
    	 return this.x;
    	 
     }
     
     public ExpressionArithmetique getImaginary() {
    	 return this.y;
    	 
     }
      
 
    @Override
	public String afficher() {
		
		return this.comp.afficher();
	}

    public ConstanteD argument() {
		double angle = Math.atan2(this.y.calculer().getDb(), this.x.calculer().getDb() );
		return new ConstanteD(angle);
	}


	@Override
	public ExpressionArithmetique simplifier() {
		return comp.simplifier();
	}


	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.comp.simplifier().afficher().equals(ea.simplifier().afficher());
	}


	@Override
	public ExpressionArithmetique distribuer() {
		return this;
	}


	@Override
	public ExpressionArithmetique associer() {
		return this;
	}


	@Override
	public ExpressionArithmetique developper() {

		return this;
	}


	@Override
	public ExpressionArithmetique setToOpposite() {
		return new Complexe(this.x.setToOpposite(),this.y.setToOpposite()).simplifier();
	}


	@Override
	public ConstanteD calculer() {
		return null;
	}


	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		return comp.affecter(string, i);
	}


	@Override
	public ExpressionArithmetique deriver() {
		return new Addition ( x.deriver(), im);
	}
	
	
	


	@Override
	public ExpressionArithmetique deriverN(int n) {
    	ExpressionArithmetique d = this;
		for (int i=0; i<n;i++) {
			d =d.deriver();
		}
		return d;
		
	}
	
	public Racine absolueZ() {
		return new Racine (new Addition (new Puissance(x, new ConstanteN(2)), new Puissance(y, new ConstanteN(2))));
		
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