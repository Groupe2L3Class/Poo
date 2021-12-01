package fr.pantheonsorbonne.cri;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ConstanteD extends ConstanteExpressionArithmetique {
	
	private double db;
	
	public ConstanteD(double db) {
        this.db = db;
    }
	
	public double getDb() {
		return this.db;
	}
	
	
	
	@Override
	public String afficher() {
		if(this.db==0) {
			return "0.0000";
		}
		Locale currentLocale = Locale.getDefault();
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
		otherSymbols.setDecimalSeparator('.');
		
		DecimalFormat df = new DecimalFormat("0.0000",otherSymbols);
		return df.format(db);
	}
	@Override
	public ConstanteD calculer() {
		return this;
	}
	@Override
	public ExpressionArithmetique setToOpposite() {
    	return new ConstanteD(-db);
    }
	
	
	 public boolean isNegative() {
	    	return (this.db<0);
	    }
	
	
}