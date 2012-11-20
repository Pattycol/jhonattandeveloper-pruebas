/*
 * DecompressedFile.java
 *
 * Created on 19 de agosto de 2004, 10:11 AM
 */

package com.stconsulting.common.util;


import java.io.File;
/**
 *
 * @author  STConsulting
 */
public class DecompressedFile extends java.io.File{
    
    /**
	 * 
	 */
	private static final long serialVersionUID=1L;
	private String comment;
    /** Creates a new instance of DecompressedFile */
    public DecompressedFile(String path, String name) {
        super(path,name);
    }
    
    public DecompressedFile(File f){
        super(f.getAbsolutePath());
        try{
            setComment( ((DecompressedFile)f).getComment());
        }catch(Exception ignore){}
    }
    
    /**
     * Getter for property comment.
     * @return Value of property comment.
     */
    public java.lang.String getComment() {
        return comment;
    }
    
    /**
     * Setter for property comment.
     * @param comment New value of property comment.
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }
    
}
