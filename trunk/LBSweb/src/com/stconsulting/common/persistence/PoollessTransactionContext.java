/*
 * PoollessTransactionContext.java
 *
 * Created on 2 de septiembre de 2004, 10:41 AM
 */

package com.stconsulting.common.persistence;

import java.util.ResourceBundle;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

public class PoollessTransactionContext{
    
    /** Creates a new instance of PoollessTransactionContext */
    private Logger log;
    
    private java.sql.Connection connection;
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    
    public PoollessTransactionContext() throws TransactionSysException{
        this(null);
    }
    
    public PoollessTransactionContext(ResourceBundle bundle)
        throws TransactionSysException{
        try{log=Logger.getLogger(this.getClass());
            if (bundle==null)
            {
                bundle=ResourceBundle.getBundle("com.stconsulting.resource.defaultConnection");
            }
            username=bundle.getString("username");
            password=bundle.getString("password");
            url=bundle.getString("url");
            driverClassName=bundle.getString("driverClassName");
            log.debug("beginning transaction");
            begin();
        }catch (Throwable t){
            throw new TransactionSysException(t);
        }
    }
    
    /** Getter for property connection.
     * @return Value of property connection.
     *
     */
    public java.sql.Connection getConnection() throws TransactionSysException {
        try{
        if (connection == null) {
            Class.forName(driverClassName);
            connection=DriverManager.getConnection(url,username, password);
        }
        return this.connection;
        }catch (Throwable t){
            throw new TransactionSysException(t);
        }
    }

    public void begin() throws TransactionSysException {        
        try {
            if (connection == null) {
                connection=getConnection();
            }
            connection.setAutoCommit(false);
        }
        catch (java.sql.SQLException sqle) {
            log.debug("Transaction Context: Error establishing autocommit");
            throw new TransactionSysException("Error establishing autocommit");
        }
    }

    public void commit() throws TransactionSysException {
        if (connection == null) {
            log.fatal("Transaction Context: Connection not established");
            throw new TransactionSysException("Connection not established");
        }
        try {
            log.debug("Transaction Context: Commiting transaction");
            connection.commit();
        }
        catch (java.sql.SQLException sqle) {
            log.debug("Transaction Context: Failed to commit transaction.", sqle);
            rollback();
        }
    }

    public void rollback() throws TransactionSysException {
        if (connection == null) {
            log.fatal("Transaction Context: Connection not established");
            throw new TransactionSysException("Connection not established");
        }
        try {
            log.debug("Transaction Context: Attempting to rollback transaction");
            connection.rollback();
        }
        catch (java.sql.SQLException sqle) {
            log.debug("Transaction Context: Failed to rollback transaction.", sqle);
            throw new TransactionSysException(sqle);
        }
    }

    public void close() throws TransactionSysException {
        try {
            if (connection != null && !connection.isClosed()) {
                log.debug("Transaction Context: Closing connection");
                connection.close();
                connection = null;
            }
        }
        catch (java.sql.SQLException sqle) {
            log.debug("Transaction Context: Error while closing connection", sqle);
            throw new TransactionSysException(sqle);
        }
    }
    
}
