/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.servicio;

import java.util.List;


import unmsm.fisi.tesis.dao.ConfiguracionDAO;
import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.entidad.Paciente;
import unmsm.fisi.tesis.si.AlgoritmoGenetico;

/**
 *
 * @author Jhon
 */
public class ProcesoPrincipal {

    PacienteServicio pacienteServicio;
    Configuracion configuracion;
    AlgoritmoGenetico algoritmoGenetico;
    Cromosoma cromosomaFinal;
    private StringBuilder seguimientoProceso;
    int Resultado [][];
    
    
    
    

    public int[][] getResultado() {
		return Resultado;
	}

	public void setResultado(int[][] resultado) {
		Resultado = resultado;
	}

	public Cromosoma getCromosomaFinal() {
		return cromosomaFinal;
	}

	public void setCromosomaFinal(Cromosoma cromosomaFinal) {
		this.cromosomaFinal = cromosomaFinal;
	}

	public Cromosoma iniciar(){


       // pacienteServicio= new PacienteServicio();

//        try {
           // List<Paciente> listaPaciente =   pacienteServicio.listaPacientes(configuracion.getNumeroPacientes());
           // if(listaPaciente!= null){
                algoritmoGenetico = new AlgoritmoGenetico();
                //cromosoma= new Cromosoma();
                
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
//        } catch (Exception e) {

//        	 for (int i = 0; i < algoritmoGenetico.getSeleccion().getFitnnesMayores().size() ; i++) {
//        		 System.out.println(" [ ");
//			
//        		System.out.println(algoritmoGenetico.getSeleccion().
//					getFitnnesMayores().elementAt(i).toString());
//				System.out.println(" ] - ");
//        	 }
        	 
        	// e.printStackTrace();
//        	 
//        }finally{
//        
//             return cromosomaFinal;
//
//        }


        return cromosomaFinal;
        
    }

    public void cargarConfiguracion(int numeroPacientes, int numeroPoblacion,
			double probabilidadCrossover_x, double probabilidadMutacion_y,
			int[] reglasToUsarOrganizacion, int numGeneraciones, int numHallazgos){
    	
        configuracion=new Configuracion(numeroPacientes, numeroPoblacion, probabilidadCrossover_x,
        		probabilidadMutacion_y,reglasToUsarOrganizacion, numGeneraciones,numHallazgos);
        
        
        String codigoOrganizaciones = "1,2,3,4,5,6,7";
        String numReglasConsiderar = reglasToUsarOrganizacion[0]+","+reglasToUsarOrganizacion[1]+","+
        							 reglasToUsarOrganizacion[2]+","+reglasToUsarOrganizacion[3]+","+
        							 reglasToUsarOrganizacion[4]+","+reglasToUsarOrganizacion[5]+","+
        							 reglasToUsarOrganizacion[6];
        
        ConfiguracionDAO confADO = new ConfiguracionDAO();
        	confADO.guardarValoresConfiguracion(configuracion, codigoOrganizaciones, numReglasConsiderar);
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

	
	public StringBuilder getSeguimientoProceso() {
		return seguimientoProceso;
	}

	public void setSeguimientoProceso(StringBuilder seguimientoProceso) {
		this.seguimientoProceso = seguimientoProceso;
	}

    
    
}
