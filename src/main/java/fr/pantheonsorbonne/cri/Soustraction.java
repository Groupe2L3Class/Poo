package fr.pantheonsorbonne.cri;

public class Soustraction extends OpBinaire {
	public Soustraction(ExpressionArithmetique leftOp, ExpressionArithmetique rightOp) {
		super(leftOp, rightOp, "-");
	}

	public ExpressionArithmetique factoriser() {
		if (this.right instanceof Addition) {
			Addition add = (Addition) this.associer();
			return add.factoriser();
		}
		if (this.getVarSquared() != null) {
			OpBinaire varSquared = (OpBinaire) this.getVarSquared();

			ExpressionArithmetique consLeft = new ConstanteN(1);
			ExpressionArithmetique consRight = new ConstanteN(1);
			ExpressionArithmetique firstCarre = varSquared.left;
			ExpressionArithmetique secondCarre = varSquared.right;
			if (firstCarre instanceof Multiplication) {
				Multiplication mulFirst = (Multiplication) firstCarre;
				if (mulFirst.left instanceof ConstanteExpressionArithmetique) {
					consLeft = mulFirst.left;
				} else if (mulFirst.right instanceof ConstanteExpressionArithmetique) {
					consLeft = mulFirst.right;
				}

			}
			if (secondCarre instanceof Multiplication) {
				Multiplication mulScnd = (Multiplication) secondCarre;
				if (mulScnd.left instanceof ConstanteExpressionArithmetique) {
					consRight = mulScnd.left;
				} else if (mulScnd.right instanceof ConstanteExpressionArithmetique) {
					consRight = mulScnd.right;
				}
			}
			return new Multiplication(new Addition(
					new Multiplication(new Racine(consLeft).simplifier(), varSquared.left.getVarOnly()).simplifier(),
					new Multiplication(new Racine(consRight).simplifier(), varSquared.right.getVarOnly()).simplifier()),
					new Soustraction(
							new Multiplication(new Racine(consLeft).simplifier(), varSquared.left.getVarOnly())
									.simplifier(),
							new Multiplication(new Racine(consRight).simplifier(), varSquared.right.getVarOnly())
									.simplifier()));
		}

		return this;
	}

	@Override
	public ExpressionArithmetique getVarSquared() {
		if (this.right.getVarSquared() == null) {
			return this.left.getVarSquared();
		} else if (this.left.getVarSquared() == null) {
			return this.right.getVarSquared();
		}
		return new Addition(this.left.getVarSquared(), this.right.getVarSquared().setToOpposite());

	}

	@Override
	public ExpressionArithmetique getVar() {
		if (this.right.getVar() == null) {
			return this.left.getVar();
		} else if (this.left.getVar() == null) {
			return this.right.getVar().setToOpposite();
		}
		return new Addition(this.left.getVar(), this.right.getVar());

	}

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique valLeft,
			ConstanteExpressionArithmetique valRight) {
		if (valRight.isNegative()) {
			return new Addition(valLeft, valRight.setToOpposite());
		}else if(((valLeft instanceof ConstanteSymbolique)&&(valRight instanceof ConstanteSymbolique))&&(valLeft.afficher().equals(valRight.afficher()))) {
				return new ConstanteN(0);
		}else if(valLeft.isEqual(valRight.setToOpposite())) {
			return new Multiplication(new ConstanteN(2), valLeft);
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight) {

		if (valLeft.getValue() == 0) {
			return new ConstanteN(-valLeft.getValue());
		}
		return new ConstanteN(valLeft.getValue() - valRight.getValue());
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {
		long num = (valLeft.getNum() * valRight.getDenum()) - (valLeft.getDenum() * valRight.getNum());
		long denum = valLeft.getDenum() * valRight.getDenum();
		return new ConstanteQ(num, denum).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteQ valRight) {
		if (valLeft.getValue() == 0) {
			return new ConstanteQ(-valRight.getNum(), valRight.getDenum());
		}
		return new ConstanteQ((valLeft.getValue() * valRight.getDenum()) - valRight.getNum(), valRight.getDenum())
				.simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteN valRight) {
		if (valRight.getValue() == 0) {
			return valLeft;
		}
		return new ConstanteQ(valLeft.getNum() - (valRight.getValue() * valLeft.getDenum()), valLeft.getDenum())
				.simplifier();

	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ExpressionArithmetique valRight) {
		if (valLeft.getValue() == 0) {
			return valRight.setToOpposite().simplifier();
		} else if ((valRight instanceof ConstanteExpressionArithmetique)
				&& (((ConstanteExpressionArithmetique) valRight).isNegative())) {

			return new Addition(valLeft, ((ConstanteExpressionArithmetique) valRight).setToOpposite());
		}

		return this;
	}

	@Override
	public ConstanteD calculer() {

		return new ConstanteD(left.calculer().getDb() - right.calculer().getDb());
	}

	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {

		return new Soustraction(left.affecter(string, i), right.affecter(string, i));

	}

	@Override
	public ExpressionArithmetique associer() {
		if (this.left instanceof Addition) {
			Addition add = (Addition) this.left;
			return new Addition(add.left, new Soustraction(add.right, this.right));
		} else if (this.right instanceof Addition) {
			Addition add = (Addition) this.right;
			return new Addition(new Soustraction(this.left, add.left), add.right);
		} else if (this.left instanceof Soustraction) {
			Soustraction add = (Soustraction) this.left;
			return new Soustraction(add.left, new Soustraction(add.right, this.right));
		} else if (this.right instanceof Soustraction) {
			Soustraction add = (Soustraction) this.right;
			return new Soustraction(new Soustraction(this.left, add.left), add.right);
		}
		return this;
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new Soustraction(this.left.deriver(), this.right.deriver()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique valLeft, ConstanteN valRight) {
		if (valRight.getValue() == 0) {
			return valLeft.simplifier();
		} else if (valRight.getValue() < 0) {
			return new Addition(valLeft, valRight.setToOpposite());
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique matLeft, Matrice matRight) {
		ExpressionArithmetique[][] vals=new ExpressionArithmetique[matRight.getValues().length][matRight.getValues().length];
		for(int i=0;i<matRight.getValues().length;i++) {
			for(int y=0;y<matRight.getValues().length;y++) {
				vals[i][y]=new Soustraction(matLeft,matRight.getValues()[i][y]).simplifier();
			}
		}
		return new Matrice(vals);
	}

	@Override
	protected ExpressionArithmetique simplifier(Matrice matLeft, Matrice matRight) {
		ExpressionArithmetique[][] vals=new ExpressionArithmetique[matRight.getValues().length][matRight.getValues().length];
		for(int i=0;i<matRight.getValues().length;i++) {
			for(int y=0;y<matRight.getValues().length;y++) {
				vals[i][y]=new Soustraction(matLeft.getValues()[i][y],matRight.getValues()[i][y]).simplifier();
			}
		}
		return new Matrice(vals);
	}

	@Override
	protected ExpressionArithmetique simplifier(Matrice matLeft, ExpressionArithmetique matRight) {
		ExpressionArithmetique[][] vals=new ExpressionArithmetique[matLeft.getValues().length][matLeft.getValues().length];
		for(int i=0;i<matLeft.getValues().length;i++) {
			for(int y=0;y<matLeft.getValues().length;y++) {
				vals[i][y]=new Soustraction(matRight,matLeft.getValues()[i][y]).simplifier();
			}
		}
		return new Matrice(vals);	
	}

}
