/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.entidad;

/**
 *
 * @author Jhon
 */
public class Configuracion {

      private int numeroPacientes;
      private int numeroPoblacion;
      private double probabilidadCrossover_x;
      private double probabilidadMutacion_y;
      private int reglasxOrganizacion[];
      private int numGeneraciones;
      private int numHallazgos;

    public Configuracion() {
    }

    
    public Configuracion(int numeroPacientes, int numeroPoblacion,
			double probabilidadCrossover_x, double probabilidadMutacion_y,
			int[] reglasxOrganizacion, int numGeneraciones, int numHallazgos) {
		//super();
		this.numeroPacientes = numeroPacientes;
		this.numeroPoblacion = numeroPoblacion;
		this.probabilidadCrossover_x = probabilidadCrossover_x;
		this.probabilidadMutacion_y = probabilidadMutacion_y;
		this.reglasxOrganizacion = reglasxOrganizacion;
		this.numGeneraciones = numGeneraciones;
		this.numHallazgos = numHallazgos;
	}


	public int getNumeroPacientes() {
		return numeroPacientes;
	}


	public void setNumeroPacientes(int numeroPacientes) {
		this.numeroPacientes = numeroPacientes;
	}


	public int getNumeroPoblacion() {
		return numeroPoblacion;
	}


	public void setNumeroPoblacion(int numeroPoblacion) {
		this.numeroPoblacion = numeroPoblacion;
	}


	public double getProbabilidadCrossover_x() {
		return probabilidadCrossover_x;
	}


	public void setProbabilidadCrossover_x(double probabilidadCrossover_x) {
		this.probabilidadCrossover_x = probabilidadCrossover_x;
	}


	public double getProbabilidadMutacion_y() {
		return probabilidadMutacion_y;
	}


	public void setProbabilidadMutacion_y(double probabilidadMutacion_y) {
		this.probabilidadMutacion_y = probabilidadMutacion_y;
	}


	public int[] getReglasxOrganizacion() {
		return reglasxOrganizacion;
	}


	public void setReglasxOrganizacion(int[] reglasxOrganizacion) {
		this.reglasxOrganizacion = reglasxOrganizacion;
	}


	public int getNumGeneraciones() {
		return numGeneraciones;
	}


	public void setNumGeneraciones(int numGeneraciones) {
		this.numGeneraciones = numGeneraciones;
	}


	public int getNumHallazgos() {
		return numHallazgos;
	}


	public void setNumHallazgos(int numHallazgos) {
		this.numHallazgos = numHallazgos;
	}



}
