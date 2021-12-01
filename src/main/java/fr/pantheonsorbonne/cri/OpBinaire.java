package fr.pantheonsorbonne.cri;

import static fr.pantheonsorbonne.cri.Utils.*;



public abstract class OpBinaire implements ExpressionArithmetique {

	protected ExpressionArithmetique right;
	protected ExpressionArithmetique left;
	protected String symbol;
	

	protected OpBinaire(ExpressionArithmetique leftOp, ExpressionArithmetique rightOp, String symbol) {
		left = leftOp;
		right = rightOp;
		this.symbol = symbol;
	}
	
	
	//GETTERS SETTERS
	
	
	public ExpressionArithmetique getLeft() {
		return this.left;
	}
	
	public ExpressionArithmetique getRight() {
		return this.right;
	}
	
	
	@Override
	public String afficher() {
		return "(" + left.afficher() + this.symbol + right.afficher() + ")";
	}

	@Override
	public ExpressionArithmetique simplifier() {
		if(!(this.left instanceof Complexe)) {
			this.left = this.left.simplifier();
			this.right = this.right.simplifier();
		}
		if (this.left instanceof ConstanteN && this.right instanceof ConstanteN) {
			return simplifier(toN(this.left), toN(this.right));
		} else if (this.left instanceof Complexe && this.right instanceof Complexe) {
			return simplifier((Complexe) this.left,(Complexe) this.right);
		} else if (this.left instanceof Matrice && this.right instanceof Matrice) {
			return simplifier((Matrice)this.left,(Matrice)this.right);
		} else if (this.left instanceof ExpressionArithmetique && this.right instanceof Matrice) {
			return simplifier(this.left,(Matrice)this.right);
		} else if (this.left instanceof Matrice && this.right instanceof ExpressionArithmetique) {
			return simplifier((Matrice)this.left,this.right);
		} else if (this.left instanceof ConstanteQ && this.right instanceof ConstanteQ) {
			return simplifier(toQ(this.left), toQ(this.right));
		} else if (this.left instanceof ConstanteN && this.right instanceof ConstanteQ) {
			return simplifier(toN(this.left), toQ(this.right));
		} else if (this.left instanceof ConstanteQ && this.right instanceof ConstanteN) {
			return simplifier(toQ(this.left), toN(this.right));
		} else if (this.left instanceof ExpressionArithmetique && this.right instanceof ConstanteN) {
			return simplifier(this.left, toN(this.right));
		} else if (this.left instanceof ConstanteN && this.right instanceof ExpressionArithmetique) {
			return simplifier(toN(this.left), this.right);
		} else if (this.left instanceof ExpressionArithmetique && this.right instanceof ConstanteExpressionArithmetique) {
			return simplifier(this.left,(ConstanteExpressionArithmetique)this.right);
		} else if (this.left instanceof ConstanteExpressionArithmetique && this.right instanceof ExpressionArithmetique) {
			return simplifier((ConstanteExpressionArithmetique)this.left,this.right);
		} else if (this.left instanceof OpBinaire && this.right instanceof OpBinaire) {
			return simplifier((OpBinaire)this.left,(OpBinaire) this.right);
		}
		
		return this;
	}
	
	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.simplifier().afficher().equals(ea.simplifier().afficher());
	}
	
	@Override
	public ExpressionArithmetique deriverN(int n) {
    	ExpressionArithmetique deriv = this;
		for (int i=0; i<n;i++) {
			deriv =deriv.deriver();
		}
		return deriv;
		
	}
	@Override
	public ExpressionArithmetique setToOpposite() {
		this.left=this.left.setToOpposite();
		this.right=this.right.setToOpposite();
		return this;
	}
	

	
	protected ExpressionArithmetique simplifier(ConstanteQ toQ, ConstanteN toN) {
		return simplifier(toN(this.right), toQ(this.left));
	}
	
	protected abstract ExpressionArithmetique simplifier(Matrice matLeft, Matrice matRight);
	
	protected abstract ExpressionArithmetique simplifier(ExpressionArithmetique matLeft, Matrice matRight);
	protected abstract ExpressionArithmetique simplifier(Matrice matLeft, ExpressionArithmetique matRight);
	
	
	
	protected abstract ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight);

	protected abstract ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight);

	
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteQ valRight) {
		return simplifier(new ConstanteQ(valLeft.getValue(), 1), valRight);
	}
	
	protected ExpressionArithmetique simplifier(ExpressionArithmetique valLeft, ConstanteExpressionArithmetique valRight) {
		return this;
	}
	
	protected ExpressionArithmetique simplifier(ConstanteExpressionArithmetique valLeft, ExpressionArithmetique valRight) {
		return this;
	}
	
	protected ExpressionArithmetique simplifier(OpBinaire valLeft, OpBinaire valRight) {
		return this;
	}
	
	protected ExpressionArithmetique simplifier(Complexe valLeft, Complexe valRight) {
		return this;
	}
	
	
	
	protected abstract ExpressionArithmetique simplifier(ExpressionArithmetique valLeft, ConstanteN valRight);

	protected abstract ExpressionArithmetique simplifier(ConstanteN valLeft, ExpressionArithmetique valRight);

	
	@Override
	public ExpressionArithmetique developper() {
		return this;
	}
	
	@Override
	public ExpressionArithmetique associer() {
		return this;
	}
	
	
	@Override
	public ExpressionArithmetique distribuer() {
		return this;
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
    	 if((this.right.getVarOnly()!=null)&&(this.left.getVarOnly()==null)) {
	    		return this.right.getVarOnly();
	    	}else if(((this.left.getVarOnly()!=null)&&(this.right.getVarOnly()==null))) {
	    		return this.left.getVarOnly();
	    	}
		 return this;
	  }
    
    
    
		
}