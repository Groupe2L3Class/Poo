package fr.pantheonsorbonne.cri;

public abstract class OpNaire implements ExpressionArithmetique {
	protected int indexDeb;
	protected int indexFin;
	protected String ope;
	protected ExpressionArithmetique ea;
	protected VariableSymbolique vs;
	

	
	
	
	

	protected OpNaire(VariableSymbolique vs, int indexDeb, int indexFin, String ope, ExpressionArithmetique ea) {
		this.indexDeb = indexDeb;
		this.indexFin = indexFin;
		this.ope = ope;
		this.ea = ea;
		this.vs = vs;
	}
	

	
	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}
	
	
	
	@Override
	public ExpressionArithmetique setToOpposite() {
		this.ea=this.ea.setToOpposite();
		return this;
	}
	
	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.afficher().equals(ea.afficher());
	}

	@Override
	public String afficher() {
		if(this.vs!=null) {
			return "(" + this.vs.afficher() + "=" + this.indexDeb + this.ope + this.indexFin + ea.afficher() + ")";
		}
		return "(" + this.indexDeb + this.ope + this.indexFin + ea.afficher() + ")";
	}

	
	
	@Override
	public ExpressionArithmetique distribuer() {
		return null;
	}

	@Override
	public ExpressionArithmetique associer() {
		return null;
	}

	@Override
	public ConstanteD calculer() {
		return this.developper().calculer();
	}

	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		return this;
	}

	@Override
	public ExpressionArithmetique deriver() {
		return this.developper().deriver();
	}



	@Override
	public ExpressionArithmetique deriverN(int n) {
		return this.developper().deriverN(n);
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
