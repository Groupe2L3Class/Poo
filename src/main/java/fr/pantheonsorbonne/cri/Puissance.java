package fr.pantheonsorbonne.cri;

public class Puissance extends OpBinaire {

	public Puissance(ExpressionArithmetique leftOp, ExpressionArithmetique rightOp) {
		super(leftOp, rightOp, "^");
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteN valRight) {
		if (valRight.getValue() == 0) {
			return new ConstanteN(1);
		}
		ConstanteQ puiss = new ConstanteQ((int) Math.pow(valLeft.getNum(), valRight.getValue()),
				(int) Math.pow(valLeft.getDenum(), valRight.getValue()));
		return puiss.simplifier();

	}

	@Override
	protected ExpressionArithmetique simplifier(Matrice matLeft, Matrice matRight) {
		return this;
	}

	@Override
	public ExpressionArithmetique getVarSquared() {
		if ((this.right instanceof ConstanteN) && (((ConstanteN) this.right).getValue() == 2)) {
			return this.left;
		}
		return null;

	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {

		if ((valRight.getNum() == 1) && (valRight.getDenum() > 0)) {
			Racine sqrt = new Racine(valLeft);
			for (int i = 2; i < valRight.getDenum(); i++) {
				sqrt = new Racine(sqrt);
			}
			return sqrt;
		}
		return new Puissance(valLeft.simplifier(), valRight.simplifier());
	}

	@Override
	public ConstanteD calculer() {

		return new ConstanteD(Math.pow(left.calculer().getDb(), right.calculer().getDb()));
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight) {

		return new ConstanteN((long) Math.pow(valLeft.getValue(), valRight.getValue()));
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ExpressionArithmetique valRight) {
		if (valLeft.getValue() == 1) {
			return valLeft;
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique valLeft, ConstanteN valRight) {
		if (valRight.getValue() == 1) {
			return valLeft;
		} else if (valRight.getValue() == 0) {
			return new ConstanteN(1);
		}else if( (valLeft instanceof ConstanteI ) && (valRight.getValue() > 1)) {
			ConstanteI left = (ConstanteI) valLeft;
			if (left.isNegative() ) {
				valLeft = valLeft.setToOpposite();
			}
			if (valRight.getValue() %2 == 0) {
			  return new ConstanteN(-1);
			}
			
			else {
				return valLeft;
			}
		}
		return this;
	}

	@Override
	public ExpressionArithmetique deriver() {

		return new Multiplication(this.right,
				new Puissance(this.left, new Soustraction(this.right, new ConstanteN(1)).simplifier()));

	}

	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {

		return new Puissance(left.affecter(string, i), right.affecter(string, i));

	}

	@Override
	public ExpressionArithmetique developper() {

		return null;
	}

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique matLeft, Matrice matRight) {

		return null;
	}

	@Override
	protected ExpressionArithmetique simplifier(Matrice matLeft, ExpressionArithmetique matRight) {
		return null;
	}

}
