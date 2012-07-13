/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.si;

import java.util.ArrayList;
import java.util.List;

import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.entidad.Organizacion;
import unmsm.fisi.tesis.servicio.LeerFichero;

/**
 *
 * @author Jhon
 */
public class Poblacion {

	private Cromosoma[] listaCromosomas;
	private int tamañoPoblacion;
	private ArrayList<Cromosoma> cromosomaHijos; 
	private StringBuilder seguimiento;
	private ArrayList<Organizacion> organizaciones;
		
	
	public ArrayList<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(ArrayList<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public StringBuilder getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(StringBuilder seguimiento) {
		this.seguimiento = seguimiento;
	}

	public ArrayList<Cromosoma> getCromosomaHijos() {
		return cromosomaHijos;
	}

	public void setCromosomaHijos(ArrayList<Cromosoma> cromosomaHijos) {
		this.cromosomaHijos = cromosomaHijos;
	}

	public Poblacion(){
		
		this.cromosomaHijos = new ArrayList<Cromosoma>();
	}
	
	public int getTamañoPoblacion() {
		return tamañoPoblacion;
	}

	public void setTamañoPoblacion(int tamañoPoblacion) {
		this.tamañoPoblacion = tamañoPoblacion;
	}
	
	public void GenerarFuenteInformacionPorOrganizacion( int []reglasxOrganizacion ) {
		
		Organizacion organi;
		this.setOrganizaciones(new ArrayList<Organizacion>());
		
		//matriz de posibilidades para la OMS
		int [][] OMS={{0,1,0,0,0,0,0,0,1,1,0,0,1,1,1},
			{1,1,1,0,1,0,1,0,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,0,0,1,0,0,1,1,1,0,1,1,0,1},
			{1,1,0,0,0,1,1,1,0,0,0,0,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,0,0,0,1,0,0,0,1,0,0,0,0},
			{1,1,1,0,0,0,1,1,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,1,1,1,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,0,1,0,0,1,0,1,0,1,1,1},
			{0,0,1,0,0,1,0,1,0,1,0,0,1,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,0,1,1,0,0,0,1,0,1,1,1,1},
			{0,1,1,1,1,1,0,0,1,1,1,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

		organi= new Organizacion();//53, 21);
		organi.setNombre("OMS");
		organi.setFuenteInformacion(OMS);
		organi.setNumeroReglas(OMS[0].length);//total de reglas de la organizacion.
		organi.setNumConocParticipante(reglasxOrganizacion[0]);// numero reglas usadas como conocimiento.
		this.getOrganizaciones().add(organi);
		
		int [][] GESMM ={{1,0,1,0,0,0,0,1,0,1,1,1,0,1,0},
				{1,1,1,1,1,0,1,1,0,1,1,1,0,1,0},
				{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,0,0,0,0,1,0,0,0,1,0,0,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,0,1,0,0,0,0,1,1,1,1},
				{1,0,1,0,1,1,1,1,0,0,1,1,1,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,1,0,1,0,1,0,0,0,0,0,1,1,0},
				{0,0,0,0,1,1,0,1,0,0,1,0,0,1,0},
				{0,0,0,1,1,1,0,0,0,1,1,1,0,1,1},
				{1,1,0,0,0,0,1,0,1,0,1,0,0,1,1},
				{0,0,1,0,1,0,0,0,1,1,1,1,1,0,0},
				{1,1,0,1,1,0,1,0,1,1,1,0,1,0,0},
				{0,0,1,0,1,1,0,0,1,0,0,1,1,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,0,0,0,0,0,0,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,1,1,0,1,0,1,0,1,0,0,1},
				{1,1,1,1,1,0,1,0,0,1,1,0,0,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,1,1,0,1,1,1,0,0,1,0,1,1},
				{1,1,1,0,0,0,0,0,1,1,1,0,1,0,0},
				{1,0,0,0,0,1,0,1,0,1,1,1,0,1,1},
				{0,0,1,0,0,1,0,0,0,0,0,1,0,1,0},
				{0,1,0,0,0,1,0,1,1,1,1,1,1,1,0},
				{1,0,0,0,0,0,1,0,0,1,0,1,0,0,1},
				{0,0,0,0,0,1,1,1,1,0,0,1,1,0,1},
				{0,1,1,0,0,0,1,0,0,1,0,0,1,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,1,0,1,1,1,1,1,1,0,1,1},
				{0,0,0,1,1,0,0,1,1,1,0,1,1,0,1}};
		
		organi= new Organizacion();//53, 21);
		organi.setNombre("GESMM");
		organi.setFuenteInformacion(GESMM);
		organi.setNumeroReglas(GESMM[0].length);
		organi.setNumConocParticipante(reglasxOrganizacion[1]);
		this.getOrganizaciones().add(organi);
		
		int [][]AAC={{0},
				{0},
				{1},
				{1},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{1},
				{1},
				{0},
				{0},
				{0},
				{0},
				{1},
				{0},
				{0},
				{0},
				{0},
				{1},
				{0},
				{0},
				{1},
				{1},
				{1},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0}};
		
		organi= new Organizacion();//53,1);
		organi.setNombre("AAC");
		organi.setFuenteInformacion(AAC);
		organi.setNumeroReglas(AAC[0].length);
		organi.setNumConocParticipante(reglasxOrganizacion[2]);
		this.getOrganizaciones().add(organi);
		
		int [][]PNE ={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,0,1,0,1,0,0,0,0,1,1,1,1,0},
				{1,1,0,0,0,0,1,1,0,0,1,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,1,1,1,1,1,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,1,1,1,0,1,1,0,0,1,1,0,0,1},
				{0,1,1,0,1,0,0,0,1,0,0,1,1,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,1,0,0,0,1,0,1,1,1,1,1},
				{1,1,1,1,0,0,0,0,0,0,0,1,1,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,1,0,0,1,0,0,0,1,1,1,1,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,0,1,0,1,0,1,1,0,1,0,1,1,1},
				{1,0,0,1,0,0,0,0,1,1,0,1,0,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		organi= new Organizacion();//53, 21);
		organi.setNombre("PNE");
		organi.setFuenteInformacion(PNE);
		organi.setNumeroReglas(PNE[0].length);
		organi.setNumConocParticipante(reglasxOrganizacion[3]);
		this.getOrganizaciones().add(organi);
		
		int [][] FDI = { {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,1,0,0,1,1,1,1,1,0},
				{1,1,1,1,0,0,0,1,1,1,1,0,1,1,1},
				{0,0,0,0,1,1,0,1,1,0,1,0,1,1,0},
				{0,1,0,1,0,0,0,0,0,1,1,0,1,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,1,1,0,1,1,0,0,0,0,0,0},
				{0,0,0,0,0,1,1,0,0,0,1,1,1,1,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,1,0,0,1,0,0,0,0,1,1,1,1,0},
				{0,1,1,0,1,0,0,0,0,0,0,1,1,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,1,1,1,0,1,1,1,0,0,0,0,1}};

		organi= new Organizacion();//53, 21);
		organi.setNombre("FDI");
		organi.setFuenteInformacion(FDI);
		organi.setNumeroReglas(FDI[0].length);
		organi.setNumConocParticipante(reglasxOrganizacion[4]);
		this.getOrganizaciones().add(organi);
		
		int [][] CNE ={{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{1,0,0,1,1,0,1,0,1,1},
		{0,1,0,1,1,0,0,1,1,1},
		{0,0,0,0,0,0,0,0,0,0},
		{0,1,1,1,0,0,1,1,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{1,1,1,1,0,0,1,0,1,1},
		{1,1,1,1,1,1,1,1,0,1},
		{0,0,0,0,0,0,0,0,0,0},
		{1,1,0,1,0,0,1,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{1,1,0,0,0,0,1,0,0,1},
		{0,0,0,0,0,1,0,1,1,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,1,0,0,1,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,1,1,1,0,1,1,1,1},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,1,1,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,1,0,0,0,1,1,0,0,0},
		{0,1,0,0,1,1,1,0,0,1},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,1,0,1,0,0,1},
		{1,1,0,1,1,0,0,1,1,1},
		{1,1,0,0,0,1,1,0,0,1},
		{1,1,1,1,1,1,1,0,1,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0}};
		
		organi= new Organizacion();//53, 21);
		organi.setNombre("CNE");
		organi.setFuenteInformacion(CNE);
		organi.setNumeroReglas(CNE[0].length);
		organi.setNumConocParticipante(reglasxOrganizacion[5]);
		this.getOrganizaciones().add(organi);
		
		int [][] GEIR={{1,0,0,0,0,0,1,0,0,1,1,0,1,1,1},
				{0,1,1,1,1,0,1,1,1,0,1,0,0,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,1,1,0,0,0,1,0,1,0,1,0,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,1,1,0,0,1,0,0,1,0,0,1,1},
				{0,0,0,1,1,0,1,0,1,1,1,1,1,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,0,0,0,0,1,1,1,1,0,1,1,0,1},
				{1,1,1,0,1,0,0,0,0,0,0,0,1,1,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,0,0,0,0,0,0,0,1,1,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		organi= new Organizacion();//53, 21);
		organi.setNombre("GEIR");
		organi.setFuenteInformacion(GEIR);
		organi.setNumeroReglas(GEIR[0].length);
		organi.setNumConocParticipante(reglasxOrganizacion[6]);
		this.getOrganizaciones().add(organi);
		
		
	}
	
	public void GenerarDatosOrganizacionDesdeArchio(int fila,int colum){
		
		Organizacion organi;
		String nombreArchivo;
		this.setOrganizaciones(new ArrayList<Organizacion>());
		
		organi= new Organizacion(fila, colum);
		nombreArchivo ="C:\\archivo.txt";
		LeerFichero.cargarDatosArchivo(nombreArchivo, organi.getFuenteInformacion(), fila, colum);
		
		this.getOrganizaciones().add(organi);
		
		
		
		
		
		
	}
	
	public void inicializar(Configuracion configuracion, StringBuilder seguimiento ) {
		
		int indice_Organiz   = 0, numero_fuenteConocimiento = 7;
		int hallazgos 		 = configuracion.getNumHallazgos();
		int tamaño_poblacion = configuracion.getNumeroPoblacion();
		int NumReglaTotal 	 = 0;
		int reglasUsadas	 = 0;
		int [][] nuevoConocimiento ;
				
		this.setSeguimiento(seguimiento);
		this.setTamañoPoblacion(tamaño_poblacion);
		this.setListaCromosomas(new Cromosoma[this.getTamañoPoblacion()]); //vector de cromosomas de la poblacion inicial
				
		for (int i = 0; i < configuracion.getReglasxOrganizacion().length; i++) {
			NumReglaTotal = NumReglaTotal + configuracion.getReglasxOrganizacion()[i];
		}
		
		GenerarFuenteInformacionPorOrganizacion(configuracion.getReglasxOrganizacion());
		//GenerarDatosOrganizacionDesdeArchio();
			
		//CREAMOS LOS PRIMEROS CONOCIMIENTOS
		for (int index_conocimiento = 0; index_conocimiento < this.getTamañoPoblacion(); index_conocimiento++) {
			
			nuevoConocimiento = new int [hallazgos][NumReglaTotal];
			reglasUsadas=0;
			indice_Organiz=0;
			
			//CREAMOS LAS REGLAS PARA CADA CONOCIMIENTO
			while(indice_Organiz < numero_fuenteConocimiento){
				
				generarConocimiento( this.getOrganizaciones().get(indice_Organiz),
									nuevoConocimiento, reglasUsadas, hallazgos, index_conocimiento 
								   );
				
				reglasUsadas = reglasUsadas+ configuracion.getReglasxOrganizacion()[indice_Organiz];
				indice_Organiz ++;
			}
			
			Cromosoma cromosoma= new Cromosoma();
			cromosoma.setGenes(nuevoConocimiento);
			this.getListaCromosomas()[index_conocimiento]=cromosoma;
			
			this.getSeguimiento().append("<br>");
			System.out.println("Conocimiento #"+(index_conocimiento+1)+"\n");
			this.getSeguimiento().append("Conocimiento #"+(index_conocimiento+1));
			this.getSeguimiento().append("<br>");
			
			for (int j1 = 0; j1 < nuevoConocimiento.length; j1++) {
				
				for (int j2 = 0; j2 < nuevoConocimiento[j1].length; j2++) {
					System.out.print(nuevoConocimiento[j1][j2]+",");
					this.getSeguimiento().append(nuevoConocimiento[j1][j2]+",");
				}
				
				System.out.print("\n");
				this.getSeguimiento().append("<br>");
			}
			
			System.out.print("\n");
			System.out.print("\n");
			
			this.getSeguimiento().append("<br>");
			this.getSeguimiento().append("<br>");
			
		}
		
	}
	
	public void generarConocimiento( Organizacion informacionOrganizacion, int [][] nuevoConocimiento,int reglasUsadas, 
									int totalHallazgos, int index_conocimiento){
		
		int totalReglasDeOrganizacionParticipante = informacionOrganizacion.getNumeroReglas();
		int reglaElegida , noElegida = 0, fueElegida= 1;
		int listaReglasElegidas[] = new int[totalReglasDeOrganizacionParticipante];
		double reglaAleatoria=0.0;
		
		for (int i = 0; i < totalReglasDeOrganizacionParticipante; i++) {
			listaReglasElegidas[i] = noElegida;
		}
		
		for (int j = 0; j < informacionOrganizacion.getNumConocParticipante(); j++) {//numero de reglas para cada conocimiento
				
				do{
					reglaAleatoria= (double) (Math.random()*totalReglasDeOrganizacionParticipante);
					if((int)reglaAleatoria == totalReglasDeOrganizacionParticipante)
						reglaAleatoria--;
				}while( listaReglasElegidas[(int)reglaAleatoria] == fueElegida);
				reglaElegida = (int)reglaAleatoria;
				listaReglasElegidas[reglaElegida] = fueElegida;
				
				
				System.out.println("Regla # "+ (j+1) + " para el conocimiento "+ (index_conocimiento+1) + " "+ (reglaElegida+1));
				this.getSeguimiento().append("Regla # "+ (j+1) + " para el conocimiento "+ (index_conocimiento+1) + " "+ (reglaElegida+1)+"<br>");
				
				for(int x=0; x < totalHallazgos ; x++){//devuelve el numero de filas
					nuevoConocimiento[ x ][ reglasUsadas + j ] = informacionOrganizacion.getFuenteInformacion()[x][reglaElegida];
				}
		}
	
	}

	public void agregarHijos(Cromosoma[] cromosomasHijos) {
		// TODO Auto-generated method stub
		this.cromosomaHijos.add(cromosomasHijos[0]);
		this.cromosomaHijos.add(cromosomasHijos[1]);
	}

	public void agregarHijoMutado(Cromosoma cromosomaMutado) {
		// TODO Auto-generated method stub
		this.cromosomaHijos.add(cromosomaMutado);
		
	}

	public Cromosoma[] getListaCromosomas() {
		return listaCromosomas;
	}

	public void setListaCromosomas(Cromosoma[] listaCromosomas) {
		this.listaCromosomas = listaCromosomas;
	}	
}
