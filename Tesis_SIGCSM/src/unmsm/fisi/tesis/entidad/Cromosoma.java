/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.entidad;

import java.io.Serializable;

/**
 *
 * @author Jhon
 */
public class Cromosoma implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2673243173777258218L;
	
	private int numeroCromosoma;
	private int [][] conocimiento ;
	private double valorAdaptacion;
	private String parentesco;
	private String estado;
	private int generacionNacimiento;
	private int generacionFallecimiento;
	private String padre;
	private String madre;
	private boolean flagSeleccion=false;
	
	public boolean isFlagSeleccion() {
		return flagSeleccion;
	}
	public void setFlagSeleccion(boolean flagSeleccion) {
		this.flagSeleccion = flagSeleccion;
	}
	public int[][] getConocimiento() {
		return conocimiento;
	}
	public void setConocimiento(int[][] conocimiento) {
		this.conocimiento = conocimiento;
	}
	public double getValorAdaptacion() {
		return valorAdaptacion;
	}
	public void setValorAdaptacion(double valorAdaptacion) {
		this.valorAdaptacion = valorAdaptacion;
	}
	public String getParentesco() {
		return parentesco;
	}
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getGeneracionNacimiento() {
		return generacionNacimiento;
	}
	public void setGeneracionNacimiento(int generacionNacimiento) {
		this.generacionNacimiento = generacionNacimiento;
	}
	public int getGeneracionFallecimiento() {
		return generacionFallecimiento;
	}
	public void setGeneracionFallecimiento(int generacionFallecimiento) {
		this.generacionFallecimiento = generacionFallecimiento;
	}
	public int getNumeroCromosoma() {
		return numeroCromosoma;
	}
	public void setNumeroCromosoma(int numeroCromosoma) {
		this.numeroCromosoma = numeroCromosoma;
	}
	public String getPadre() {
		return padre;
	}
	public void setPadre(String padre) {
		this.padre = padre;
	}
	public String getMadre() {
		return madre;
	}
	public void setMadre(String madre) {
		this.madre = madre;
	}
	
	
	
	
}
