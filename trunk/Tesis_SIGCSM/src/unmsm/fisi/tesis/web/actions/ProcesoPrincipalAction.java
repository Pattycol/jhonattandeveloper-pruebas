package unmsm.fisi.tesis.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import unmsm.fisi.tesis.servicio.ProcesoPrincipal;
import unmsm.fisi.tesis.web.forms.ConfiguracionForm;
import unmsm.fisi.tesis.web.forms.LoginForm;

public class ProcesoPrincipalAction extends Action {

	
	public ProcesoPrincipalAction() {
        super();
    }

   
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {

    	ConfiguracionForm objetoForm = (ConfiguracionForm) form;
    	
    	int tamanioPoblacion = objetoForm.getT_poblacion();
    	int numeroPacientes = objetoForm.getN_pacientes();
    	int numReglasOMS = objetoForm.getNumReglasOMS();
    	int numReglasGESMM = objetoForm.getNumReglasGESMM();
    	int numReglasAAC = objetoForm.getNumReglasAAC();
    	int numReglasFDI = objetoForm.getNumReglasFDI();
    	int numReglasPNE = objetoForm.getNumReglasPNE();
    	int numReglasCNE = objetoForm.getNumReglasCNE();
    	int numReglasGERI = objetoForm.getNumReglasGERI();
    	int numGeneraciones = objetoForm.getNumGeneraciones();
    	int numHallazgos = objetoForm.getNumHallazgos();
    	
    	double probaCross = objetoForm.getX();
    	double probaMutacion = objetoForm.getU();
    	
    	
    	int reglasToUsarOrganizacion[]= {numReglasOMS,numReglasGESMM,numReglasAAC,numReglasPNE,numReglasFDI,numReglasCNE,numReglasGERI };
    	
    	ProcesoPrincipal procesoPrincipal= new ProcesoPrincipal();
    	procesoPrincipal.cargarConfiguracion(numeroPacientes, tamanioPoblacion,probaCross,probaMutacion,reglasToUsarOrganizacion,numGeneraciones, numHallazgos);
    	procesoPrincipal.iniciar();
    	
    	
        for (int i = 0; i <procesoPrincipal.getResultado().length; i++) {
			
			for (int j = 0; j < procesoPrincipal.getResultado()[0].length; j++) {
				
				//resul.add(String.valueOf(procesoServicio.getResultado()[i][j]) );
				//resul.add(" - ");
				System.out.print(procesoPrincipal.getResultado()[i][j]);
				System.out.print(" , ");
			}
			//resul.add("<br>" );
			System.out.print("\n");
		}
    	request.setAttribute("mensaje", procesoPrincipal.getSeguimientoProceso());
    	//request.setAttribute("resultado", resul);
        return mapping.findForward("success");
        	
        
        
    }

}
