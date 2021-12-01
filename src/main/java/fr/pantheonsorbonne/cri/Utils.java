package fr.pantheonsorbonne.cri;

public abstract class Utils {

	
	protected Utils(){
		
	}
	
    public static ConstanteQ toQ(ExpressionArithmetique ea) {
        return (ConstanteQ) ea;
    }

    public static ConstanteN toN(ExpressionArithmetique ea) {
        return (ConstanteN) ea;
    }
   
}
