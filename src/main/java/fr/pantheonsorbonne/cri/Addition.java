package fr.pantheonsorbonne.cri;

public class Addition extends OpBinaire {

	public Addition(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right, "+");
	}
	
	

	public ExpressionArithmetique factoriser() {
		if ((this.getVarSquared() != null) && (this.getVar() != null)) {
			// recuperation variables+coeffs
			ExpressionArithmetique varSquaredAndCoeff = this.getVarSquared();
			ExpressionArithmetique varNotSquaredAndCoeff = this.getVar();

			// recuperation coeffs
			ExpressionArithmetique consLeft = new ConstanteN(1);
			ExpressionArithmetique consRight = new ConstanteN(1);
			ExpressionArithmetique firstCarre = ((OpBinaire) varSquaredAndCoeff).left;
			ExpressionArithmetique secondCarre = ((OpBinaire) varSquaredAndCoeff).right;
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
			// constantes pour test le cas x(a+b)^2 avec x constante
			ExpressionArithmetique constAll = new Multiplication(new Multiplication(consLeft, consRight),
					new ConstanteN(2)).simplifier();

			// constantes pour test le cas (ax+by)^2 avec x,y constante
			ExpressionArithmetique constAllFacteurCoeff = new Addition(consLeft, consRight).simplifier();

			// Mutiplication pour vérifier l'égalité du cas (ax+by)^2
			Multiplication eaTest = new Multiplication(constAll,
					new Multiplication(firstCarre.getVarOnly(),
							(secondCarre.getVarOnly())).simplifier());

			// Mutiplication pour vérifier l'égalité du cas x(a+b)^2
			Multiplication eaTestFacteurCoeff = new Multiplication(constAllFacteurCoeff,
					new Multiplication(firstCarre.getVarOnly(),
							(secondCarre.getVarOnly())).simplifier());

			// On vérifie l'égalité et retourne la factorisation correspondante

			if ((eaTest.simplifier().isEqual(varNotSquaredAndCoeff))
					|| (eaTest.simplifier().associer().isEqual(varNotSquaredAndCoeff))) {
				return new Puissance(varSquaredAndCoeff, new ConstanteN(2));
			} else if (varSquaredAndCoeff instanceof Soustraction) {
				if ((eaTest.setToOpposite().simplifier().isEqual(varNotSquaredAndCoeff))
						|| (eaTest.setToOpposite().simplifier().associer().isEqual(varNotSquaredAndCoeff))) {
					return new Puissance(varSquaredAndCoeff, new ConstanteN(2));
				}
				eaTestFacteurCoeff.left = eaTestFacteurCoeff.left.setToOpposite();
			}
			if ((eaTestFacteurCoeff.simplifier().isEqual(varNotSquaredAndCoeff))
					|| (eaTestFacteurCoeff.simplifier().associer().isEqual(varNotSquaredAndCoeff))) {
				Multiplication mul = (Multiplication) varNotSquaredAndCoeff;
				Multiplication mulRight = (Multiplication) mul.right;
				ExpressionArithmetique varA = mulRight.left;
				ExpressionArithmetique varB = mulRight.right;
				if (varSquaredAndCoeff instanceof Soustraction) {
					eaTestFacteurCoeff.left = eaTestFacteurCoeff.left.setToOpposite();
					return new Multiplication(new Division(constAllFacteurCoeff, new ConstanteN(2)).simplifier(),
							new Puissance(new Soustraction(varA, varB), new ConstanteN(2)));
				}
				return new Multiplication(new Division(constAllFacteurCoeff, new ConstanteN(2)).simplifier(),
						new Puissance(new Addition(varA, varB), new ConstanteN(2)));
			}
		}
		return this;
	}

	@Override
	public ExpressionArithmetique getVarSquared() {
		if ((this.left instanceof Addition) || (this.right instanceof Addition)) {
			if (this.right.getVarSquared() == null) {
				return this.left.getVarSquared();
			} else if (this.left.getVarSquared() == null) {
				return this.right.getVarSquared();
			}
			return new Addition(this.left.getVarSquared(), this.right.getVarSquared());
		} else if ((((this.left instanceof Soustraction) || (this.right instanceof Soustraction))
				|| ((this.left instanceof Puissance) || (this.right instanceof Puissance)))
				|| ((this.left instanceof Multiplication) || (this.right instanceof Multiplication))) {
			if (this.right.getVarSquared() == null) {
				return this.left.getVarSquared();
			} else if (this.left.getVarSquared() == null) {
				return this.right.getVarSquared();
			}
			return new Soustraction(this.left.getVarSquared(), this.right.getVarSquared());
		}
		return this;

	}

	@Override
	public ExpressionArithmetique getVar() {
		if (this.right.getVar() == null) {
			return this.left.getVar();
		} else if (this.left.getVar() == null) {
			return this.right.getVar();
		}
		return new Addition(this.left.getVar(), this.right.getVar());

	}


	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique valLeft,
			ConstanteExpressionArithmetique valRight) {
		if (valRight.isNegative()) {
			return new Soustraction(valLeft, valRight.setToOpposite()).simplifier();
		} else if ((valLeft instanceof Multiplication)
				&& (((Multiplication) valLeft).left.getClass() == valRight.getClass())) {

			return new Multiplication(valRight, new Addition(((Multiplication) valLeft).right, new ConstanteN(1)))
					.simplifier();

		}else if(valRight.isEqual(valLeft)) {
			return new Multiplication(new ConstanteN(2), valRight);
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(OpBinaire valLeft, OpBinaire valRight) {
		if ((valLeft instanceof Multiplication) && (valRight instanceof Multiplication)) {
			Multiplication mulLeft = (Multiplication) valLeft;
			Multiplication mulRight = (Multiplication) valRight;
			if (mulLeft.left.isEqual(mulRight.left)) {
				return new Multiplication(mulLeft.left, new Addition(mulLeft.right, mulRight.right).simplifier());
			}
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ExpressionArithmetique valRight) {
		if (valLeft.getValue() == 0) {
			return valRight;
		}
		return this;
	}
	
	@Override
	protected ExpressionArithmetique simplifier(Complexe valLeft, Complexe valRight) {
		return new Complexe(new Addition(valLeft.getReal(),valRight.getReal()).simplifier(),new Addition(valLeft.getImaginary(),valRight.getImaginary()).simplifier());
	}

	protected ExpressionArithmetique simplifier(ExpressionArithmetique valLeft, ConstanteN valRight) {
		if (valRight.getValue() == 0) {
			return valLeft;
		} else if (valRight.getValue() < 0) {
			return new Soustraction(valLeft, valRight.setToOpposite());
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight) {
		return new ConstanteN(valLeft.getValue() + valRight.getValue());
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {
		long num = valLeft.getNum() * valRight.getDenum() + valLeft.getDenum() * valRight.getNum();
		long denum = valLeft.getDenum() * valRight.getDenum();
		return new ConstanteQ(num, denum).simplifier();
	}

	@Override
	public ConstanteD calculer() {
		return new ConstanteD(this.left.calculer().getDb() + this.right.calculer().getDb());
	}

	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		return new Addition(left.affecter(string, i), right.affecter(string, i));
	}

	@Override
	public ExpressionArithmetique associer() {
		if (this.left instanceof Addition) {
			Addition add = (Addition) this.left;
			if (add.right instanceof VariableSymbolique) {
				return new Addition(new Addition(add.left, this.right), add.right);
			}
			return new Addition(add.left, new Addition(add.right, this.right));
		} else if (this.right instanceof Addition) {
			Addition add = (Addition) this.right;
			if (add.left instanceof VariableSymbolique) {
				return new Addition(new Addition(add.right, this.left), add.left);
			}
			return new Addition(new Addition(this.left, add.left), add.right);
		} else if (this.left instanceof Soustraction) {
			Soustraction sous = (Soustraction) this.left;
			if (sous.right instanceof VariableSymbolique) {
				return new Soustraction(new Addition(sous.left, this.right), sous.right);
			}
			return new Addition(sous.left, new Soustraction(this.right, sous.right));

		} else if (this.right instanceof Soustraction) {
			Soustraction sous = (Soustraction) this.right;
			if (sous.left instanceof VariableSymbolique) {
				return new Addition(new Soustraction(this.left, sous.right), sous.left);
			}
			return new Soustraction(new Addition(this.left, sous.left), sous.right);
		}
		return this;

	}

	@Override
	public ExpressionArithmetique deriver() {
		return new Addition(this.left.deriver(), this.right.deriver()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique matLeft, Matrice matRight) {
		ExpressionArithmetique[][] vals=new ExpressionArithmetique[matRight.getValues().length][matRight.getValues().length];
		for(int i=0;i<matRight.getValues().length;i++) {
			for(int y=0;y<matRight.getValues().length;y++) {
				vals[i][y]=new Addition(matLeft,matRight.getValues()[i][y]).simplifier();
			}
		}
		return new Matrice(vals);
	}

	@Override
	protected ExpressionArithmetique simplifier(Matrice matLeft, Matrice matRight) {
		ExpressionArithmetique[][] vals=new ExpressionArithmetique[matRight.getValues().length][matRight.getValues().length];
		for(int i=0;i<matRight.getValues().length;i++) {
			for(int y=0;y<matRight.getValues().length;y++) {
				vals[i][y]=new Addition(matLeft.getValues()[i][y],matRight.getValues()[i][y]).simplifier();
			}
		}
		return new Matrice(vals);
	}

	@Override
	protected ExpressionArithmetique simplifier(Matrice matLeft, ExpressionArithmetique matRight) {
		ExpressionArithmetique[][] vals=new ExpressionArithmetique[matLeft.getValues().length][matLeft.getValues().length];
		for(int i=0;i<matLeft.getValues().length;i++) {
			for(int y=0;y<matLeft.getValues().length;y++) {
				vals[i][y]=new Addition(matRight,matLeft.getValues()[i][y]).simplifier();
			}
		}
		return new Matrice(vals);	
		
	}

}
