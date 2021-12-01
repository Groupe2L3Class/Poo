package fr.pantheonsorbonne.cri;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream; //permet d’ouvrir un flux de lecture binaire sur un fichier.
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;//manipuler un flux binaire sous la forme d’un flux caractères
import java.io.LineNumberReader;//compter les lignes lors de la lecture d’un flux caractères.


import java.nio.charset.StandardCharsets;

public class Latex  implements Closeable {
	
	private static  LineNumberReader source ;
	static StringBuilder texte = new StringBuilder();
	static int index;
	static  String line;
	

	

	public Latex(File file) throws IOException {
		try {
			source = new LineNumberReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static  String[] tab() throws IOException {
		String ligne;
		String[] tableau=new String[100];
		int nbr =0;
		
			while ((ligne = source.readLine()) != null) {
				  tableau[nbr]=ligne;
				  nbr++;
				}
		
		return tableau;
	}
	
	public String afficheLigne(int nbr) throws IOException {
		String[] tab= tab();
        StringBuilder texteSb = new StringBuilder();
        texteSb.append(tab[nbr]);
		return texteSb.toString();
	}
	
	
	public ExpressionArithmetique parcours(String line) throws IOException {
		String l = null;
		ExpressionArithmetique ea = null;
		for (int i=line.length()-1 ; i>=0; i--) {
			char c=line.charAt(i);
			//verifie si c'est un nombre
			if(Character.isDigit(line.charAt(i))) {
				int j =i+1;
				while(i!=0 && Character.isDigit(line.charAt(i-1))) {
					i--;
				}
				int num =Integer.parseInt(line.substring(i, j));
				
				if(ea==null) {
					ea = new ConstanteN(num);
				}
			
			}
			else {
				l= op(line, i);
				ExpressionArithmetique db;
				int nbr= lastNombre(line, i);
				String avantOp = op(line, i-(l.length()));
				if(ea!=null) {
					
					if("variable".equals(avantOp)){
						String v=Character.toString(line.charAt(i-1));
					    db=new VariableSymbolique(v);
					}
					else if("\\pi".equals(avantOp)) {
						db= ConstanteSymbolique.pi;
					}
					else if(")".equals(avantOp)) {
						int deb= derniereOp(line, i, '(');
						String entre= line.substring(deb, i);
						db= parcours(entre);
					}
					else {
						db=new ConstanteN(nbr);
					}
					
					if(l.equals("\\times")) {
						ea = new Multiplication (db, ea );
						i=i-l.length();
					}
					else if(l.equals("+")) {
						
						ea = new Addition (db, ea );
						i=i-l.length();
					}
					else if(l.equals("-")) {
						
							
							ea = new Soustraction (db, ea );
											
							i=i-l.length();
					}
					
					else if(c=='}') {
						int par = derniereOp(line, i, '{');
						String entre= line.substring(par, i);
						if(op(line, i-entre.length()-1).equals("\\frac{")) {
							ea= new Division(parcours(entre), ea);
						}
						else if(op(line, i-entre.length()-1).equals("\\sqrt{")) {
							ea= new Racine(parcours(entre));
						}
						i=i-entre.length()-6;
					}
					
					else if(c=='ˆ') {
						
						ea=	new Puissance (db, ea );
												
						i=i-l.length();
					}
					else if(l.equals("espace")) {
						if(line.lastIndexOf("\\sum")!=-1) {
							int fin=lastNombre(line, i);
							int debut =lastNombre(line, i-3);
							int indexDebut=line.indexOf(Integer.toString(debut));
							if("=".equals(op(line, indexDebut))) {
								String v=Character.toString(line.charAt(indexDebut-2)); 
								ea= new Somme(new VariableSymbolique(v),debut,fin ,ea);
							}
							i=line.indexOf("\\sum");
						}
					}
					else if(l.equals("\\cos")) {
						ea= new Cos(ea);
						i-=4;
					}
					else if(l.equals("\\sin")) {
						ea= new Sin(ea);
						i-=3;
					}
					else if(l.equals("\\sqrt{")) {
						ea= new Racine(ea);				
						i-=5;
					}
					else if(l.equals("\\ln")) {
						ea= new Ln(ea);
						i-=2;
					}
					else if(l.equals("\\exp")) {
						ea= new Exponentielle(ea);
						i-=3;
					}
					
					
				}	
				else {
					if(l.equals("}")) {
						int par = derniereOp(line, i, '{');
						String entre= line.substring(par, i);
						ea= parcours(entre);
						i=i-entre.length();
					}
					else if(l.equals("variable")) {
						ea = new VariableSymbolique(Character.toString(c));
					}
					else if(l.equals("\\pi")) {
						ea=ConstanteSymbolique.pi;
					}
					else if(l.equals(")") ) {
						int deb= derniereOp(line, i, '(');
						String entre= line.substring(deb, i);
						ea= parcours(entre);
						i=i-entre.length()-1;
						
					}
					
				}
			}
				
			}
		
		return ea;
	}
	
	
	public static int derniereOp(String line, int nbr, char c) {
		for (int i=nbr ; i>=0; i--) {
			if(line.charAt(i)==c) {
				return i+1;
				}
		}
		return 0;
	
	}
	
	public static String op(String line, int nbr) {
		for (int i=nbr ; i>=0; i--) {
			if(! Character.isDigit(line.charAt(i))) {
				int j =i+1;
				if(line.charAt(i)=='x' && line.charAt(i-1)!='e' || line.charAt(i)=='y'  ) {
					return  "variable";
				}
				if(line.charAt(i)==' ') {
					return  "espace";
				}
				
				while(i!=0 && ! Character.isDigit(line.charAt(i-1)) && line.charAt(i)!='\\'&& !op(line, i-1).equals("variable")
						&& line.charAt(i)!='}' && line.charAt(i)!=')') {
					i--;
				}
				return line.substring(i, j);
			}
		}
		return null;
	
	}

	
	public static int lastNombre(String line, int nbr) {
		for (int i=nbr ; i>=0; i--) {
			//verifie si c'est un nombre
			if(Character.isDigit(line.charAt(i))) {
				int j =i+1;
				while(i!=0 && Character.isDigit(line.charAt(i-1))) {
					i--;
				}
			
				return Integer.parseInt(line.substring(i, j));
			}
		}
		return 0;
	}
	
	
	@Override
	public void close() throws IOException {
		source.close();
		
	}

}
