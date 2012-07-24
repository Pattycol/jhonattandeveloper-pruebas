/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.si;

import java.util.ArrayList;
import java.util.Vector;

import sun.java2d.pipe.Region;
import unmsm.fisi.tesis.dao.FitnessDAO;
import unmsm.fisi.tesis.dao.PoblacionDAO;
import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.entidad.Poblacion;

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

		poblacion = new Poblacion();
		this.seleccion = new Seleccion();
		this.seleccion.setFitnnesMayores(new Vector());
		this.seleccion.getFitnnesMayores().addElement("0".toString());
		this.operadores = new Operadores();

		this.setSeguimiento(new StringBuilder());

		Carga cargaInformacion= new Carga();
		poblacion = cargaInformacion.generarPoblacionInicial(configuracion, seguimiento);
		
		int contadorHijos = 0,contadorGeneraciones = 1,contadorIteraciones = 0;
		int poblacionInicial = poblacion.getTamanioPoblacion();
		
		while (contadorGeneraciones < configuracion.getNumGeneraciones()) {

			this.getSeguimiento().append("<br>");
			pintarMensajes("*************** GENERACION # " + contadorGeneraciones + "*******************");
			
			contadorHijos = 0;
			contadorIteraciones = 1;
			
			do {
				pintarMensajes("---- ITERACION #" + contadorIteraciones);
			
				double random_r = (double) (Math.random());
				
				if (random_r >= configuracion.getProbabilidadCrossover_x()) {
					
					cromosomaSeleccionada_1 = seleccion.metodoRuleta(configuracion,this.poblacion.getListaCromosomas(),this.getSeguimiento());
					cromosomaSeleccionada_2 = seleccion.metodoRuleta(configuracion,this.poblacion.getListaCromosomas(),this.getSeguimiento());
					
					pintarMensajes("(" + random_r + " >= Realiza Crossover)"); 
					pintarMensajes("      Padre 1  y Padre 2");//mostrarCromosomaSeleccionados(cromosomaSeleccionada_2.getGenes());

					Cromosoma[] cromosomasHijos = operadores.crossover(cromosomaSeleccionada_1,cromosomaSeleccionada_2,this.getSeguimiento(),contadorGeneraciones);
					this.poblacion.agregarHijos(cromosomasHijos);
					contadorHijos = contadorHijos + 2;

					pintarMensajes("      Hijo Cruzado 1    y    Hijo Cruzado 2");//mostrarCromosomaSeleccionados(cromosomasHijos[1].getGenes());
			
					cromosomasHijos = null;
					cromosomaSeleccionada_1 = null;
					cromosomaSeleccionada_2 = null;
					
				}
				if (random_r >= configuracion.getProbabilidadMutacion_y()) {

					cromosomaSeleccionada_1 = seleccion.metodoRuleta(configuracion,this.poblacion.getListaCromosomas(),this.getSeguimiento());
				
					// Mutation ;
					Cromosoma cromosomaMutado = operadores.mutacion(cromosomaSeleccionada_1,this.getSeguimiento(),contadorGeneraciones);
					this.poblacion.agregarHijoMutado(cromosomaMutado);
					contadorHijos = contadorHijos + 1;

					pintarMensajes("(" + random_r + ">= Realiza Mutacion)");
					pintarMensajes("      Padre a Mutar");//mostrarCromosomaSeleccionados(cromosomaSeleccionada_1.getGenes());
					pintarMensajes("      Hijo Mutado");//mostrarCromosomaSeleccionados(cromosomaMutado.getGenes());
					
					cromosomaMutado= null;
					cromosomaSeleccionada_1 = null;
				}
				
				for(int i = 0; i<poblacion.getListaCromosomas().size();i++ ){
					poblacion.getListaCromosomas().get(i).setFlagSeleccion(false);
				}

				contadorIteraciones++;

			 //}while (contadorHijos <= configuracion.getNumeroPoblacion());
			}while (contadorHijos <= configuracion.getNumeroPoblacion());//

			guardarInformacionDeGeneracion( contadorGeneraciones, contadorIteraciones, poblacionInicial);
				
			Poblacion nueva_poblacion = new Poblacion();
			nueva_poblacion.setListaCromosomas( this.seleccion.seleccionarNuevaPoblacion(this.poblacion,configuracion,contadorGeneraciones,this.getSeguimiento()));
			this.poblacion = nueva_poblacion ;
			this.poblacion.setTamanioPoblacion(nueva_poblacion.getListaCromosomas().size());
			
			contadorGeneraciones++;
		}
		
		FitnessDAO objFitnes = new FitnessDAO();
		//objFitnes.eliminarValoresFitness();


	}

	public void pintarMensajes(String mensaje){
		
		System.out.println(mensaje);
		this.getSeguimiento().append(mensaje);
		this.getSeguimiento().append("<br>");
	}
	
	public void guardarInformacionDeGeneracion(int numGeneracion, int iteraciones, int poblacionInicial)
	{
		PoblacionDAO poblADO = new PoblacionDAO();
		String detalle = "Geracion #"+numGeneracion;
		poblADO.asignarGeneracionYPoblacion(numGeneracion, detalle, iteraciones , poblacionInicial, this.poblacion.getTamanioPoblacion());
		
		
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



