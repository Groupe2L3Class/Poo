package fr.pantheonsorbonne.cri;



public class Division extends OpBinaire {

	public Division(ExpressionArithmetique left, ExpressionArithmetique right) {
        super(left, right, "/");
    }



	


	

	@Override
	public ConstanteD calculer() {
		return new ConstanteD((this.left.calculer().getDb()) / (this.right.calculer().getDb()) );
	}



	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight) {
		ConstanteQ cons = new ConstanteQ(valLeft.getValue(), valRight.getValue());
		return cons.simplifier();
	}
	
	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteQ valRight) {
		return new ConstanteQ(valLeft.getValue()*valRight.getDenum(), valRight.getNum()).simplifier();
		
		
	}
	 
	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteN valRight) {
		return new ConstanteQ(valLeft.getNum(), valLeft.getDenum()*valRight.getValue()).simplifier();
	}



	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {
		return new ConstanteQ(valLeft.getNum() * valRight.getDenum(), valLeft.getDenum() * valRight.getNum()).simplifier();
		
	}



	
	

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique valLeft, ConstanteN valRight) {
		if (valRight.getValue()==1) {
    		return valLeft.simplifier();
    	}
    	return this;
	}
	



	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ExpressionArithmetique valRight) {
		if (valLeft.getValue()==0) {
    		return valLeft.simplifier();
    	}
		
    	return this;

	}



	@Override
	public ExpressionArithmetique associer() {
		if (this.left instanceof Multiplication) {
			Multiplication add = (Multiplication) this.left;
			return new Division(add.left,new Multiplication(add.right, this.right));
		}else if (this.right instanceof Multiplication) {
			Multiplication add = (Multiplication) this.right;
			return new Multiplication(new Division(this.left,add.left),add.right);
		}else if (this.left instanceof Division) {
			Division add = (Division) this.left;
			return new Division(add.left,new Division(add.right, this.right));
		}else if (this.right instanceof Division) {
			Division add = (Division) this.right;
			return new Division(new Division(this.left,add.left),add.right);
		}
		return this;
		
	}



	@Override
	public ExpressionArithmetique deriver() {
		this.left = this.left.simplifier();
		this.right = this.right.simplifier();
		ExpressionArithmetique sous = new Soustraction (new Multiplication(this.left.deriver(),this.right),new Multiplication (this.left,right.deriver())).simplifier();
		return new Division (sous, new Puissance (this.right,new ConstanteN(2))).simplifier();
	}

	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		
		return new Division(left.affecter(string, i), right.affecter(string, i));
		
		
	}

	@Override
	public ExpressionArithmetique setToOpposite() {
		this.left=this.left.setToOpposite();
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ExpressionArithmetique matLeft, Matrice matRight) {
		return null;
	}



	@Override
	protected ExpressionArithmetique simplifier(Matrice matLeft, ExpressionArithmetique matRight) {
		return null;
	}
	
	 @Override
		protected ExpressionArithmetique simplifier(Matrice matLeft, Matrice matRight) { 	
			return this;
		}
		
}
