package unmsm.fisi.tesis.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import unmsm.fisi.tesis.servicio.ProcesoServicio;
import unmsm.fisi.tesis.web.forms.ConfiguracionForm;
import unmsm.fisi.tesis.web.forms.LoginForm;

public class ConfiguracionAction extends Action {

	
	public ConfiguracionAction() {
        super();
    }

   
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
        throws Exception {

    	ConfiguracionForm objetoForm = (ConfiguracionForm) form;
    	
    	int t = objetoForm.getT_poblacion();
    	int n = objetoForm.getN_pacientes();
    	int numReglasOMS = objetoForm.getNumReglasOMS();
    	int numReglasGESMM = objetoForm.getNumReglasGESMM();
    	int numReglasAAC = objetoForm.getNumReglasAAC();
    	int numReglasFDI = objetoForm.getNumReglasFDI();
    	int numReglasPNE = objetoForm.getNumReglasPNE();
    	int numReglasCNE = objetoForm.getNumReglasCNE();
    	int numReglasGERI = objetoForm.getNumReglasGERI();
    	int numGeneraciones = objetoForm.getNumGeneraciones();
    	int numHallazgos = objetoForm.getNumHallazgos();
    	
    	double x = objetoForm.getX();
    	double u = objetoForm.getU();
    	
    	
    	int vector[]= {numReglasOMS,numReglasGESMM,numReglasAAC,numReglasFDI,numReglasPNE,numReglasCNE,numReglasGERI };
    	
    	ProcesoServicio procesoServicio= new ProcesoServicio();
    	procesoServicio.cargarConfiguracion(n, t,x,u,vector,numGeneraciones, numHallazgos);
    	procesoServicio.iniciar();
    	
    	List resul = new ArrayList();
    	
        for (int i = 0; i <procesoServicio.getResultado().length; i++) {
			
			for (int j = 0; j < procesoServicio.getResultado()[0].length; j++) {
				
				//resul.add(String.valueOf(procesoServicio.getResultado()[i][j]) );
				//resul.add(" - ");
				System.out.print(procesoServicio.getResultado()[i][j]);
				System.out.print(" , ");
			}
			//resul.add("<br>" );
			System.out.print("\n");
		}
    	request.setAttribute("mensaje", procesoServicio.getSeguimientoProceso());
    	//request.setAttribute("resultado", resul);
        return mapping.findForward("success");
        	
        
        
    }

}
