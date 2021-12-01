package fr.pantheonsorbonne.cri;



public class Multiplication extends OpBinaire {

	public Multiplication(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right, "*");

	}

	@Override
	public ExpressionArithmetique getVarSquared() {
		if ((((this.left instanceof ConstanteExpressionArithmetique) && (!(this.left instanceof VariableSymbolique))
				&& (!(this.left instanceof VariableSymboliqueIndice))) && (this.right instanceof Puissance))) {
			Puissance puissRight = (Puissance) this.right;
			if (puissRight.getVarOnly() != null) {
				return new Multiplication(this.left, puissRight.left);
			}

		}

		return null;

	}

	@Override
	public ExpressionArithmetique getVar() {
		if ((this.left.getVar() == null) && (this.right.getVar() != null)) {
			return new Multiplication(this.left, this.right.getVar());
		} else if ((this.left.getVar() != null) && (this.right.getVar() == null)) {
			return new Multiplication(this.left.getVar(), this.right);
		} else if ((this.right.getVar() != null) && (this.left.getVar() != null)) {
			return new Multiplication(this.left.getVar(), this.right.getVar());
		}
		return null;

	}

	@Override
	public ExpressionArithmetique setToOpposite() {
		this.left = this.left.setToOpposite();
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteExpressionArithmetique valLeft,
			ExpressionArithmetique valRight) {
		if (valRight instanceof Multiplication) {
			Multiplication mul = (Multiplication) valRight;
			if (mul.left instanceof Multiplication) {
				Multiplication mulLeft = (Multiplication) mul.left;
				if (mulLeft.left instanceof ConstanteExpressionArithmetique) {
					return new Multiplication(new Multiplication(this.left, mulLeft.left),
							new Multiplication(mulLeft.right, mul.right)).simplifier();
				}
			}
		}
		return this;
	}
	
	
	
	@Override
	protected ExpressionArithmetique simplifier(Complexe valLeft, Complexe valRight) {
		return new Complexe(new Soustraction(new Multiplication(valLeft.getReal(),valRight.getReal()),new Multiplication(valLeft.getImaginary(),valRight.getImaginary())),
				new Addition(new Multiplication(valLeft.getReal(),valRight.getImaginary()),new Multiplication(valRight.getReal(),valLeft.getImaginary()))).simplifier();
	}
	
	

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique valLeft, ConstanteN valRight) {
		if (valRight.getValue() == 1) {
			return valLeft.simplifier();
		}else if (valRight.getValue() == 0) {
			return new ConstanteN(0);
		}else if (valLeft instanceof Multiplication) {
			Multiplication mul = (Multiplication) valLeft;
			if ((mul.left instanceof ConstanteN) || (mul.left instanceof ConstanteQ)) {
				return new Multiplication(new Multiplication(mul.left, this.right), mul.right).simplifier();
			}
		}else if (valLeft instanceof ConstanteI){
   			if (valRight.getValue()==-1) {
   	   		   
   	       		return valLeft.setToOpposite();
   	   		}
   	   	} else if (((valLeft instanceof ConstanteExpressionArithmetique)&&((ConstanteExpressionArithmetique) valLeft).isNegative())&&(valRight.isNegative())) {
			return new Multiplication(((ConstanteExpressionArithmetique) valLeft).setToOpposite(),valRight.setToOpposite()).simplifier();
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(OpBinaire valLeft, OpBinaire valRight) {
		if ((valLeft instanceof Puissance) && (valRight instanceof Puissance)) {
			Puissance mulLeft = (Puissance) valLeft;
			Puissance mulRight = (Puissance) valRight;
			if (mulLeft.left.isEqual(mulRight.left)) {
				return new Puissance(mulLeft.left, new Addition(mulLeft.right, mulRight.right).simplifier());
			}
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique valLeft,
			ConstanteExpressionArithmetique valRight) {
		if (valLeft instanceof Multiplication) {
			Multiplication mul = (Multiplication) valLeft;
			if ((mul.left instanceof ConstanteN) || (mul.left instanceof ConstanteQ)) {
				return new Multiplication(new Multiplication(mul.left, this.right), mul.right);
			}
		} else if (valLeft instanceof Puissance) {
			if (((Puissance) valLeft).left.isEqual(valRight)) {
				return new Puissance(valRight, new Addition(((Puissance) valLeft).right, new ConstanteN(1)))
						.simplifier();
			}
		} else if ((valLeft instanceof ConstanteExpressionArithmetique)
				&& (((ConstanteExpressionArithmetique) valLeft).isNegative()) && (valRight.isNegative())) {

			if (valLeft instanceof VariableSymbolique) {
				return new Multiplication(((VariableSymbolique) valLeft).setToOpposite(), valRight.setToOpposite());
			} else if (valLeft instanceof ConstanteQ) {
				return new Multiplication(valRight.setToOpposite(), ((ConstanteQ) valLeft).setToOpposite());
			} else if (valLeft instanceof ConstanteN) {
				return new Multiplication(valRight.setToOpposite(), ((ConstanteN) valLeft).setToOpposite());
			} else if (valLeft instanceof ConstanteSymbolique) {
				return new Multiplication(valRight.setToOpposite(), ((ConstanteSymbolique) valLeft).setToOpposite());
			} else if (valLeft instanceof VariableSymboliqueIndice) {
				return new Multiplication(valRight.setToOpposite(), ((VariableSymbolique) valLeft).setToOpposite());
			}

		} else if ((valLeft instanceof ConstanteI)&& (valRight instanceof ConstanteI)) {
			return new ConstanteN(-1);
		} else if (((valLeft instanceof ConstanteExpressionArithmetique)&&((ConstanteExpressionArithmetique) valLeft).isNegative())&&(valRight.isNegative())) {
			return new Multiplication(((ConstanteExpressionArithmetique) valLeft).setToOpposite(),valRight.setToOpposite());
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ExpressionArithmetique valRight) {
		if (valLeft.getValue() == 1) {
			return valRight.simplifier();
		}else if (valLeft.getValue() == 0) {
			return new ConstanteN(0);
		}else if ((valRight instanceof ConstanteI)&&(valLeft.getValue()==-1)) {
   			 
   			
       		return valRight.setToOpposite().simplifier();
       	
   		}
		return simplifier((ConstanteExpressionArithmetique) this.left, this.right);

	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight) {

		return new ConstanteN(valLeft.getValue() * valRight.getValue());
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {

		return new ConstanteQ(valLeft.getNum() * valRight.getNum(), valLeft.getDenum() * valRight.getDenum())
				.simplifier();
	}

	@Override
	public String afficher() {
		if ((((this.left instanceof ConstanteN) || (this.left instanceof ConstanteQ))
				&& ((this.right instanceof ConstanteSymbolique) || (this.right instanceof VariableSymbolique))
				|| (((this.right instanceof ConstanteN) || (this.right instanceof ConstanteQ))
						&& ((this.left instanceof ConstanteSymbolique)
								|| (this.left instanceof VariableSymbolique))))) {
			if ((this.left instanceof ConstanteN) || (this.left instanceof ConstanteQ)) {
				return "(" + left.afficher() + right.afficher() + ")";
			} else {
				return "(" + right.afficher() + left.afficher() + ")";
			}
		} else {
			return "(" + left.afficher() + this.symbol + right.afficher() + ")";
		}
	}

	@Override
	public ConstanteD calculer() {

		return new ConstanteD(left.calculer().getDb() * right.calculer().getDb());
	}

	@Override
	public ExpressionArithmetique distribuer() {
		if (right instanceof Addition) {
			return new Addition(new Multiplication(left, ((Addition) right).left),
					new Multiplication(left, ((Addition) right).right));
		} else if (left instanceof Addition) {
			return new Addition(new Multiplication(right, ((Addition) left).left),
					new Multiplication(right, ((Addition) left).right));
		} else if (right instanceof Soustraction) {
			return new Soustraction(new Multiplication(left, ((Soustraction) right).left),
					new Multiplication(left, ((Soustraction) right).right));
		} else if (left instanceof Soustraction) {
			return new Soustraction(new Multiplication(right, ((Soustraction) left).left),
					new Multiplication(right, ((Soustraction) left).right));
		}
		return this;

	}

	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {

		return new Multiplication(left.affecter(string, i), right.affecter(string, i));

	}

	@Override
	public ExpressionArithmetique associer() {
		if (this.left instanceof Multiplication) {
			Multiplication add = (Multiplication) this.left;
			return new Multiplication(add.left, new Multiplication(add.right, this.right));
		} else if (this.right instanceof Multiplication) {
			Multiplication add = (Multiplication) this.right;
			return new Multiplication(new Multiplication(this.left, add.left), add.right);
		} else if (this.left instanceof Division) {
			Division add = (Division) this.left;
			return new Multiplication(add.left, new Division(add.right, this.right));
		} else if (this.right instanceof Division) {
			Division add = (Division) this.right;
			return new Division(new Multiplication(this.left, add.left), add.right);
		}
		return this;

	}

	@Override
	public ExpressionArithmetique deriver() {
		this.left = this.left.simplifier();
		this.right = this.right.simplifier();
		if ((this.left instanceof ConstanteN) || (this.left instanceof ConstanteQ)
				|| (this.left instanceof ConstanteSymbolique)) {
			return new Multiplication(this.left, this.right.deriver()).simplifier();

		}
		if ((this.right instanceof ConstanteN) || (this.right instanceof ConstanteQ)
				|| (this.left instanceof ConstanteSymbolique)) {
			return new Multiplication(this.left.deriver(), this.right).simplifier();

		}
		return new Multiplication(this.left.deriver(), this.right.deriver()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique matLeft, Matrice matRight) {
		ExpressionArithmetique[][] vals = new ExpressionArithmetique[matRight.getValues().length][matRight
				.getValues().length];
		for (int i = 0; i < matRight.getValues().length; i++) {
			for (int y = 0; y < matRight.getValues().length; y++) {
				vals[i][y] = new Multiplication(matLeft, matRight.getValues()[i][y]).simplifier();
			}
		}
		return new Matrice(vals);
	}

	@Override
	protected ExpressionArithmetique simplifier(Matrice matLeft, Matrice matRight) {
		ExpressionArithmetique[][] vals = new ExpressionArithmetique[matRight.getValues().length][matRight
				.getValues().length];
		
		ExpressionArithmetique currValue=null;
		for (int i = 0; i < matRight.getValues().length; i++) {
			for (int y = 0; y < matRight.getValues().length; y++) {
				vals[i][y]=new ConstanteN(0);
				for (int c = 0; c < matRight.getValues().length; c++) {
					currValue=new Multiplication(matLeft.getValues()[i][c], matRight.getValues()[c][y]).simplifier();
					vals[i][y] = new Addition(vals[i][y],currValue).simplifier();
				}
				
			}
		}
		return new Matrice(vals);
	}

	@Override
	protected ExpressionArithmetique simplifier(Matrice matLeft, ExpressionArithmetique matRight) {
		ExpressionArithmetique[][] vals = new ExpressionArithmetique[matLeft.getValues().length][matLeft
				.getValues().length];
		for (int i = 0; i < matLeft.getValues().length; i++) {
			for (int y = 0; y < matLeft.getValues().length; y++) {
				vals[i][y] = new Multiplication(matRight, matLeft.getValues()[i][y]).simplifier();
			}
		}
		return new Matrice(vals);
	}

}
