/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import unmsm.fisi.tesis.servicio.LeerFichero;

/**
 *
 * @author Jhon
 */
public class Poblacion implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1975407254234335963L;
	
	private List<Cromosoma> listaCromosomas;
	private int tamanioPoblacion;

	private StringBuilder seguimiento;
	
	

	public StringBuilder getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(StringBuilder seguimiento) {
		this.seguimiento = seguimiento;
	}

	public Poblacion(){
		
	}
	
	
	
	
	
	
	
	public int getTamanioPoblacion() {
		
		return tamanioPoblacion;
	}

	public void setTamanioPoblacion(int tamanioPoblacion) {
		this.tamanioPoblacion = tamanioPoblacion;
	}

	

	public void agregarHijos(Cromosoma[] cromosomasHijos) {
		// TODO Auto-generated method stub
		
		this.getListaCromosomas().add(cromosomasHijos[0]);
		this.getListaCromosomas().add(cromosomasHijos[1]);
		setTamanioPoblacion(getListaCromosomas().size());
		
	}

	public void agregarHijoMutado(Cromosoma cromosomaMutado) {
		// TODO Auto-generated method stub
		this.getListaCromosomas().add(cromosomaMutado);
		setTamanioPoblacion(getListaCromosomas().size());
		
		
	}

	public List<Cromosoma> getListaCromosomas() {
		if(listaCromosomas==null){
			listaCromosomas = new ArrayList<Cromosoma>();
		}
		return listaCromosomas;
	}

	public void setListaCromosomas(List<Cromosoma> listaCromosomas) {
		this.listaCromosomas = listaCromosomas;
	}

	
}
