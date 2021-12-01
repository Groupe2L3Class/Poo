package fr.pantheonsorbonne.cri;

public class Somme extends OpNaire {

	public Somme(VariableSymbolique vs, int indexDeb, int indexFin, ExpressionArithmetique ea) {
		super(vs, indexDeb, indexFin, "Σ", ea);

	}
	
	
	@Override
	public ExpressionArithmetique developper() {
		int deb=this.indexDeb;
		if(this.vs==null) {
			if (deb < indexFin) {
				return new Addition(this.ea,new Somme(null, ++deb, this.indexFin, this.ea).developper());
			}
			return this.ea;
		}
		
		if (deb < indexFin) {
			return new Addition(this.ea.affecter(this.vs.getSymbole(),new ConstanteN(deb)),new Somme(this.vs, ++deb, this.indexFin, this.ea).developper());
			}
			return this.ea.affecter(this.vs.getSymbole(),new ConstanteN(deb));
		
		
	}

}
