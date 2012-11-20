package pe.com.claro.caef.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class Constantes {
	
	
	public static String RESIDENCIAL = "R";
	public static String CORPORATIVO = "C";

	
	public static String convertirFecha(String fec){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
		String fechaNueva="";
		Date fex ;
		try {
			if(fec.length() <= 0)
			{
				fec = "";
			}
			else
			{
				for (int i=0;i<fec.length();i++){
					fec = fec.replace("T", " ");
					if(fec.substring(i,i+1).equals(".")){
						fechaNueva = fec.substring(0, i);
						fex = sdf.parse(fechaNueva);
						fechaNueva = formatoFecha.format(fex);
					}
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return fechaNueva;
	}
	
	
}
