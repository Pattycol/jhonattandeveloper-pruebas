/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.servicio;

import java.util.List;


import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.entidad.Paciente;
import unmsm.fisi.tesis.si.AlgoritmoGenetico;

/**
 *
 * @author Jhon
 */
public class ProcesoServicio {

    PacienteServicio pacienteServicio;
    Configuracion configuracion;
    AlgoritmoGenetico algoritmoGenetico;
    Cromosoma cromosoma;
    private StringBuilder seguimientoProceso;
    int Resultado [][];
    
    
    
    

    public int[][] getResultado() {
		return Resultado;
	}

	public void setResultado(int[][] resultado) {
		Resultado = resultado;
	}

	public Cromosoma iniciar(){


        pacienteServicio= new PacienteServicio();

        try {
           // List<Paciente> listaPaciente =   pacienteServicio.listaPacientes(configuracion.getNumeroPacientes());
           // if(listaPaciente!= null){
                algoritmoGenetico = new AlgoritmoGenetico();
                cromosoma= new Cromosoma();
                
                algoritmoGenetico.ejecutar(configuracion);
                // cromosoma = algoritmoGenetico.getCromosoma();
                this.setSeguimientoProceso(algoritmoGenetico.getSeguimiento());
                this.getSeguimientoProceso().append("<br><br><br>");
                
                for (int i = 0; i < algoritmoGenetico.getSeleccion().getFitnnesMayores().size() ; i++) {
                	this.getSeguimientoProceso().append(" [ ");
                	System.out.println(" [ ");
					this.getSeguimientoProceso().append(algoritmoGenetico.getSeleccion().
							getFitnnesMayores().elementAt(i).toString());
					System.out.println(algoritmoGenetico.getSeleccion().
							getFitnnesMayores().elementAt(i).toString());
					this.getSeguimientoProceso().append(" ] - ");
					System.out.println(" ] - ");
				}
                
                
                this.setResultado(algoritmoGenetico.getSeleccion().getResultado());
                
                algoritmoGenetico= null;

           // }
        } catch (Exception e) {

        	 for (int i = 0; i < algoritmoGenetico.getSeleccion().getFitnnesMayores().size() ; i++) {
        		 System.out.println(" [ ");
			
        		System.out.println(algoritmoGenetico.getSeleccion().
					getFitnnesMayores().elementAt(i).toString());
				System.out.println(" ] - ");
        	 }
        	 
        	 e.printStackTrace();
        	 
        }finally{
        
             return cromosoma;

        }



        
    }

    public void cargarConfiguracion(int numeroPacientes, int numeroPoblacion,
			double probabilidadCrossover_x, double probabilidadMutacion_y,
			int[] reglasxOrganizacion, int numGeneraciones, int numHallazgos){
    	
        configuracion=new Configuracion(numeroPacientes, numeroPoblacion, probabilidadCrossover_x,
        		probabilidadMutacion_y,reglasxOrganizacion, numGeneraciones,numHallazgos);

    }

	public PacienteServicio getPacienteServicio() {
		return pacienteServicio;
	}

	public void setPacienteServicio(PacienteServicio pacienteServicio) {
		this.pacienteServicio = pacienteServicio;
	}

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

	public AlgoritmoGenetico getAlgoritmoGenetico() {
		return algoritmoGenetico;
	}

	public void setAlgoritmoGenetico(AlgoritmoGenetico algoritmoGenetico) {
		this.algoritmoGenetico = algoritmoGenetico;
	}

	public Cromosoma getCromosoma() {
		return cromosoma;
	}

	public void setCromosoma(Cromosoma cromosoma) {
		this.cromosoma = cromosoma;
	}

	public StringBuilder getSeguimientoProceso() {
		return seguimientoProceso;
	}

	public void setSeguimientoProceso(StringBuilder seguimientoProceso) {
		this.seguimientoProceso = seguimientoProceso;
	}

    
    
}
