package pe.com.claro.caef.web.util;



/**
 * 
 * 
 * 
 * @author JASC
 *
 */

public class ExcelUtil {

	
	public static String convertirPath(String path){

		String path2="";
		
		for (int i = 0; i < path.length(); i++) {
			
			if(path.substring(i, i+1).compareTo("\\")==0){
				
				path2 = path2 + "/";
			}else{
				path2 = path2 + path.substring(i, i+1);
				
			}
			
			
		}
		
		return path2;
		
		
	}
	
}
