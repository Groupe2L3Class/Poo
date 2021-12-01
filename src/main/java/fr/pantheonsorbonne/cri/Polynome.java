package fr.pantheonsorbonne.cri;


public class Polynome implements ExpressionArithmetique {

	ExpressionArithmetique varSquared;
	ExpressionArithmetique varNotSquared;
	ExpressionArithmetique constante;
	
	public Polynome(ExpressionArithmetique varSquared,ExpressionArithmetique varNotSquared,ExpressionArithmetique constante) {
		this.varSquared=varSquared;
		this.varNotSquared=varNotSquared;
		this.constante=constante;
	}
	
	public String resoudre() {
		String res ="";
		//recuperation coeffs
		ConstanteExpressionArithmetique coeffVarSquared=new ConstanteN(1);
		ConstanteExpressionArithmetique coeffVarNotSquared=new ConstanteN(1);
		if (varSquared instanceof Multiplication) {
			Multiplication mulVarSquared = (Multiplication) varSquared;
			if (mulVarSquared.left instanceof ConstanteExpressionArithmetique) {
				coeffVarSquared = (ConstanteExpressionArithmetique) mulVarSquared.left;
			} else if (mulVarSquared.right instanceof ConstanteExpressionArithmetique) {
				coeffVarSquared = (ConstanteExpressionArithmetique) mulVarSquared.right;
			}
		}
		
		if (varNotSquared instanceof Multiplication) {
			Multiplication mulVarNotSquared = (Multiplication) varNotSquared;
			if (mulVarNotSquared.left instanceof ConstanteExpressionArithmetique) {
				coeffVarNotSquared = (ConstanteExpressionArithmetique) mulVarNotSquared.left;
			} else if (mulVarNotSquared.right instanceof ConstanteExpressionArithmetique) {
				coeffVarNotSquared = (ConstanteExpressionArithmetique) mulVarNotSquared.right;
			}
		}
		
		ConstanteExpressionArithmetique delta=(ConstanteExpressionArithmetique) new Soustraction(new Puissance(coeffVarNotSquared,new ConstanteN(2)),
				new Multiplication(new ConstanteN(4),new Multiplication(coeffVarSquared,this.constante))).simplifier();
		
		
		
		
		
		if((delta instanceof ConstanteN)&&(((ConstanteN) delta).getValue()==0)) {
			ExpressionArithmetique solution=new Division(coeffVarNotSquared.setToOpposite(),
				new Multiplication(new ConstanteN(2),coeffVarSquared)).simplifier();
			res="Le Delta est égale à 0, l'equation admet une solution : x= "+solution.afficher();
			return res;
		}
		
		if(!(delta.isNegative())) {
			ExpressionArithmetique solution1=new Division(new Soustraction(coeffVarNotSquared.setToOpposite(),new Racine(delta)),
					new Multiplication(new ConstanteN(2),coeffVarSquared)).simplifier();
			ExpressionArithmetique solution2=new Division(new Addition(coeffVarNotSquared.setToOpposite(),new Racine(delta)),
					new Multiplication(new ConstanteN(2),coeffVarSquared)).simplifier();
			res="Le Delta est Positif, l'equation admet deux solutions : x1= "+solution1.afficher()+" x2= "+solution2.afficher();
		
			
		

		}else if(delta.isNegative()){
		
			ExpressionArithmetique solution1=new Division(new Soustraction(coeffVarNotSquared.setToOpposite(),new Multiplication(new ConstanteI(),new Racine(delta.setToOpposite()))),
					new Multiplication(new ConstanteN(2),coeffVarSquared)).simplifier();
			ExpressionArithmetique solution2=new Division(new Addition(coeffVarNotSquared.setToOpposite(),new Multiplication(new ConstanteI(),new Racine(delta.setToOpposite()))),
					new Multiplication(new ConstanteN(2),coeffVarSquared)).simplifier();
			res="Le Delta est Négatif, l'equation admet deux solutions complexes : x1= "+solution1.afficher()+" x2= "+solution2.afficher();
			
		}
		return res;
		
		
	}
	
	
	

	@Override
	public String afficher() {
		return new Addition(varSquared,new Addition(varNotSquared,constante)).simplifier().afficher();
	}

	@Override
	public ExpressionArithmetique simplifier() {
		this.varSquared=varSquared.simplifier();
		this.varNotSquared=varNotSquared.simplifier();
		this.constante=constante.simplifier();
		return this;
	}

	@Override
	public boolean isEqual(ExpressionArithmetique ea) {
		return ((ea instanceof Polynome)&&((varSquared.isEqual(((Polynome)ea).varSquared))&&
				(varNotSquared.isEqual(((Polynome)ea).varNotSquared))&&(constante.isEqual(((Polynome)ea).constante))));
	}

	@Override
	public ExpressionArithmetique distribuer() {
		return null;
	}

	@Override
	public ExpressionArithmetique associer() {
		return null;
	}

	@Override
	public ExpressionArithmetique developper() {
		return null;
	}

	@Override
	public ExpressionArithmetique setToOpposite() {
		this.varSquared=varSquared.setToOpposite();
		this.varNotSquared=varNotSquared.setToOpposite();
		this.constante=constante.setToOpposite();
		return this;

	}

	@Override
	public ExpressionArithmetique getVarSquared() {
		return this.varSquared.getVarSquared();
	}

	@Override
	public ExpressionArithmetique getVar() {
		return this.varNotSquared.getVar();
	}

	@Override
	public ExpressionArithmetique getVarOnly() {
		return this.varNotSquared.getVarOnly();
	}

	@Override
	public ConstanteD calculer() {
		return null;
	}

	@Override
	public ExpressionArithmetique affecter(String string, ExpressionArithmetique i) {
		this.varSquared=varSquared.affecter(string,i);
		this.varNotSquared=varNotSquared.affecter(string,i);
		return this;
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new Addition(this.varSquared,new Addition(this.varNotSquared,this.constante)).simplifier().deriver();
	}

	@Override
	public ExpressionArithmetique deriverN(int n) {
		return new Addition(this.varSquared,new Addition(this.varNotSquared,this.constante)).simplifier().deriverN(n);
	}

}
