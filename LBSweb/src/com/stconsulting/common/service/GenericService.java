/*
 * GenericService.java
 *
 * Created on 18 de enero de 2004, 09:41 PM
 */

package com.stconsulting.common.service;

import org.apache.log4j.Logger;
/**
 *
 * @author  tcdata
 */
public class GenericService {
    protected Logger log;
    
    /** Creates a new instance of GenericService */
    public GenericService() {
        log = Logger.getLogger(this.getClass());
    }
    
}
