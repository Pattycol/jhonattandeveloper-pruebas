/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.si;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.Converter;

import unmsm.fisi.tesis.dao.CromosomaDAO;
import unmsm.fisi.tesis.dao.FitnessDAO;
import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.entidad.Poblacion;
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
	private Vector FitnnesMayores;
	private int[][] resultado;

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

	public Cromosoma metodoRuleta(Configuracion configuracion,List<Cromosoma> listaCromosomas, StringBuilder seguimiento) {

		int tamanoPoblacion = listaCromosomas.size();
		double probabAcumuladas[] = new double[tamanoPoblacion];
		double fitnessTemp[] = new double[tamanoPoblacion];
		double acumulado = 0.0;
		double valor_acumulado;
		this.setSeguimiento(seguimiento);

		valor_acumulado = evaluarValorCromosoma(configuracion, listaCromosomas, fitnessTemp);
		
		for (int i = 0; i < tamanoPoblacion; i++) {

			acumulado = acumulado + (fitnessTemp[i]) / valor_acumulado;
			probabAcumuladas[i] = acumulado;
		}

		int posiSeleccionada = 0;

		do {

			double random_r = (double) (Math.random());

			for (int i = 0; i < probabAcumuladas.length; i++) {
				if (random_r <= probabAcumuladas[i]) {
					posiSeleccionada = i;
					i = probabAcumuladas.length;
				}
			}

		} while (listaCromosomas.get(posiSeleccionada).isFlagSeleccion());

		listaCromosomas.get(posiSeleccionada).setFlagSeleccion(true);
		listaCromosomas.get(posiSeleccionada).setParentesco("Padre");
		return listaCromosomas.get(posiSeleccionada);
		
	}

	
	
	public double evaluarValorCromosoma(Configuracion configuracion, List<Cromosoma> listaCromosomas,double fitnessTemp[]){
		
		funcionFitness = new FuncionFitness(configuracion.getNumeroPacientes());
		funcionFitness.setExperienciasPacientes(Carga.cargarHistoriasDePacientes());
		funcionFitness.setDiagnosticos(Carga.cargarDiagnosticoDePacientes());
		
		double valor_acumulado = 0;
		int tamanoPoblacion = listaCromosomas.size();
		
		for (int indiceCromosoma = 0; indiceCromosoma < tamanoPoblacion; indiceCromosoma++) {
			//LA LISTACROMOSOMAS TENEMOS TODOS LOS INDIVIDUOS(PADRES E HIJOS)
			
			Cromosoma individuo =listaCromosomas.get(indiceCromosoma);

			//if(!(individuo.getParentesco().equals("Hijo") || individuo.getParentesco().equals("Hijo_Mutado")))
			if(individuo.getValorAdaptacion() == 0.0)
				fitnessTemp[indiceCromosoma] = this.funcionFitness.evaluarFuncion(individuo, configuracion);
			else
				fitnessTemp[indiceCromosoma] = individuo.getValorAdaptacion();
			
			valor_acumulado = valor_acumulado + fitnessTemp[indiceCromosoma];
			this.getSeguimiento().append( "FITTNES en Seleccion: " + fitnessTemp[indiceCromosoma]+"<br>");
		}
		
		return valor_acumulado;
	}
	
	public List<Cromosoma> seleccionarNuevaPoblacion(Poblacion poblacion,Configuracion configuracion, int contadorGeneraciones, StringBuilder seguimiento) {

		setSeguimiento(seguimiento);
		
		evaluarValorDeCromosomaYGuardar(configuracion, poblacion, contadorGeneraciones );
		
		// Quicksort.quickSort(conocimientoFitnesses); //QuickSort_2.quick_srt(conocimientoFitnesses, 0,
		List<ConocimientoFitness> listaConocimientos = FitnessDAO.obtenerMejoresConocimientosDeGeneracion(contadorGeneraciones);
		mostrarMejoresConocimientos(listaConocimientos);
		
		List<Cromosoma> ListaDeCromosomasMejoresxGeneracion = new ArrayList<Cromosoma>();
		
		int indice = 0;
		while (indice < listaConocimientos.size()){ //;  //configuracion.getNumeroPoblacion()) {// conocimientoFitnesses.length/2){
			ListaDeCromosomasMejoresxGeneracion.add(
					poblacion.getListaCromosomas().get(
							(listaConocimientos.get(indice)).getPosicionCromosoma()
													  ));
			indice++;
		}
		
		this.getSeguimiento().append("Tamaño total de Generacion: " + poblacion.getTamanioPoblacion()+ "<br>");
		this.getSeguimiento().append("Tamaño usados para next Generacion: " + ListaDeCromosomasMejoresxGeneracion.size()+"<br>");

		listaConocimientos.clear();
		poblacion = null;
		
		return ListaDeCromosomasMejoresxGeneracion;

	}
	

	public void evaluarValorDeCromosomaYGuardar(Configuracion configuracion, Poblacion poblacion, int contadorGeneraciones){
		
		int numeroPacientes = configuracion.getNumeroPacientes();
		double valorFitnes = 0;

		funcionFitness = new FuncionFitness(numeroPacientes);
		funcionFitness.setExperienciasPacientes(Carga.cargarHistoriasDePacientes());
		funcionFitness.setDiagnosticos(Carga.cargarDiagnosticoDePacientes());

		ConocimientoFitness[] ListaDeConocimientoFitnesses = new ConocimientoFitness[poblacion.getListaCromosomas().size()];
		
		for (int indiceCromosoma = 0; indiceCromosoma < poblacion.getTamanioPoblacion(); indiceCromosoma++) {

			valorFitnes = funcionFitness.evaluarFuncion(poblacion.getListaCromosomas().get(indiceCromosoma), configuracion);
		
			ListaDeConocimientoFitnesses[indiceCromosoma] = new ConocimientoFitness();
			ListaDeConocimientoFitnesses[indiceCromosoma].setValorFitness(valorFitnes);
			ListaDeConocimientoFitnesses[indiceCromosoma].setPosicionCromosoma(indiceCromosoma);
			ListaDeConocimientoFitnesses[indiceCromosoma].setNumeroGeneracion(contadorGeneraciones);

			CromosomaDAO.guardarDetalleCromosoma(ListaDeConocimientoFitnesses[indiceCromosoma], poblacion.getListaCromosomas().get(indiceCromosoma));

			this.getSeguimiento().append("Fitnes en Nueva Poblacion: "+ ListaDeConocimientoFitnesses[indiceCromosoma].getValorFitness()+"<br>");

			if (Double.parseDouble(FitnnesMayores.lastElement().toString()) < ListaDeConocimientoFitnesses[indiceCromosoma].getValorFitness()) {
				FitnnesMayores.addElement(String.valueOf(ListaDeConocimientoFitnesses[indiceCromosoma].getValorFitness()));
				this.setResultado(poblacion.getListaCromosomas().get(indiceCromosoma).getConocimiento());
			}

		 }
		
	}
	
	public void mostrarMejoresConocimientos( List<ConocimientoFitness> listaConocimientos ){
		
		this.getSeguimiento().append("Despues del Quicsort: " + "<br>");
		System.out.println("Despues del Quicsort: ");
		for (int k = 0; k < listaConocimientos.size(); k++) {
			System.out.print(listaConocimientos.get(k).getValorFitness()+" /  ");
			this.getSeguimiento().append("  " + listaConocimientos.get(k).getValorFitness()+" / ");
		}
		this.getSeguimiento().append("<br>");
		System.out.println("");
	}
	
	
	public int[][] getResultado() {
		return resultado;
	}

	public void setResultado(int[][] resultado) {
		this.resultado = resultado;
	}

}
