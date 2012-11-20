/*
 * BatchCobroAutomathicExecuter.java
 *
 * Created on 10 de junio de 2005, 03:02 PM
 */

package com.stconsulting.common.listener;

import org.apache.log4j.Logger;
import java.util.*;
import com.stconsulting.common.util.Constants;
import com.stconsulting.common.bean.*;
import com.stconsulting.lbsweb.consulta.bean.Cobro;
import com.stconsulting.lbsweb.consulta.service.*;
import com.stconsulting.lbsweb.connector.*;
/**
 *
 * @author STCosulting
 */
public class BatchCobroAutomathicExecuter {
    /**
     * Este metodo es el que se encarga de realizar el batchero de cobranza
     * de las consultas registradas
     */
    protected Logger log;
    /** Creates a new instance of BatchCobroAutomathicExecuter */
    public BatchCobroAutomathicExecuter() {
    }
    
    public void startAutomathicExecuter(){
        //Logger log = Logger.getLogger(this.getClass());
        try {
            log.debug("Entrando al ListenerCobro "+new java.util.Date());
            long period = Constants.TIEMPO_REPETICION_COBRO;
            log.debug("Periodo :"+period);
            //Realizar inmediatamente la consulta de las tareas
            long delay = Constants.DELAY_COBRO; 
            
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                       ExecuteTask();
                    }
            }, delay, period);
            
        }        
        catch (Exception e) {
            System.err.println("Exception ocurrred, while attempting start Notifyer: " + e);
        }
    }
    
    private void ExecuteTask(){
        
        try {

            log.debug("Entrando a ExecuteTask Cobro : "+new java.util.Date());
            CobroService serviceCobro=new CobroService();
            CdrGeneratorService serviceCdr=new CdrGeneratorService();
            List<Cobro> listaCobro=serviceCobro.cargaListaCobroPorEstado(Constants.COD_ESTADO_COBRO_PENDIENTE);
            log.debug("Lista de Cobro : "+listaCobro.size());
            if(listaCobro.size()>0){
                int [] numConsecutivo=serviceCobro.getNextCdrPK();
                Resultado resultado=serviceCdr.generaCDR(listaCobro,numConsecutivo);
                String codResultado;
                if(resultado.getCodigo().equals(Constants.OK)){
                    serviceCobro.actualizaConsecutivoCdr(numConsecutivo[0], numConsecutivo[1]+listaCobro.size()-1);
                    //actualizar el consecutivo
                    log.debug("Se va a actualizar el estado de cobro");
                    codResultado=serviceCobro.actualizaEstadoCobro(listaCobro,Constants.COD_ESTADO_COBRO_CERRADO);
                    if(codResultado.equals(Constants.ERROR)){
                        serviceCdr.eliminaArchivo(resultado.getDescripcion());
                    }
                }
            }
        }catch (Exception e) {
            log.debug("Error en Execute Task Cobro :"+e);
        }
      
         log.debug("Ya se realizo la ejecucion de la cobranza");
    }
}
