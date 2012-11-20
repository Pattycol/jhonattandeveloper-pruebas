/*
 * Billing.java
 *
 * Created on 27 de mayo de 2005, 02:25 PM
 */

package com.stconsulting.lbsweb.connector;

import com.stconsulting.common.util.Constants;
import com.stconsulting.common.util.Helper;
import com.tim.billing.ws.client.*;

import org.apache.log4j.Logger;
/**
 *
 * @author STconsulting
 */
public class BillingService {
    protected Logger log = null;
    
    /** Creates a new instance of Billing */
    public BillingService() {
        log = Logger.getLogger(this.getClass());
    }
    
    public String consultaSaldo(String numero,double monto) throws com.stconsulting.common.service.ServiceException{
        String codResultado=Constants.COD_RESULTADO_BILLING_NO_TIENE_SALDO;
        try{
            String url = Helper.getProperty(Constants.WEBSERVICE_BUNDLE,Constants.PROPERTY_URL_WS_BILLING);//"http://services.tim.com.pe/SWBillingGWY/SWBillingGWY"; 
            String portName = Helper.getProperty(Constants.WEBSERVICE_BUNDLE,Constants.PROPERTY_NAME_WS_BILLING);//"SWBillingGWYPort";
            String canal = Helper.getProperty(Constants.WEBSERVICE_BUNDLE,Constants.PROPERTY_CANAL_WS_BILLING);//"SMSMAIL";
            String user = Helper.getProperty(Constants.WEBSERVICE_BUNDLE,Constants.PROPERTY_USER_WS_BILLING);//"network";
            String password= Helper.getProperty(Constants.WEBSERVICE_BUNDLE,Constants.PROPERTY_PSW_WS_BILLING);//"redgateway";
            SWBillingGWYLocator service = new SWBillingGWYLocator();
            service.setSWBillingGWYPortEndpointAddress(url);
            service.setSWBillingGWYPortWSDDServiceName(portName);

            SWBillingGWYPort port = service.getSWBillingGWYPort();
            log.debug("Antes de invocar al servicio web de Billing");
            log.debug("url : "+url);
            log.debug("servicio : "+portName);
            log.debug("parametros : "+numero+","+monto);
            int resultadoInt=port.obtenerResultadoWSDetalle(numero,monto,canal,user,password);
            if(resultadoInt==Constants.COD_BILLING_PREPAGO_TIENE_SALDO ||
               resultadoInt==Constants.COD_BILLING_POSTPAGO_TIENE_PERMISO){
                codResultado = Constants.COD_RESULTADO_BILLING_TIENE_SALDO; 
            }else{
                codResultado = Constants.COD_RESULTADO_BILLING_NO_TIENE_SALDO; 
            }
            log.debug("Luego de invocar al servicio : "+codResultado);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new com.stconsulting.common.service.ServiceException(e.getMessage());
        }
        return codResultado;
    }
}
