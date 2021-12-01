package fr.pantheonsorbonne.cri;
public class Sin extends OpUnaire {
    public Sin (ExpressionArithmetique op) {
        super(op, "sin");
    }
    @Override
    public ExpressionArithmetique simplifier() {
        ExpressionArithmetique val = this.value.simplifier();
        if (val instanceof ConstanteSymbolique) {
            ConstanteSymbolique cons = (ConstanteSymbolique) val;
            if (cons.constant == ConstanteSymbolique.ConstantesSymboliqueConnues.PI)
                return new ConstanteN(0);
        }else if (val instanceof Multiplication) {
        	Multiplication multi = (Multiplication) val;
        	multi.left.simplifier();
        	multi.right.simplifier();
        	
        	if((multi.left instanceof ConstanteN)&&(multi.right instanceof ConstanteSymbolique)) {
        		ConstanteSymbolique rightC = (ConstanteSymbolique) multi.right;
        		if(rightC.constant.getStrPresentation().equals("π")) {
        			return new ConstanteN(0);
        		}
        	}else if((multi.right instanceof ConstanteN)&&(multi.left instanceof ConstanteSymbolique)) {
        		ConstanteSymbolique leftC = (ConstanteSymbolique) multi.left;
        		if(leftC.constant.getStrPresentation().equals("π")) {
        			return new ConstanteN(0);
        		}
        	}else if((multi.left instanceof ConstanteQ)&&(multi.right instanceof ConstanteSymbolique)) {
        		ConstanteQ leftC = (ConstanteQ) multi.left;
        		ConstanteSymbolique rightC = (ConstanteSymbolique) multi.right;
        		if((rightC.constant.getStrPresentation().equals("π"))&&((Math.abs(leftC.getNum())%2==1)&&(leftC.getDenum()==2))) {
        			if(leftC.getNum()>0) {
        				return new ConstanteN(-1);
        			}else if(leftC.getNum()<0) {
        				return new ConstanteN(1);
        			}
        		}
        	}else if((multi.right instanceof ConstanteQ)&&(multi.left instanceof ConstanteSymbolique)) {
        		ConstanteSymbolique leftC = (ConstanteSymbolique) multi.left;
        		ConstanteQ rightC = (ConstanteQ) multi.right;
        		if((leftC.constant.getStrPresentation().equals("π"))&&((Math.abs(rightC.getNum())%2==1)&&(rightC.getDenum()==2))) {
        			if(rightC.getNum()>0) {
        				return new ConstanteN(-1);
        			}else if(rightC.getNum()<0) {
        				return new ConstanteN(1);
        			}
        		}
        	}
        } else if (val instanceof ConstanteN) {
            ConstanteN value = (ConstanteN) val;
            if (value.getValue() == 0)
                return new ConstanteN(0);
        }
        return new Sin(val);
    }
    
    
    @Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		
		ExpressionArithmetique val=this.value.affecter(string, i);
		return new Sin(val);
		
		
	}
    
    public ConstanteD calculer() {
    	if (this.simplifier().isEqual(new ConstanteN(0))) {
    		return new ConstanteD(0.0);
    		
    	}
   
    	
        return new ConstanteD( Math.sin(this.value.calculer().getDb()) );
    	
    }
    	
	
	
	
	
	@Override
	public ExpressionArithmetique deriver() {
		return new Multiplication(this.value.deriver(), new Cos(this.value)).simplifier();
		
	}
		
	
    

}

