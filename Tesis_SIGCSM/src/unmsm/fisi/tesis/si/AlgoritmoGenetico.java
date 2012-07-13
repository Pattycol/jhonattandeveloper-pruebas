/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.si;

import java.util.ArrayList;
import java.util.Vector;

import sun.java2d.pipe.Region;
import unmsm.fisi.tesis.dao.FitnessDAO;
import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;

/**
 * 
 * @author Jhon
 */
public class AlgoritmoGenetico {

	private Poblacion poblacion;
	private Operadores operadores;
	private Seleccion seleccion;
	private Cromosoma cromosomaSeleccionada_1;
	private Cromosoma cromosomaSeleccionada_2;
	
	private StringBuilder seguimiento;

	public void ejecutar(Configuracion configuracion) {

		Carga cargaInformacion= new Carga();
		
		poblacion = new Poblacion();
		this.seleccion = new Seleccion();
		this.seleccion.setFitnnesMayores(new Vector());
		this.seleccion.getFitnnesMayores().addElement("0".toString());
		this.operadores = new Operadores();
//
//		try {
			this.setSeguimiento(new StringBuilder());
			
			poblacion = cargaInformacion.generarPoblacionInicial(configuracion, seguimiento);
			
			this.getSeguimiento().append("<br>");

			int contadorHijos = 0,contadorGeneraciones = 1,contadorIteraciones = 0;
			
			while (contadorGeneraciones < configuracion.getNumGeneraciones()) {

				System.out.println("*************** GENERACION # " + "0"+ contadorGeneraciones + "*******************");
				this.getSeguimiento().append("*************** GENERACION # " + "0"+contadorGeneraciones + "*******************");this.getSeguimiento().append("<br>");
	
				contadorHijos = 0;contadorIteraciones = 1;
				
				do {
					System.out.println("---- ITERACION #" + contadorIteraciones);this.getSeguimiento().append("---- ITERACION #" + contadorIteraciones);this.getSeguimiento().append("<br>");
				
					double random_r = (double) (Math.random());
					if (random_r >= configuracion.getProbabilidadCrossover_x()) {
						// para la seleccion le enviamos el objeto(poblacion,genberada anteriormente) que contiene el arreglo de cromososmas
						
						cromosomaSeleccionada_1 = seleccion.metodoRuleta(configuracion,this.poblacion.getListaCromosomas(),this.getSeguimiento());
						cromosomaSeleccionada_2 = seleccion.metodoRuleta(configuracion,this.poblacion.getListaCromosomas(),this.getSeguimiento());
						
						System.out.println("(" + random_r + " >= Realiza Crossover)"); System.out.println("      Padre 1");
						this.getSeguimiento().append("(" + random_r + ">= Realiza Crossover)");this.getSeguimiento().append("<br>");
						this.getSeguimiento().append("      Padre 1");this.getSeguimiento().append("<br>");
						//mostrarCromosomaSeleccionados(cromosomaSeleccionada_1.getGenes());System.out.println("      Padre 2");
						this.getSeguimiento().append("      Padre 2");this.getSeguimiento().append("<br>");
						
						//mostrarCromosomaSeleccionados(cromosomaSeleccionada_2.getGenes());

						// operadoresO
						Cromosoma[] cromosomasHijos = operadores.crossover(cromosomaSeleccionada_1,cromosomaSeleccionada_2,this.getSeguimiento(),contadorGeneraciones);
						this.poblacion.agregarHijos(cromosomasHijos);
						contadorHijos = contadorHijos + 2;

						System.out.println("      Hijo Cruzado 1");this.getSeguimiento().append("      Hijo Cruzado 1");this.getSeguimiento().append("<br>");
						//mostrarCromosomaSeleccionados(cromosomasHijos[0].getGenes());
						System.out.println("      Hijo Cruzado 2");this.getSeguimiento().append("      Hijo Cruzado 2");this.getSeguimiento().append("<br>");
						//mostrarCromosomaSeleccionados(cromosomasHijos[1].getGenes());
				
						cromosomasHijos = null;
					}
					if (random_r >= configuracion.getProbabilidadMutacion_y()) {

						cromosomaSeleccionada_1 = seleccion.metodoRuleta(configuracion,this.poblacion.getListaCromosomas(),this.getSeguimiento());
					
						// Mutation ;
						Cromosoma cromosomaMutado = operadores.mutacion(cromosomaSeleccionada_1,this.getSeguimiento(),contadorGeneraciones);
						this.poblacion.agregarHijoMutado(cromosomaMutado);
						contadorHijos = contadorHijos + 1;

						System.out.println("(" + random_r + ">= Realiza Mutacion)");
						System.out.println("      Padre a Mutar");
						this.getSeguimiento().append("(" + random_r + ">= Realiza Mutacion)");this.getSeguimiento().append("<br>");
						this.getSeguimiento().append("      Padre a Mutar");this.getSeguimiento().append("<br>");
						
						//mostrarCromosomaSeleccionados(cromosomaSeleccionada_1.getGenes());
						System.out.println("      Hijo Mutado");this.getSeguimiento().append("      Hijo Mutado");this.getSeguimiento().append("<br>");
						
						//mostrarCromosomaSeleccionados(cromosomaMutado.getGenes());
						
						cromosomaMutado= null;
					}

					cromosomaSeleccionada_1 = null;
					cromosomaSeleccionada_2 = null;
					contadorIteraciones++;

				 //}while (contadorHijos <= configuracion.getNumeroPoblacion());
			}while (contadorHijos <= this.poblacion.getListaCromosomas().size());//

				
			Poblacion new_poblacion = new Poblacion();
			new_poblacion.setListaCromosomas( this.seleccion.seleccionarNuevaPoblacion(this.poblacion,configuracion,contadorGeneraciones,this.getSeguimiento()));
			this.poblacion = null ;
			this.poblacion= new_poblacion ;
			this.poblacion.setTamanioPoblacion(new_poblacion.getListaCromosomas().size());
			//this.setSeguimiento(this.seleccion.getSeguimiento());
			contadorGeneraciones++;
			//Constantes.NUMERO_CROMOSOMAS=0;
			this.getSeguimiento().append("<br>");
		}
		FitnessDAO objFitnes = new FitnessDAO();
		//objFitnes.eliminarValoresFitness();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Ocurrio un Exception:" + e.getMessage());
//		}

	}

	public boolean condicion() {
		return true;
	}
	

	public void mostrarCromosomaSeleccionados(int conocimiento[][]) {

		// System.out.println("Conocimiento #"+(i+1)+"\n");
		for (int j1 = 0; j1 < conocimiento.length; j1++) {

			for (int j2 = 0; j2 < conocimiento[j1].length; j2++) {
				System.out.print(conocimiento[j1][j2] + ",");
				this.getSeguimiento().append(conocimiento[j1][j2] + ",");
			}
			System.out.print("\n");
			this.getSeguimiento().append("<br>");

		}
		
		System.out.print("\n");
		System.out.print("\n");
		
		this.getSeguimiento().append("<br>");
		this.getSeguimiento().append("<br>");

	}

	public StringBuilder getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(StringBuilder seguimiento) {
		this.seguimiento = seguimiento;
	}

	public Seleccion getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}

	
	
}



