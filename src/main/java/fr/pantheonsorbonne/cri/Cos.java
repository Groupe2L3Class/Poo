package fr.pantheonsorbonne.cri;

public class Cos extends OpUnaire {

    public Cos(ExpressionArithmetique op) {
        super(op, "cos");
    }


    @Override
    public ExpressionArithmetique simplifier() {
        ExpressionArithmetique val = this.value.simplifier();
        if (val instanceof ConstanteSymbolique) {
            ConstanteSymbolique cons = (ConstanteSymbolique) val;
            if (cons.constant == ConstanteSymbolique.ConstantesSymboliqueConnues.PI)
                return new ConstanteN(-1);
        } else if (val instanceof Multiplication) {
        	Multiplication multi = (Multiplication) val;
        	multi.left.simplifier();
        	multi.right.simplifier();
        	if((multi.left instanceof ConstanteN)&&(multi.right instanceof ConstanteSymbolique)) {
        		ConstanteN leftC = (ConstanteN) multi.left;
        		ConstanteSymbolique rightC = (ConstanteSymbolique) multi.right;
        		if ((Math.abs(leftC.getValue())%2==0)&&(rightC.constant.getStrPresentation().equals("π"))) {
        			return new ConstanteN(1);
        		}else if ((Math.abs(leftC.getValue())%2==1)&&(rightC.constant.getStrPresentation().equals("π"))) {
        			return new ConstanteN(-1);
        		}
        	}else if((multi.right instanceof ConstanteN)&&(multi.left instanceof ConstanteSymbolique)) {
        		ConstanteN rightC = (ConstanteN) multi.right;
        		ConstanteSymbolique leftC = (ConstanteSymbolique) multi.left;
        		if ((Math.abs(rightC.getValue())%2==0)&&(leftC.constant.getStrPresentation().equals("π"))) {
        			return new ConstanteN(1);
        		}else if ((Math.abs(rightC.getValue())%2==1)&&(leftC.constant.getStrPresentation().equals("π"))) {
        			return new ConstanteN(-1);
        		}
        	}else if((multi.left instanceof ConstanteQ)&&(multi.right instanceof ConstanteSymbolique)) {
        		ConstanteQ leftC = (ConstanteQ) multi.left;
        		ConstanteSymbolique rightC = (ConstanteSymbolique) multi.right;
        		if((rightC.constant.getStrPresentation().equals("π"))&&((Math.abs(leftC.getNum())%2==1)&&(leftC.getDenum()==2))) {
        			return new ConstanteN(0);
        		}
        	}else if((multi.right instanceof ConstanteQ)&&(multi.left instanceof ConstanteSymbolique)) {
        		ConstanteQ rightC = (ConstanteQ) multi.right;
        		ConstanteSymbolique leftC = (ConstanteSymbolique) multi.left;
        		if((leftC.constant.getStrPresentation().equals("π"))&&((Math.abs(rightC.getNum())%2==1)&&(rightC.getDenum()==2))) {
        			return new ConstanteN(0);
        		}
        	}
        }
        if (val instanceof ConstanteN) {
            ConstanteN value = (ConstanteN) val;
            if (value.getValue() == 0)
                return new ConstanteN(1);
        }
        
        return new Cos(val);
        
    }
    
    public ConstanteD calculer() {	
    	if (this.simplifier().isEqual(new ConstanteN(0))) {
    		return new ConstanteD(0.0);
    		
    	}
    		return new ConstanteD( Math.cos(this.value.calculer().getDb()) );
    	
    }

	
	
	public ExpressionArithmetique deriver() {
		return new Multiplication( new ConstanteN(-1),new Multiplication(this.value.deriver(), new Sin(this.value))).simplifier();
	}


	
	
	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		
		ExpressionArithmetique val=this.value.affecter(string, i);
		return new Cos(val);
		
		
	}
	
	

	
}
