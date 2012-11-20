/*
 * ServiceException.java
 *
 * Created on 18 de enero de 2004, 09:39 PM
 */

package com.stconsulting.common.service;

/**
 *
 * @author  tcdata
 */
public class ServiceException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>ServiceException</code> without detail message.
     */
    public ServiceException() {
    }
    
    
    /**
     * Constructs an instance of <code>ServiceException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable cause) {
        //super(msg, cause);
    }
    
    public ServiceException(Throwable cause) {
        //super(cause);
    }
}
