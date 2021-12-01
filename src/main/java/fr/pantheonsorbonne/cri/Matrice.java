package fr.pantheonsorbonne.cri;

public class Matrice implements ExpressionArithmetique {
	private ExpressionArithmetique[][] values;

	public Matrice(ExpressionArithmetique[][] values) {
		this.values=values;
	}
	
	public ExpressionArithmetique[][] getValues() {
        return this.values;
    }
	
	
	@Override
	public String afficher() {
		StringBuilder res=new StringBuilder("[");
		for(int i=0;i<values.length;i++) {
			res.append("[");
			for(int y=0;y<values.length;y++) {
				res.append(values[i][y].afficher());
				if(y!=values.length-1) {
					res.append(",");
				}
			}
		
			
			res.append("]");
			if(i!=values.length-1) {
				res.append(",");
			}
		
		}
		res.append("]");
		return res.toString();
	}

	@Override
	public Matrice simplifier() {
		for(int i=0;i<values.length;i++) {
		
			for(int y=0;y<values.length;y++) {
				this.values[i][y]=this.values[i][y].simplifier();
			}
		}
		return this;
	}

	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		
		return this.simplifier().afficher().equals(ea.simplifier().afficher());
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
	public ExpressionArithmetique developper() {

		return null;
	}

	@Override
	public ExpressionArithmetique setToOpposite() {

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

	@Override
	public ConstanteD calculer() {

		return null;
	}

	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {

		return null;
	}

	@Override
	public ExpressionArithmetique deriver() {

		return null;
	}

	@Override
	public ExpressionArithmetique deriverN(int n) {

		return null;
	}

}
