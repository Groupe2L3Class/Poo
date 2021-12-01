package fr.pantheonsorbonne.cri;

public class ConstanteN extends ConstanteExpressionArithmetique {

    private long value;

    public ConstanteN(long value) {
        this.value = value;
    }
    
   

	public long getValue() {
		return this.value;
	}
    @Override
    public String afficher() {
        return Long.toString(value);
    }

	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.simplifier().afficher().equals(ea.simplifier().afficher());
	}
	
	@Override
	public ExpressionArithmetique setToOpposite() {
    	return new ConstanteN(-value);
    }

	@Override
	public ConstanteD calculer() {
		return new ConstanteD(value);
	}

	public boolean isNegative() {
    	return (this.value<0);
    }
	

	

	

	

	


}
