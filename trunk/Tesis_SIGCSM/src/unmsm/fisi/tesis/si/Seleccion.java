/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.si;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.Converter;

import unmsm.fisi.tesis.dao.FitnessDAO;
import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.servicio.ConocimientoFitness;
import unmsm.fisi.tesis.servicio.QuickSort_2;
import unmsm.fisi.tesis.servicio.Quicksort;

/**
 *
 * @author Jhon
 */
public class Seleccion {

	private FuncionFitness funcionFitness;
	private StringBuilder seguimiento;
	private int posicionCromosomaElegida;
	private Vector FitnnesMayores;
	private int [][] resultado;
	
	
	public Vector getFitnnesMayores() {
		return FitnnesMayores;
	}

	public void setFitnnesMayores(Vector fitnnesMayores) {
		FitnnesMayores = fitnnesMayores;
	}

	public StringBuilder getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(StringBuilder seguimiento) {
		this.seguimiento = seguimiento;
	}

	public int getPosicionCromosomaElegida() {
		return posicionCromosomaElegida;
	}

	public void setPosicionCromosomaElegida(int posicionCromosomaElegida) {
		this.posicionCromosomaElegida = posicionCromosomaElegida;
	}

	public Cromosoma metodoRuleta(Configuracion configuracion, Cromosoma[] cromosomas,StringBuilder seguimiento){
		
		int tamanoPoblacion= configuracion.getNumeroPoblacion();
		int numeroPacientes = configuracion.getNumeroPacientes();
		
		tamanoPoblacion=cromosomas.length;
		this.setSeguimiento(seguimiento);
		
		double probabAcumuladas[] = new double[tamanoPoblacion];
		double fitnessTemp[]= new double[tamanoPoblacion];
		double acumulado =0.0;
		double valor_acumulado=0;
		
		funcionFitness= new FuncionFitness(numeroPacientes);//numero de pacientes es 20
		
		for (int i = 0; i < tamanoPoblacion; i++) {
			
			fitnessTemp[i]= this.funcionFitness.evaluarFuncion(cromosomas[i],configuracion);
			this.getSeguimiento().append("FITTNES_Ruleta: " +fitnessTemp[i]);
			this.getSeguimiento().append("<br>");
			valor_acumulado = valor_acumulado + fitnessTemp[i];
			
			if(Double.parseDouble(FitnnesMayores.lastElement().toString())<fitnessTemp[i]){
				FitnnesMayores.addElement(String.valueOf(fitnessTemp[i]));
				this.setResultado(cromosomas[i].getGenes());
			}
			
		}
		for (int i = 0; i < tamanoPoblacion; i++) {
			
			acumulado = acumulado + ( fitnessTemp[i] ) / valor_acumulado;
			probabAcumuladas[i]=acumulado;
		}
		
		int posiSeleccionada =-2;
		do{
			do{
			
				double random_r= (double) (Math.random());
				
				for (int i = 0; i < probabAcumuladas.length; i++) {
					if(random_r <= probabAcumuladas[i]){
						posiSeleccionada = i;
						i= probabAcumuladas.length;
					}
				}
			}while(posiSeleccionada == -2);
		}while(this.getPosicionCromosomaElegida() == posiSeleccionada);
		
		this.setPosicionCromosomaElegida(posiSeleccionada);
		return cromosomas[posiSeleccionada];
		
	}

	public Cromosoma[] seleccionarNuevaPoblacion(Poblacion poblacion,Configuracion configuracion,int contadorGeneraciones, StringBuilder seguimiento) throws Exception {
	
		int numeroPacientes=configuracion.getNumeroPacientes();
		this.setSeguimiento(seguimiento);
		Cromosoma[] cromosomasTotales = new Cromosoma[poblacion.getListaCromosomas().length +poblacion.getCromosomaHijos().size()];
		//Cromosoma cromosomaSeleccionada;
		
		int j=0;
		for (int i = 0; i < cromosomasTotales.length; i++)
		{
			if(i<poblacion.getListaCromosomas().length)
			{
				cromosomasTotales[i] = poblacion.getListaCromosomas()[i];
			}else{
				cromosomasTotales[i] = poblacion.getCromosomaHijos().get(j);
				j++;
			}
		}
		FitnessDAO objFitnes = new FitnessDAO();
		this.funcionFitness= new FuncionFitness(numeroPacientes);
		ConocimientoFitness[] conocimientoFitnesses= new ConocimientoFitness[cromosomasTotales.length];
		double valorFitnes=0;
		for (int i = 0; i < cromosomasTotales.length; i++) {
			
			conocimientoFitnesses[i] = new ConocimientoFitness();
			valorFitnes=this.funcionFitness.evaluarFuncion(cromosomasTotales[i],configuracion);
			conocimientoFitnesses[i].setValorFitness(valorFitnes);
			conocimientoFitnesses[i].setPosicionCromosoma(i);
			conocimientoFitnesses[i].setNumeroGeneracion(contadorGeneraciones);
			
			
			objFitnes.guardarValoreFitness(conocimientoFitnesses[i]);
			
			this.getSeguimiento().append("FITTNES_2: " +conocimientoFitnesses[i].getValorFitness());this.getSeguimiento().append("<br>");
			
			if(Double.parseDouble(FitnnesMayores.lastElement().toString())<conocimientoFitnesses[i].getValorFitness()){
				FitnnesMayores.addElement(String.valueOf(conocimientoFitnesses[i].getValorFitness()));
				this.setResultado(cromosomasTotales[i].getGenes());
			}
			
		}
		
		
		//Quicksort.quickSort(conocimientoFitnesses);
		//QuickSort_2.quick_srt(conocimientoFitnesses, 0, conocimientoFitnesses.length-1);
		List<ConocimientoFitness> listaConocimientos;
		
		listaConocimientos = objFitnes.obtenerMejoresConocimientosDeGeneracion(contadorGeneraciones);
		
		this.getSeguimiento().append("Despues del Quicsort: "+"<br>");
		for (int k = 0; k < listaConocimientos.size(); k++) {
			System.out.println(listaConocimientos.get(k).getValorFitness());
			this.getSeguimiento().append("  " +listaConocimientos.get(k).getValorFitness());
			this.getSeguimiento().append(" / ");
		}
		this.getSeguimiento().append("<br>");
		
		
		
		
		
		Cromosoma[] cromoMejores= new Cromosoma[configuracion.getNumeroPoblacion()];//conocimientoFitnesses.length/2];
		int indice=0;
		while(indice < configuracion.getNumeroPoblacion()){//  conocimientoFitnesses.length/2){
			cromoMejores[indice] = cromosomasTotales[(listaConocimientos.get(indice)).getPosicionCromosoma()];
			indice++;
		}
		listaConocimientos.clear();
		this.getSeguimiento().append("tamaño: " +conocimientoFitnesses.length +"pero solo usamos la mitad");
		this.getSeguimiento().append("<br>");
		this.getSeguimiento().append("tamaño: " +cromoMejores.length);
		this.getSeguimiento().append("<br>");
			
		poblacion = null;	
		return cromoMejores;
		
		
	}

	public int[][] getResultado() {
		return resultado;
	}

	public void setResultado(int[][] resultado) {
		this.resultado = resultado;
	}
	
	
}
