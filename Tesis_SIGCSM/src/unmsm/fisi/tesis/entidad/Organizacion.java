package unmsm.fisi.tesis.entidad;

public class Organizacion {
	
	private String nombre;
	private int [][] fuenteInformacion;
	private int numReglasAConsiderar;
	private int numeroReglas;
	private String prioridad;
	
	
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public Organizacion(int row,int column){
		this.fuenteInformacion = new int[row][column];
		
	}
	public Organizacion(){
		
	}
	
	
	
	
	public Organizacion(String nombre, int[][] fuenteInformacion,
			int numReglasAConsiderar, int numeroReglas, String prioridad) {
		super();
		this.nombre = nombre;
		this.fuenteInformacion = fuenteInformacion;
		this.numReglasAConsiderar = numReglasAConsiderar;
		this.numeroReglas = numeroReglas;
		this.prioridad = prioridad;
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
	public int getNumReglasAConsiderar() {
		return numReglasAConsiderar;
	}
	public void setNumReglasAConsiderar(int numReglasAConsiderar) {
		this.numReglasAConsiderar = numReglasAConsiderar;
	}

	
	
	
	

}
