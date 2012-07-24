/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.si;

import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;

/**
 * 
 * @author Jhon
 */
public class FuncionFitness {

	private double resultado_fx;
	// Matriz de Pacientes E
	private int experienciasPacientes[][];
	// Matriz de Diagnostico del Experto o Medico
	private int diagnosticos[];

	private int resultado_Sistema[];
	private int numeroPacientes;
	private StringBuilder seguimiento;

	public FuncionFitness(int numeroPacientes) {
		this.numeroPacientes = numeroPacientes;

	}

	public int[][] getExperienciasPacientes() {
		return experienciasPacientes;
	}

	public void setExperienciasPacientes(int[][] experienciasPacientes) {
		this.experienciasPacientes = experienciasPacientes;
	}

	public int[] getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(int[] diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public int[] evaluacionSistema(Cromosoma cromosoma,
			Configuracion configuracion) {

		int columnas = cromosoma.getConocimiento()[0].length;
		int filas = cromosoma.getConocimiento().length;
		this.resultado_Sistema = new int[this.numeroPacientes];
		int cont = 0;

		try {
			for (int e = 0; e < this.numeroPacientes; e++) {

				for (int i = 0; i < columnas; i++) {

					for (int j = 0; j < filas; j++) {

						if (cromosoma.getConocimiento()[j][i] == getExperienciasPacientes()[j][e]) {
							cont++;
						}
					}
					if (cont == filas - 1) {
						this.resultado_Sistema[e] = 1;
					} else {
						this.resultado_Sistema[e] = 0;
					}
					cont = 0;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resultado_Sistema;
	}

	public double evaluarFuncion(Cromosoma cromosoma,
			Configuracion configuracion) {

		int valor = 0;
		this.resultado_fx  = cromosoma.getValorAdaptacion();
		
		if (resultado_fx == 0.0) {
			
			resultado_Sistema = evaluacionSistema(cromosoma, configuracion);
			for (int i = 0; i < configuracion.getNumeroPacientes(); i++) {
				valor = valor + 2 * (this.getDiagnosticos()[i]) * (this.resultado_Sistema[i]) - this.getDiagnosticos()[i] - this.resultado_Sistema[i];

			}

			this.resultado_fx = (double) (valor + configuracion.getNumeroPacientes()) / configuracion.getNumeroPacientes();
			cromosoma.setValorAdaptacion(resultado_fx);
		}
		
		System.out.println("FITNES: " + resultado_fx);
		return this.resultado_fx;

	}

	public StringBuilder getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(StringBuilder seguimiento) {
		this.seguimiento = seguimiento;
	}

}
