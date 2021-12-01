package fr.pantheonsorbonne.cri;

public class ConstanteQ extends ConstanteExpressionArithmetique {
    private long num;
    private long denum;
    public ConstanteQ(long num, long denum) {
    	if((num<0)&&(denum<0)) {
    		this.num=Math.abs(num);
    		this.denum=Math.abs(denum);
    	}
        this.num = num;
        this.denum = denum;
    }
    
    
   
    public long getNum() {
        return num;
    }
    public long getDenum() {
        return denum;
    }
    
    private static long pgcd(long n1, long n2) {
    	n1=Math.abs(n1);
    	n2=Math.abs(n2);
        while (n1 != n2) {
            if (n1 > n2)
                n1 -= n2;
            else
                n2 -= n1;
        }
        return n2;
    }
    
    @Override
	public ExpressionArithmetique setToOpposite() {
    	return new ConstanteQ(-this.num,this.denum);
    }
    
    @Override
    public String afficher() {
        return "(" + getNum() + "/" + getDenum() + ")";
    }
    @Override
    public ExpressionArithmetique simplifier() {
    	if(this.num==0) {
    		return new ConstanteN(0);
    	}
    	
    	
    	
        long pgcd = pgcd(this.num, this.denum);
        if (pgcd == 1) {
        	if(this.denum==1) {
        		return new ConstanteN(this.num);
        	}
            return super.simplifier();
        } else if (this.denum / pgcd == 1) {
            return new ConstanteN(this.num / pgcd);
        } else {
        	
            return new ConstanteQ(getNum() / pgcd, getDenum() / pgcd);
        }
    }
    
   
    public ConstanteD calculer() {
    	
    	
    		double db = ((num)/((double) denum));
    	
    		return new ConstanteD( db );
    	
    }
    
    public boolean isNegative() {
    	return (((denum<0)&&(num>0))||((num<0)&&(denum>0)));
    }
    	
    
	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return this.simplifier().afficher().equals(ea.simplifier().afficher());
	}
	
	
}