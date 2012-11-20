/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.si;

import org.apache.commons.digester.xmlrules.FromXmlRuleSet;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import unmsm.fisi.tesis.dao.CromosomaDAO;
import unmsm.fisi.tesis.entidad.Cromosoma;

/**
 *
 * @author Jhon
 */
public class Operadores {

	private Cromosoma cromosoma_hijo1;
	private Cromosoma cromosoma_hijo2;
	private Cromosoma cromosoma_mutado;
	
	
	
	public Cromosoma getCromosoma_hijo1() {
		return cromosoma_hijo1;
	}

	public void setCromosoma_hijo1(Cromosoma cromosoma_hijo1) {
		this.cromosoma_hijo1 = cromosoma_hijo1;
	}

	public Cromosoma getCromosoma_hijo2() {
		return cromosoma_hijo2;
	}

	public void setCromosoma_hijo2(Cromosoma cromosoma_hijo2) {
		this.cromosoma_hijo2 = cromosoma_hijo2;
	}

	public Cromosoma getCromosoma_mutado() {
		return cromosoma_mutado;
	}

	public void setCromosoma_mutado(Cromosoma cromosoma_mutado) {
		this.cromosoma_mutado = cromosoma_mutado;
	}

	
//	
//	public Cromosoma[] crossover(Cromosoma crom_1,Cromosoma crom_2,StringBuilder seguimiento){
//		
//		//Cromosoma 1
//		int genes_1[][] =(crom_1.getConocimiento()).clone();
//		//Cromosoma 2
//		int genes_2[][] = crom_2.getConocimiento().clone();
//		
//		double temp1= (double) (Math.random()*(genes_1[0].length));
//		int puntoCorteColumna = (int)temp1 ;
//		
//		double temp2= (double) (Math.random()*(genes_1.length));
//		int puntoCorteFila = (int)temp2 ;
//		
//		puntoCorteColumna = 5;
//		puntoCorteFila = 12;
//		
//		System.out.println("Punto Corte---> C["+puntoCorteFila+","+puntoCorteColumna+"]");
//		seguimiento.append("Punto Corte---> C["+puntoCorteFila+","+puntoCorteColumna+"]");
//		int temp=0;
//	
//		for (int i = 0; i < puntoCorteFila ; i++) {
//			for (int j = puntoCorteColumna ; j < genes_1[0].length  ; j++) {
//				
//				temp = genes_1[i][j];
//				genes_1[i][j]= genes_2[i][j];
//				genes_2[i][j] = temp;
//				
//			}
//		}
//		
//		for (int i = puntoCorteFila; i < genes_1.length ; i++) {
//			for (int j = 0 ; j < puntoCorteColumna  ; j++) {
//				
//				temp = genes_1[i][j];
//				genes_1[i][j]= genes_2[i][j];
//				genes_2[i][j] = temp;
//				
//			}
//		}
//		cromosoma_hijo1 = new Cromosoma();
//		cromosoma_hijo1.setConocimiento(genes_1);
//		cromosoma_hijo2 = new Cromosoma();
//		cromosoma_hijo2.setConocimiento(genes_2);
//		
//		Cromosoma[] cromosomas=new Cromosoma[2];
//		cromosomas[0]= cromosoma_hijo1;
//		cromosomas[1]= cromosoma_hijo2;
//
//		return cromosomas;
//		
//	}
	
	public Cromosoma[] crossover(Cromosoma crom_padre,Cromosoma crom_madre,StringBuilder seguimiento, int generacionActual){
		
		
		int colum = crom_padre.getConocimiento()[0].length;
		int filas = crom_madre.getConocimiento().length;
		double numAleatorio;
		int puntoCorteColumna,puntoCorteFila;
		
		int crom_primer_hijo[][]  = new int[filas][colum];
		int crom_segundo_hijo[][] = new int[filas][colum];
		
		numAleatorio = (double) (Math.random()*(colum));
		puntoCorteColumna = (int)numAleatorio ;
		
		numAleatorio= (double) (Math.random()*(filas));
		puntoCorteFila = (int)numAleatorio ;
		
		//puntoCorteColumna = 5;
		//puntoCorteFila = 12;
		
		System.out.println("Punto Corte---> C["+puntoCorteFila+","+puntoCorteColumna+"]");
		seguimiento.append("Punto Corte---> C["+puntoCorteFila+","+puntoCorteColumna+"]");
		
	
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < colum; j++) {
				
				if(i < puntoCorteFila){
					
					if(j < puntoCorteColumna){
						crom_primer_hijo[i][j]  = crom_padre.getConocimiento()[i][j];
						crom_segundo_hijo[i][j] = crom_madre.getConocimiento()[i][j];
					}else{
						crom_primer_hijo[i][j]  = crom_madre.getConocimiento()[i][j];
						crom_segundo_hijo[i][j] = crom_padre.getConocimiento()[i][j];
					}
					
				}else{
					
					if(j<puntoCorteColumna){
						crom_primer_hijo[i][j]  = crom_madre.getConocimiento()[i][j];
						crom_segundo_hijo[i][j] = crom_padre.getConocimiento()[i][j];
					}else{
						crom_primer_hijo[i][j]  = crom_padre.getConocimiento()[i][j];
						crom_segundo_hijo[i][j] = crom_madre.getConocimiento()[i][j];
					}
				}
			
			}
		}
		
		
		cromosoma_hijo1 = new Cromosoma();
		cromosoma_hijo1.setConocimiento(crom_primer_hijo);
		cromosoma_hijo1.setParentesco("Hijo");
		cromosoma_hijo1.setNumeroCromosoma(Constantes.NUMERO_CROMOSOMAS);
		cromosoma_hijo1.setGeneracionNacimiento(generacionActual);
		cromosoma_hijo1.setPadre(crom_padre.getGeneracionNacimiento()+"-"+crom_padre.getNumeroCromosoma());
		cromosoma_hijo1.setMadre(crom_madre.getGeneracionNacimiento()+"-"+crom_madre.getNumeroCromosoma());
		Constantes.NUMERO_CROMOSOMAS++;
		
		cromosoma_hijo2 = new Cromosoma();
		cromosoma_hijo2.setConocimiento(crom_segundo_hijo);
		cromosoma_hijo2.setParentesco("Hijo");
		cromosoma_hijo2.setNumeroCromosoma(Constantes.NUMERO_CROMOSOMAS);
		cromosoma_hijo2.setGeneracionNacimiento(generacionActual);
		cromosoma_hijo2.setPadre(crom_padre.getGeneracionNacimiento()+"-"+crom_padre.getNumeroCromosoma());
		cromosoma_hijo2.setMadre(crom_madre.getGeneracionNacimiento()+"-"+crom_madre.getNumeroCromosoma());
		Constantes.NUMERO_CROMOSOMAS++;
		
		Cromosoma[] cromosomasHijos=new Cromosoma[2];
		cromosomasHijos[0]= cromosoma_hijo1;
		cromosomasHijos[1]= cromosoma_hijo2;
	
		return cromosomasHijos;
	
   }
	
	public Cromosoma mutacion(Cromosoma crom_padre,StringBuilder seguimiento, int generacionActual){
		
		double numAleatorio;
		int filas = crom_padre.getConocimiento().length;
		int colum = crom_padre.getConocimiento()[0].length;
		
		int crom_hijo_mutado[][] = new int[filas][colum];
		
		numAleatorio= (double) (Math.random()*(colum));
		int puntoCorteColumna = (int)numAleatorio ;
		
		numAleatorio= (double) (Math.random()*(filas));
		int puntoCorteFila = (int)numAleatorio ;
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < colum; j++) {
				crom_hijo_mutado[i][j] = crom_padre.getConocimiento()[i][j];
			}
		}
		
		
		crom_hijo_mutado[puntoCorteFila][puntoCorteColumna] = 1 - crom_hijo_mutado[puntoCorteFila][puntoCorteColumna] ;
		
		System.out.println("Posicion Cambiada ---> C["+puntoCorteFila+":"+puntoCorteColumna+"]");
		seguimiento.append("Punto Corte---> C["+puntoCorteFila+":"+puntoCorteColumna+"]");
		
		Cromosoma cromoMutado = new Cromosoma();
		cromoMutado.setConocimiento(crom_hijo_mutado);
		cromoMutado.setNumeroCromosoma(Constantes.NUMERO_CROMOSOMAS);
		cromoMutado.setParentesco("Hijo_Mutado");
		cromoMutado.setGeneracionNacimiento(generacionActual);
		cromoMutado.setPadre(crom_padre.getGeneracionNacimiento()+"-"+crom_padre.getNumeroCromosoma());
		Constantes.NUMERO_CROMOSOMAS++;
		
		return cromoMutado;
		
		
	}
	
	public static void main(String args[]){
		
  int [][] genes1=
   			      {{0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
		     	   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2},
				   {1,1,1,1,1,5,5,5,5,5,5,5,5,5,5},
				   {1,1,1,1,1,5,5,5,5,5,5,5,5,5,5},
				   {1,1,1,1,1,5,5,5,5,5,5,5,5,5,5},
				   {1,1,1,1,1,5,5,5,5,5,5,5,5,5,5},
				   {1,1,1,1,1,5,5,5,5,5,5,5,5,5,5},
				   {1,1,1,1,1,5,5,5,5,5,5,5,5,5,5},
				   {1,1,1,1,1,5,5,5,5,5,5,5,5,5,5},
				   {1,1,1,1,1,5,5,5,5,5,5,5,5,5,5},
				   {1,1,1,1,1,5,5,5,5,5,5,5,5,5,5}};
	
  int [][] genes2={{4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
    	   		   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {4,4,4,4,4,3,3,3,3,3,3,3,3,3,3},
				   {7,7,7,7,7,8,8,8,8,8,8,8,8,8,8},
				   {7,7,7,7,7,8,8,8,8,8,8,8,8,8,8},
				   {7,7,7,7,7,8,8,8,8,8,8,8,8,8,8},
				   {7,7,7,7,7,8,8,8,8,8,8,8,8,8,8},
				   {7,7,7,7,7,8,8,8,8,8,8,8,8,8,8},
				   {7,7,7,7,7,8,8,8,8,8,8,8,8,8,8},
				   {7,7,7,7,7,8,8,8,8,8,8,8,8,8,8},
				   {7,7,7,7,7,8,8,8,8,8,8,8,8,8,8},
				   {7,7,7,7,7,8,8,8,8,8,8,8,8,8,8}};
  
      Cromosoma cromosoma1= new Cromosoma();
      Cromosoma cromosoma2= new Cromosoma();
      Cromosoma cromosoma3= new Cromosoma();
      
      
      cromosoma1.setConocimiento(genes1);
      cromosoma2.setConocimiento(genes2);
      
      Operadores operadores= new Operadores();
      Cromosoma[] cromosomasHijos= operadores.crossover(cromosoma1, cromosoma2,new StringBuilder(),1);
      cromosoma3= operadores.mutacion(cromosoma1, new StringBuilder(),1);
     
      operadores.mostrar(cromosoma3);
      System.out.println("PADRES");
      operadores.mostrar(cromosoma1);
      operadores.mostrar(cromosoma2);
      
      System.out.println("HIJOS GENERADOS");
      
      for (int i = 0; i < cromosomasHijos.length; i++) {
    	  for (int j = 0; j < cromosomasHijos[i].getConocimiento().length; j++) {
			for (int j2 = 0; j2 < cromosomasHijos[i].getConocimiento()[0].length; j2++) {
				System.out.print(cromosomasHijos[i].getConocimiento()[j][j2]+ ",");
			}
			System.out.println("");
		}
    	  System.out.println("");
    	  System.out.println("");
      }
      

	}
	
	public void mostrar(Cromosoma cromosoma){
		
		for (int j1 = 0; j1 <  cromosoma.getConocimiento().length; j1++) {

			for (int j2 = 0; j2 < cromosoma.getConocimiento()[j1].length; j2++) {
				System.out.print(cromosoma.getConocimiento()[j1][j2] + ",");
			}
			System.out.print("\n");

		}
		System.out.print("\n");
		System.out.print("\n");
	}
	
	
	
}