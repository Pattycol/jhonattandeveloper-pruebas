
package unmsm.fisi.tesis.servicio;

import java.io.*;
import java.util.StringTokenizer;

import antlr.Parser;

public class LeerFichero {

	public static void main(String [] arg) {
	      File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("C:\\archivo.txt");
	         
	         int Data [][]= new int[3][5];
	         cargarDatosArchivo("C:\\archivo.txt",Data,3,5);
	         
	         
	         /*fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            System.out.println(linea);
	            */
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	   }
	public static void cargarDatosArchivo(String nombreArchivo, int Data [][], int fila , int colum){
		
		 File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (nombreArchivo);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea,caracter;
	         int i=0,j=0;
	         
	         //Data= new int[fila][colum];
	         
	         StringTokenizer frase;
	         while((linea=br.readLine())!=null){
	        	 frase = new StringTokenizer(linea,",");
	        	 
	        	 while(frase.hasMoreTokens()){
	        	
	        		 caracter=frase.nextToken();
		        	 if(!caracter.equals("")){
		        		 Data[i][j]= Integer.parseInt(caracter); 
		        		 j++;
		        		 if(j==colum){
		 	         		j=0;
		 	         		i++;
		 	         	}
		        	 }
	        	 }
	         }
	        	 
	         //System.out.println(linea);
	            
	            for(int k = 0;k<fila;k++){
	            	for(int m = 0;m<colum;m++){
	      	    	  System.out.print(Data[k][m]);
	      	      }
	            	 System.out.println("");
	  	      	}
	         	
	      }catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	
	
}
