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

	public Cromosoma metodoRuleta(Configuracion configuracion,
			List<Cromosoma> listaCromosomas, StringBuilder seguimiento) {

		int numeroPacientes = configuracion.getNumeroPacientes();
		int tamanoPoblacion = listaCromosomas.size();
		this.setSeguimiento(seguimiento);

		double probabAcumuladas[] = new double[tamanoPoblacion];
		double fitnessTemp[] = new double[tamanoPoblacion];
		double acumulado = 0.0;
		double valor_acumulado = 0;

		funcionFitness = new FuncionFitness(numeroPacientes);// numero de
																// pacientes es
																// 20

		funcionFitness.setExperienciasPacientes(Carga.cargarHistoriasDePacientes());
		funcionFitness.setDiagnosticos(Carga.cargarDiagnosticoDePacientes());

		for (int indiceCromosoma = 0; indiceCromosoma < tamanoPoblacion; indiceCromosoma++) {

			fitnessTemp[indiceCromosoma] = this.funcionFitness.evaluarFuncion(listaCromosomas.get(indiceCromosoma), configuracion);
			this.getSeguimiento().append(
					"FITTNES_Ruleta: " + fitnessTemp[indiceCromosoma]);
			this.getSeguimiento().append("<br>");
			valor_acumulado = valor_acumulado + fitnessTemp[indiceCromosoma];

			if (Double.parseDouble(FitnnesMayores.lastElement().toString()) < fitnessTemp[indiceCromosoma]) {
				FitnnesMayores.addElement(String
						.valueOf(fitnessTemp[indiceCromosoma]));
				this.setResultado(listaCromosomas.get(indiceCromosoma)
						.getConocimiento());
			}

		}
		for (int i = 0; i < tamanoPoblacion; i++) {

			acumulado = acumulado + (fitnessTemp[i]) / valor_acumulado;
			probabAcumuladas[i] = acumulado;
		}

		int posiSeleccionada = -2;

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
		listaCromosomas.get(posiSeleccionada).setParentesco("padre");
		return listaCromosomas.get(posiSeleccionada);

	}

	public List<Cromosoma> seleccionarNuevaPoblacion(Poblacion poblacion,
			Configuracion configuracion, int contadorGeneraciones,
			StringBuilder seguimiento) {

		int numeroPacientes = configuracion.getNumeroPacientes();
		this.setSeguimiento(seguimiento);

		FitnessDAO objFitnes = new FitnessDAO();
		this.funcionFitness = new FuncionFitness(numeroPacientes);
		this.funcionFitness.setExperienciasPacientes(Carga
				.cargarHistoriasDePacientes());
		this.funcionFitness.setDiagnosticos(Carga
				.cargarDiagnosticoDePacientes());

		ConocimientoFitness[] conocimientoFitnesses = new ConocimientoFitness[poblacion
				.getListaCromosomas().size()];
		double valorFitnes = 0;

		for (int indiceCromosoma = 0; indiceCromosoma < poblacion
				.getTamanioPoblacion(); indiceCromosoma++) {

			conocimientoFitnesses[indiceCromosoma] = new ConocimientoFitness();
			valorFitnes = this.funcionFitness.evaluarFuncion(poblacion
					.getListaCromosomas().get(indiceCromosoma), configuracion);
			conocimientoFitnesses[indiceCromosoma].setValorFitness(valorFitnes);
			conocimientoFitnesses[indiceCromosoma]
					.setPosicionCromosoma(indiceCromosoma);
			conocimientoFitnesses[indiceCromosoma]
					.setNumeroGeneracion(contadorGeneraciones);

			objFitnes.guardarValoreFitness(
					conocimientoFitnesses[indiceCromosoma], poblacion
							.getListaCromosomas().get(indiceCromosoma));

			this.getSeguimiento().append(
					"FITTNES_2: "
							+ conocimientoFitnesses[indiceCromosoma]
									.getValorFitness());
			this.getSeguimiento().append("<br>");

			if (Double.parseDouble(FitnnesMayores.lastElement().toString()) < conocimientoFitnesses[indiceCromosoma]
					.getValorFitness()) {
				FitnnesMayores.addElement(String
						.valueOf(conocimientoFitnesses[indiceCromosoma]
								.getValorFitness()));
				this.setResultado(poblacion.getListaCromosomas()
						.get(indiceCromosoma).getConocimiento());
			}

		}

		// Quicksort.quickSort(conocimientoFitnesses);
		// QuickSort_2.quick_srt(conocimientoFitnesses, 0,
		// conocimientoFitnesses.length-1);
		List<ConocimientoFitness> listaConocimientos;

		listaConocimientos = objFitnes
				.obtenerMejoresConocimientosDeGeneracion(contadorGeneraciones);

		this.getSeguimiento().append("Despues del Quicsort: " + "<br>");
		for (int k = 0; k < listaConocimientos.size(); k++) {
			System.out.println(listaConocimientos.get(k).getValorFitness());
			this.getSeguimiento().append(
					"  " + listaConocimientos.get(k).getValorFitness());
			this.getSeguimiento().append(" / ");
		}
		this.getSeguimiento().append("<br>");

		List<Cromosoma> ListaDeCromosomasMejoresxGeneracion = new ArrayList<Cromosoma>();
		int indice = 0;
		while (indice < configuracion.getNumeroPoblacion()) {// conocimientoFitnesses.length/2){
			ListaDeCromosomasMejoresxGeneracion.add(poblacion
					.getListaCromosomas().get(
							(listaConocimientos.get(indice))
									.getPosicionCromosoma()));
			indice++;
		}
		listaConocimientos.clear();
		this.getSeguimiento().append(
				"tamaño: " + conocimientoFitnesses.length
						+ "pero solo usamos la mitad");
		this.getSeguimiento().append("<br>");
		this.getSeguimiento().append(
				"tamaño: " + ListaDeCromosomasMejoresxGeneracion.size());
		this.getSeguimiento().append("<br>");

		poblacion = null;
		return ListaDeCromosomasMejoresxGeneracion;

	}

	public int[][] getResultado() {
		return resultado;
	}

	public void setResultado(int[][] resultado) {
		this.resultado = resultado;
	}

}
