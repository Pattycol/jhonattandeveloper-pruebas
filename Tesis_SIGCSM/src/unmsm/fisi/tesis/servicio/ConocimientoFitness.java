package unmsm.fisi.tesis.servicio;

public class ConocimientoFitness {
	
	private int numeroGeneracion;
	private double valorFitness;
	private int posicionCromosoma;
	
	
	
	public ConocimientoFitness(double valorFitness, int posicionCromosoma) {
		//super();
		this.valorFitness = valorFitness;
		this.posicionCromosoma = posicionCromosoma;
	}
	
	public ConocimientoFitness() {
		
	}
	public double getValorFitness() {
		return valorFitness;
	}
	public void setValorFitness(double valorFitness) {
		this.valorFitness = valorFitness;
	}
	public int getPosicionCromosoma() {
		return posicionCromosoma;
	}
	public void setPosicionCromosoma(int posicionCromosoma) {
		this.posicionCromosoma = posicionCromosoma;
	}

	public int getNumeroGeneracion() {
		return numeroGeneracion;
	}

	public void setNumeroGeneracion(int numeroGeneracion) {
		this.numeroGeneracion = numeroGeneracion;
	}
	
	
	
	

}
