/*
 * DAOException.java 
 *
 * Created on 12 de diciembre de 2003, 04:01 PM
 */

package com.stconsulting.common.persistence;

/**
 * 
 * @author curso
 */
public class DAOException extends java.lang.Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID=-4250952315556576101L;

	/**
	 * Creates a new instance of <code>DAOException</code> without detail
	 * message.
	 */
	public DAOException(){
	}

	/**
	 * Constructs an instance of <code>DAOException</code> with the specified
	 * detail message.
	 * 
	 * @param msg
	 *            the detail message.
	 */
	public DAOException(String msg){
		super(msg);
	}

	public DAOException(String message,Throwable cause){
		// super(message, cause);
	}

	public DAOException(Throwable cause){
		// super(cause);
	}
}
