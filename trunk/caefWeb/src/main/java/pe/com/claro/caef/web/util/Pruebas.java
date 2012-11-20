package pe.com.claro.caef.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.jfree.util.Log;

public class Pruebas {
	
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
	
	public Pruebas() {
		
		String codigo = "DHF-0001";
		
		for(int i=0;i<codigo.length();i++){
			
			if(codigo.substring(i, i+1).equals("-")){

			}
			
		}
		
		
		//Timestamp timestamp = new Timestamp(date.getTime());
		//t2 = timestamp.toString();

		Calendar ca = Calendar.getInstance();	
		System.out.println("año: "+ca.get(Calendar.YEAR));
		
		boolean paso = isFechaValida("2010-07-20 12:14:21");
		
		//if(paso)
			//System.out.println("es fecha valida");
		//else
			//System.out.println("ya fue xD");
		
		String st2 = "2011-07-20T12:14:21.000-05:00";
		String nuevaFecha="";
		
		
		try{
			for (int i=0;i<st2.length();i++){
				st2 = st2.replace("T", " ");
				if(st2.substring(i,i+1).equals(".")){
					nuevaFecha = st2.substring(0, i);
					//System.out.println("Nueva fecha: "+nuevaFecha);
				}
				
			}
			
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//Date fex = sdf.parse("2011-07-20");
			//String fechaActual = formatoFecha.format(fex);
			//System.out.println("wowowowow: "+convertirFecha("2011-07-20T12:14:21.000-05:00"));
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String convertirFecha(String fec){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
		String fechaNueva="";
		Date fex ;
		try {
			for (int i=0;i<fec.length();i++){
				fec = fec.replace("T", " ");
				if(fec.substring(i,i+1).equals(".")){
					fechaNueva = fec.substring(0, i);
					fex = sdf.parse(fechaNueva);
					fechaNueva = formatoFecha.format(fex);
				}
				
			}
			
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fechaNueva;
	}
	
	
	 public  boolean isFechaValida(String fechax) {
		  try {
		      formatoFecha.setLenient(false);
		      formatoFecha.parse(fechax);
		  } catch (ParseException e) {
			  Log.info(e.toString());
		      return false;
		  }
		  return true;
	 }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Pruebas();

	}

}
