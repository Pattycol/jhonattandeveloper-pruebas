package unmsm.fisi.tesis.web.forms;

import org.apache.struts.action.ActionForm;

public class ConfiguracionForm extends ActionForm {
	
	//Tamaño de Poblacion
	private int t_poblacion;
	
	//Numero de Hallazgos
	private int numHallazgos;
	
	//Numero de Pacientes
	private int n_pacientes;
	
	private int numGeneraciones;
	
	//Numero de Reglas para cada Conocimiento

	public int getNumGeneraciones() {
		return numGeneraciones;
	}

	public void setNumGeneraciones(int numGeneraciones) {
		this.numGeneraciones = numGeneraciones;
	}

	private int numReglasOMS;
	private int numReglasGESMM;
	private int numReglasAAC;
	private int numReglasPNE;
	private int numReglasFDI;
	private int numReglasCNE;
	private int numReglasGERI;
	
	//Probabilidad para aplicar crossover
	private double x;
			
	//Probabilidad para aplicar mutation
	private double u;

	public int getT_poblacion() {
		return t_poblacion;
	}

	public void setT_poblacion(int t_poblacion) {
		this.t_poblacion = t_poblacion;
	}

	public int getNumHallazgos() {
		return numHallazgos;
	}

	public void setNumHallazgos(int numHallazgos) {
		this.numHallazgos = numHallazgos;
	}

	public int getN_pacientes() {
		return n_pacientes;
	}

	public void setN_pacientes(int n_pacientes) {
		this.n_pacientes = n_pacientes;
	}

	public int getNumReglasOMS() {
		return numReglasOMS;
	}

	public void setNumReglasOMS(int numReglasOMS) {
		this.numReglasOMS = numReglasOMS;
	}

	public int getNumReglasGESMM() {
		return numReglasGESMM;
	}

	public void setNumReglasGESMM(int numReglasGESMM) {
		this.numReglasGESMM = numReglasGESMM;
	}

	public int getNumReglasAAC() {
		return numReglasAAC;
	}

	public void setNumReglasAAC(int numReglasAAC) {
		this.numReglasAAC = numReglasAAC;
	}

	public int getNumReglasPNE() {
		return numReglasPNE;
	}

	public void setNumReglasPNE(int numReglasPNE) {
		this.numReglasPNE = numReglasPNE;
	}

	public int getNumReglasFDI() {
		return numReglasFDI;
	}

	public void setNumReglasFDI(int numReglasFDI) {
		this.numReglasFDI = numReglasFDI;
	}

	public int getNumReglasCNE() {
		return numReglasCNE;
	}

	public void setNumReglasCNE(int numReglasCNE) {
		this.numReglasCNE = numReglasCNE;
	}

	public int getNumReglasGERI() {
		return numReglasGERI;
	}

	public void setNumReglasGERI(int numReglasGERI) {
		this.numReglasGERI = numReglasGERI;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getU() {
		return u;
	}

	public void setU(double u) {
		this.u = u;
	}


	
	
}
