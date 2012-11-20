/*
 * ConsultaService.java
 *
 * Created on 25 de mayo de 2005, 03:05 PM
 */

package com.stconsulting.lbsweb.ws;

import com.stconsulting.common.util.Constants;
import java.rmi.RemoteException;
import org.apache.log4j.Logger;

/**
 *
 * @author  STConsulting
 */
public class ConsultaService {
    
    private static Logger log;
    
    /** Creates a new instance of ConsultaService */
    public ConsultaService() {
        log  = Logger.getLogger(this.getClass());
    }
    
    public com.stconsulting.lbsweb.ws.ResultadoConsulta consultaLBS(com.stconsulting.lbsweb.ws.ParametroConsulta parametro) throws RemoteException {        
        
        com.stconsulting.lbsweb.ws.ResultadoConsulta resultado = null;
        com.stconsulting.lbsweb.ws.ConsultaControllerService controllerService = null;
        try{
            log.info("Dentro del metodo 'consultaLBS'");
            controllerService = new ConsultaControllerService();
            resultado=new com.stconsulting.lbsweb.ws.ResultadoConsulta();
            resultado.setCodResultado(Constants.COD_RES_WS_CONSULTA_ERROR_INTERNO);
            resultado.setMensaje(Constants.MSJ_RES_WS_CONSULTA_ERROR_INTERNO);
            
            
            resultado=controllerService.consultaLBS(parametro);
            log.info("Finaliza el metodo 'consultaLBS'");
            return resultado;
        }catch(Exception e){
            log.error(e);
            throw new RemoteException(e.getMessage());
        }
    }
}
