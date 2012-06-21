package unmsm.fisi.tesis.entidad;

public class Organizacion {
	
	private String nombre;
	private int [][] fuenteInformacion;
	private int numConocParticipante;
	private int numeroReglas;
	
	
	public Organizacion(int row,int column){
		this.fuenteInformacion = new int[row][column];
		
	}
	public Organizacion(){
		
	}
	
	
	public int getNumeroReglas() {
		return numeroReglas;
	}
	public void setNumeroReglas(int numeroReglas) {
		this.numeroReglas = numeroReglas;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int[][] getFuenteInformacion() {
		return fuenteInformacion;
	}
	public void setFuenteInformacion(int[][] fuenteInformacion) {
		this.fuenteInformacion = fuenteInformacion;
	}
	public int getNumConocParticipante() {
		return numConocParticipante;
	}
	public void setNumConocParticipante(int numConocParticipante) {
		this.numConocParticipante = numConocParticipante;
	}
	

	

	
	
	
	

}
