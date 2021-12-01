package fr.pantheonsorbonne.cri;

public class ConstanteSymbolique extends ConstanteExpressionArithmetique {

	

	public static final  ExpressionArithmetique pi = new ConstanteSymbolique(ConstantesSymboliqueConnues.PI,false);

	public static final  ExpressionArithmetique e = new ConstanteSymbolique(ConstantesSymboliqueConnues.EXPONENTIELLE,false);
	private boolean isNegative;
	public ConstanteSymbolique(ConstantesSymboliqueConnues constanteConnue, boolean isNegative) {
		this.constant = constanteConnue;
		this.isNegative = isNegative;
	}

	ConstantesSymboliqueConnues constant;

	

	

	public enum ConstantesSymboliqueConnues {
		PI("π",Math.PI), EXPONENTIELLE("e",Math.E);

		String strRepresentation;
		double value;

		ConstantesSymboliqueConnues(String strRepresentation, double value) {
			this.strRepresentation = strRepresentation;
			this.value=value;
		}

		public String getStrPresentation() {
			return this.strRepresentation;
		}
		
		public double getValue() {
			return this.value;
		}

	}

	@Override
	public String afficher() {
		if (this.isNegative) {
			return "-"+this.constant.getStrPresentation();
		}
		return this.constant.getStrPresentation();
	}

	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.simplifier().afficher().equals(ea.simplifier().afficher());
	}
	
	@Override
	public ConstanteD calculer() {
		if (this.isNegative) {
			return new ConstanteD(-(this.constant.getValue()));
		}
		return new ConstanteD(this.constant.getValue());
		
	}

	@Override
	public ExpressionArithmetique setToOpposite() {
		if(this.isNegative) {
			return new ConstanteSymbolique(this.constant, false);
		}
		return new ConstanteSymbolique(this.constant, true);
	}

	
	public boolean isNegative() {
    	return this.isNegative;
    }


	



	
	
}
