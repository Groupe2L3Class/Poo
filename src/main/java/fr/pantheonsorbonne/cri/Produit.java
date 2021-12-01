package fr.pantheonsorbonne.cri;


public class Produit extends OpNaire {

	public Produit(VariableSymbolique vs, int indexDeb, int indexFin, ExpressionArithmetique ea) {
		super(vs, indexDeb, indexFin, "𝚷" , ea);
	}
	
	
	@Override
	public ExpressionArithmetique developper() {
		int deb=this.indexDeb;
		if(this.vs==null) {
			if (deb < indexFin) {
				return new Multiplication(this.ea,new Produit(null, ++deb, this.indexFin, this.ea).developper());
			}
			return this.ea;
		}
		
		if (deb < indexFin) {
			return new Multiplication(this.ea.affecter(this.vs.getSymbole(),new ConstanteN(deb)),new Produit(this.vs, ++deb, this.indexFin, this.ea).developper());
			}
			return this.ea.affecter(this.vs.getSymbole(),new ConstanteN(deb));
		
		
	}



	

}
