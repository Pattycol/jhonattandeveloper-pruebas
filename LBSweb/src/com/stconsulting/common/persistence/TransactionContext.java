/*
 * TransactionContext.java
 *
 * Created on July 17, 2003, 5:10 PM
 */

package com.stconsulting.common.persistence;

import java.io.Serializable;
import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 * @author Administrator
 */
public class TransactionContext implements Serializable{

	/** Holds value of property dataSource. */
	//private javax.sql.DataSource dataSource;

	/**
	 * 
	 */
	private static final long serialVersionUID=573833316694992065L;

	/** Holds value of property connection. */
	private java.sql.Connection connection;

	private Logger log;

	/** Creates a new instance of TransactionContext */
	public TransactionContext() throws TransactionSysException{
		try{
			PooledConnectionFactory factory=PooledConnectionFactory.getInstance();
			if(factory == null)
				log.debug("factory es null");
			connection=factory.getConnection();
			log=Logger.getLogger(TransactionContext.class);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TransactionSysException(e);
		}
	}

	/**
	 * Getter for property connection.
	 * 
	 * @return Value of property connection.
	 * 
	 */
	public Connection getConnection() throws TransactionSysException{
		if(connection == null){
			log.fatal("Transaction Context: Connection not established");
			throw new TransactionSysException("Connection not established");
		}
		return this.connection;
	}

	public void begin() throws TransactionSysException{
		if(connection == null){
			log.fatal("Transaction Context: Connection not established");
			throw new TransactionSysException("Connection not established");
		}
		try{
			connection.setAutoCommit(false);
		}
		catch(java.sql.SQLException sqle){
			log.debug("Transaction Context: Error establishing autocommit");
			throw new TransactionSysException("Error establishing autocommit");
		}
	}

	public void begin(boolean supportTransactions) throws TransactionSysException{
		log.debug("Transaction Context: Establishing transaction support to " + supportTransactions);
		if(connection == null){
			log.fatal("Transaction Context: Connection not established");
			throw new TransactionSysException("Connection not established");
		}
		try{
			if(supportTransactions){
				connection.setAutoCommit(false);
			}
			else{
				connection.setAutoCommit(true);
			}
		}
		catch(java.sql.SQLException sqle){
			log.debug("Transaction Context: Error establishing autocommit");
			throw new TransactionSysException("Error establishing autocommit");
		}
	}

	public void commit() throws TransactionSysException{
		if(connection == null){
			log.fatal("Transaction Context: Connection not established");
			throw new TransactionSysException("Connection not established");
		}
		try{
			log.debug("Transaction Context: Commiting transaction");
			connection.commit();
		}
		catch(java.sql.SQLException sqle){
			log.debug("Transaction Context: Failed to commit transaction.",sqle);
			rollback();
		}
	}

	public void rollback() throws TransactionSysException{
		if(connection == null){
			log.fatal("Transaction Context: Connection not established");
			throw new TransactionSysException("Connection not established");
		}
		try{
			log.debug("Transaction Context: Attempting to rollback transaction");
			connection.rollback();
		}
		catch(java.sql.SQLException sqle){
			log.debug("Transaction Context: Failed to rollback transaction.",sqle);
			throw new TransactionSysException(sqle);
		}
	}

	public void close() throws TransactionSysException{
		try{
			if(connection != null && !connection.isClosed()){
				log.debug("Transaction Context: Closing connection");
				connection.close();
				connection=null;
			}
		}
		catch(java.sql.SQLException sqle){
			log.debug("Transaction Context: Error while closing connection",sqle);
			throw new TransactionSysException(sqle);
		}
	}

	protected void finalize() throws java.lang.Throwable{
		try{
			//dataSource=null;
			if(connection != null && !connection.isClosed()){
				log.debug("Transaction Context: Closing connection in finalize method");
				connection.close();
				connection=null;
			}
		}
		catch(java.sql.SQLException ignore){
		}
	}
}
