package com.stconsulting.common.util;

import com.stconsulting.common.bean.*;
import java.util.*;
import java.text.*;
import javax.servlet.http.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.stconsulting.common.util.ConstantsMQT2;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.InvocationTargetException;

import com.stconsulting.common.form.ControlsForm;
import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.lbsweb.util.Util;

public class Helper{
	
	private static Logger log=Logger.getLogger(Helper.class);

	public static String getDescripcion(String codigo,List<Option> lista){
		String result="";

		if(lista != null && lista.size() > 0){
			for(Option option : lista){
				if(codigo.equals(option.getCodigo())){
					result=option.getDescripcion();
					break;
				}

			}
		}
		// log.debug("Resultado del Helper.getDescripcion "+result);
		return result;
	}

	public static String getAlias(String codigo,List<Option> lista){
		String result="";
		if(lista != null && lista.size() > 0){
			for(Option option : lista){
				if(codigo.equals(option.getCodigo())){
					result=option.getAlias();
					break;
				}

			}
		}
		log.debug("Resultado del Helper.getAlias " + result);
		return result;
	}

	/**
	 * Obttiene una cadena de valores separada por comas
	 * 
	 * @autor STConsulting
	 */
	public static String getCadena(List<String> lista){
		String cadena="";
		if(lista != null){
			for(int i=0;i < lista.size();i++){
				if(i > 0){
					cadena+=",'" + lista.get(i) + "'";
				}
				else{
					cadena+="'" + lista.get(i) + "'";
				}
			}
		}
		return cadena;

	}

	public static String getDate(){
		String ret="";
		ret=getDateTime().substring(0,10);
		return ret;
	}

	public static String getDateTime(){
		String ret="";
		java.util.Date date=new java.util.Date();
		Timestamp ts=new Timestamp(date.getTime());
		ret=ts.toString();
		return ret;
	}

	public static String formateaFecha(java.util.Date theDate,String formato){
		String result="";
		if(theDate != null)
			result=new SimpleDateFormat(formato).format(theDate);
		return result;
	}

	public static java.util.Date parseaFecha(String dateAsString,String formatoFecha) throws Exception{

		java.util.Date result=null;
		SimpleDateFormat format=new SimpleDateFormat(formatoFecha);
		format.setLenient(false);
		result=format.parse(dateAsString);

		return result;
	}

	public static String formateaFecha(String dateAsString,String formatoFecha) throws Exception{

		String result="";
		if(dateAsString != null)
			result=new SimpleDateFormat(formatoFecha).format(parseaFecha(dateAsString,formatoFecha));

		return result;
	}

	public static String getRangoHora(int hour){

		String rangoHoras="";
		String strPreHora="";
		//String strhour=Integer.toString(hour);
		String stram="am";
		String strpm="pm";

		if(hour == 12){
			rangoHoras=Integer.toString(hour) + ConstantsMQT2.FORMATO_RANGO_HORAS + "-" + getPreFijoHora(hour + 1) + ConstantsMQT2.FORMATO_RANGO_HORAS + " " + strpm;
		}
		else if(hour >= 0 && hour < 12){
			rangoHoras=getPreFijoHora(hour) + ConstantsMQT2.FORMATO_RANGO_HORAS + "-" + getPreFijoHora(hour + 1) + ConstantsMQT2.FORMATO_RANGO_HORAS + " " + stram;
		}
		else{
			strPreHora=getPreFijoHora(hour);
			rangoHoras=strPreHora + ConstantsMQT2.FORMATO_RANGO_HORAS + "-" + getPreFijoHora(Integer.parseInt(strPreHora) + 1) + ConstantsMQT2.FORMATO_RANGO_HORAS + " " + strpm;
		}

		// log.debug("RangoHoras Final: "+ rangoHoras);
		return rangoHoras;
	}

	public static String getPreFijoHora(int hour){
		String strhour=Integer.toString(hour);
		String strPreHora=strhour;
		try{
			if(hour == 0){
				strPreHora="12";
			}
			else if(hour > 12 && hour < 24){
				strPreHora=Integer.toString(hour % 12);
			}
			strPreHora=strPreHora.length() > 1 ? strPreHora : "0" + strPreHora;
		}
		catch(Exception e){
			System.out.print(e.toString());
		}

		return strPreHora;

	}

	public static String formateaDecimal(String numero,String formato){
		String result="";
		double theDouble=new Double(numero).doubleValue();
		result=new DecimalFormat(formato).format(theDouble);
		return result;
	}

	public static String formateaDecimal(double numero,String formato){
		String result="";
		result=new DecimalFormat(formato).format(numero);
		return result;
	}

	public static String formateaDecimalLocale(double bdcNumero,String formato){
		String strFormateado="";
		Locale objLoc=new Locale("en","US");

		DecimalFormat objNf=(DecimalFormat) NumberFormat.getInstance(objLoc);
		objNf.applyLocalizedPattern(formato);
		strFormateado=objNf.format(bdcNumero);

		// log.debug("HelperFormateaDecimalLocale: "+strFormateado);
		return strFormateado;
	}

	public static String checkValue(String str){
		return str == null ? "" : str.trim();
	}

	public static void removeSessions(HttpServletRequest request){

		HttpSession session=request.getSession();
		// EMD
		session.removeAttribute("fechaHoy");
		session.removeAttribute(Constants.SESSION_NUEVA_TAREA);
		session.removeAttribute(Constants.SESSION_CANT_MOBILES_SEL);
		session.removeAttribute(Constants.SESSION_FECHA_INICIO);
		session.removeAttribute(Constants.SESSION_FECHA_FIN);
	}

	public static String getProperty(String archivo,String key){
		String property="";
		try{
			java.util.ResourceBundle bundle=java.util.ResourceBundle.getBundle(archivo);
			if(bundle != null)
				property=bundle.getString(key);
		}
		catch(Exception ignore){
		}
		return property;
	}

	public static double getPropertyDouble(String archivo,String key){
		double property=0;
		String str=getProperty(archivo,key);
		try{
			property=Double.parseDouble(str);
		}
		catch(Exception ignore){
		}
		return property;
	}

	public static String getSchema(String source){
		String table="";
		java.util.ResourceBundle bundle=java.util.ResourceBundle.getBundle(Constants.CONNECTION_BUNDLE);
		if(Constants.BD_LBS.equals(source)){
			table=bundle.getString(Constants.BD_LBS);
		}

		return table;
	}

	// Diferencia de Dias
	public static int getDiferenciaDias(Date fechaInicial,Date fechaFinal){
		Calendar d1=Calendar.getInstance();
		Calendar d2=Calendar.getInstance();
		d1.setTime(fechaInicial);
		d2.setTime(fechaFinal);
		if(d1.after(d2)){ // swap dates so that d1 is start and d2 is end
			Calendar swap=d1;
			d1=d2;
			d2=swap;
		}
		int days=d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR) + 1;
		int y2=d2.get(Calendar.YEAR);
		if(d1.get(Calendar.YEAR) != y2){
			d1=(Calendar) d1.clone();
			do{
				days+=d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR,1);
			}while(d1.get(Calendar.YEAR) != y2);
		}
		log.debug("Helper:Diferencia de Dias " + days);
		return days;
	}

	public static void updateArrayChecksForm(ActionForm form,HttpServletRequest request,String nameTables[],String nameChecks[]){
		for(int j=0;j < nameTables.length;j++){
			try{
				String property[]=(String[]) PropertyUtils.getSimpleProperty(form,nameChecks[j]);
				((ControlsForm) form).setCheckControl(j,property);
			}
			catch(IllegalAccessException e){
				e.printStackTrace();
			}
			catch(InvocationTargetException e){
				e.printStackTrace();
			}
			catch(NoSuchMethodException e){
				e.printStackTrace();
			}
			//String _curForm11[]=((ControlsForm) form).getCheckControl()[j];
			String tableID=nameTables[j];
			String checkID=nameChecks[j];
			String strChecksSelected="_checksSelected" + checkID;
			String strChecksForm="_checksForm" + tableID;
			Object curObject=request.getSession().getAttribute(strChecksSelected);
			Object checksForm=request.getSession().getAttribute(strChecksForm);
			if(checksForm != null){
				String _checksSelected[]=new String[0];
				if(curObject != null)
					_checksSelected=(String[]) curObject;
				String _checksForm[]=(String[]) checksForm;
				String _curForm[]=((ControlsForm) form).getCheckControl()[j];
				String _curAux[]=deleteArray(_checksSelected,_checksForm);
				String _curFinal[]=addArray(_curAux,_curForm);
				/*for(int i=0;i < _curFinal.length;i++){
					String s=_curFinal[i];
				}*/

				request.getSession().setAttribute(strChecksSelected,_curFinal);
				((ControlsForm) form).setCheckControl(j,_curFinal);
			}
		}

	}

	private static String[] deleteArray(String _checksSelected[],String _checksForm[]){
		int _sizeChecksSelected=0;
		int _checksMatch=0;
		int _newSize=0;
		String arrResult[]=null;
		StringBuffer stringWillDeleled=new StringBuffer("|");
		try{
			if(_checksSelected != null)
				_sizeChecksSelected=_checksSelected.length;
			for(int i=0;i < _sizeChecksSelected;i++){
				for(int j=0;j < _checksForm.length;j++)
					if(_checksSelected[i].equals(_checksForm[j])){
						_checksMatch++;
						stringWillDeleled.append(new StringBuffer(_checksForm[j]));
						stringWillDeleled.append(new StringBuffer("|"));
					}

			}

			_newSize=_sizeChecksSelected - _checksMatch;
			arrResult=new String[_newSize];
			int j=0;
			for(int i=0;i < _checksSelected.length;i++){
				String string=_checksSelected[i];
				if(stringWillDeleled.toString().indexOf("|" + string + "|") == -1){
					arrResult[j]=string;
					j++;
				}
			}

		}
		catch(Exception e){

		}
		return arrResult;
	}

	private static String[] addArray(String _curAux[],String _curForm[]){
		String arrResult[]=null;
		if(_curForm == null)
			_curForm=new String[0];
		int _newSize=_curAux.length + _curForm.length;
		try{
			arrResult=new String[_newSize];
			for(int i=0;i < _curAux.length;i++)
				arrResult[i]=_curAux[i];

			for(int i=0;i < _curForm.length;i++)
				arrResult[_curAux.length + i]=_curForm[i];

		}
		catch(Exception e){

		}
		return arrResult;
	}

	// EMD
	public static String getDescDias(int codMes){
		String descMes="";
		String strcodMes=Integer.toString(codMes);
		if(Constants.COD_LUNES.equals(strcodMes)){
			descMes="Lun";
		}
		else if(Constants.COD_MARTES.equals(strcodMes)){
			descMes="Mar";
		}
		else if(Constants.COD_MIERCOLES.equals(strcodMes)){
			descMes="Mie";
		}
		else if(Constants.COD_JUEVES.equals(strcodMes)){
			descMes="Jue";
		}
		else if(Constants.COD_VIERNES.equals(strcodMes)){
			descMes="Vie";
		}
		else if(Constants.COD_SABADO.equals(strcodMes)){
			descMes="Sab";
		}
		else if(Constants.COD_DOMINGO.equals(strcodMes)){
			descMes="Dom";
		}
		return descMes;
	}

	public static String getListaItemsSelected(String[] listaDias){
		String listaDiasSel="";
		for(int i=0;i < listaDias.length;i++){
			listaDiasSel+=listaDias[i];
		}
		return listaDiasSel;

	}

	// Cambios EMD 08/06/2005
	public static boolean isContenidoDia(String listaDias,int dia){
		boolean isContenido=false;
		String strDia=Integer.toString(dia);
		for(int i=0;i < listaDias.length();i++){
			if(listaDias.substring(i,i + 1).equals(strDia)){
				isContenido=true;
				break;
			}
		}
		return isContenido;

	}

	// Batchero
	public static int getAddMinutes(int minutesCurrent){
		int minutesAdd=0;
		//Constants constantes=new Constants();
		//Helper helper=new Helper();
		int maxmin=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_CONSULTA));
		try{
			int inicio=1;
			int indice=1;
			while(inicio < minutesCurrent){
				inicio=indice * maxmin;
				indice++;
			}
			minutesAdd=inicio - minutesCurrent;
		}
		catch(Exception e){
			log.debug("Exception getAddMinutes :" + e);
		}
		log.debug("Minutes Add :" + minutesAdd);
		return minutesAdd;

	}

	public static long getRestMinutesCurrent(Date fechaCurrent,Tarea tarea){
		long difDias=0;
		long difMin=0;
		try{
			String codHorario=tarea.getCodHorario();
			int rangoHoras=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_RANGO_SEPARACION_HORAS));
			int numsepfinal=0;
			if((Constants.NUM_HORAS_DIA % rangoHoras) == 0){
				numsepfinal=Constants.NUM_HORAS_DIA / rangoHoras;
			}
			else{
				numsepfinal=(Constants.NUM_HORAS_DIA / rangoHoras) + 1;
			}
			Calendar fechaFin=new GregorianCalendar();
			log.debug("Cod Horario " + codHorario);
			if(codHorario != null && !codHorario.equals("")){

				if(Constants.COD_HORARIO_DIA_COMPLETO.equals(codHorario) || numsepfinal == Integer.parseInt(codHorario)){
					log.debug("Entrando a Ultima Hora");
					fechaFin.add(Calendar.DATE,1);
					fechaFin.set(Calendar.HOUR_OF_DAY,0);
					fechaFin.set(Calendar.MINUTE,0);
					fechaFin.set(Calendar.SECOND,0);
				}
				else{
					log.debug("Entrando Hora Inicio :" + tarea.getHoraInicio() + " Hora Fin: " + tarea.getHoraFin());
					fechaFin.set(Calendar.HOUR_OF_DAY,tarea.getHoraFin());
					fechaFin.set(Calendar.MINUTE,0);
					fechaFin.set(Calendar.SECOND,0);
				}
			}

			int minCurrent=0;
			int horaCurrent=0;
			Calendar fechaInicio=new GregorianCalendar();
			fechaInicio.setTime(fechaCurrent);
			horaCurrent=fechaInicio.get(Calendar.HOUR_OF_DAY);
			minCurrent=fechaInicio.get(Calendar.MINUTE);
			if(!Constants.COD_HORARIO_DIA_COMPLETO.equals(codHorario)){
				if(horaCurrent >= tarea.getHoraInicio() && horaCurrent <= tarea.getHoraFin()){
					fechaInicio.add(Calendar.MINUTE,Helper.getAddMinutes(minCurrent));
					fechaInicio.set(Calendar.SECOND,0);
				}
				else if(horaCurrent <= tarea.getHoraInicio() || horaCurrent < tarea.getHoraFin()){
					fechaInicio.set(Calendar.HOUR_OF_DAY,tarea.getHoraInicio());
					fechaInicio.set(Calendar.MINUTE,0);
					fechaInicio.set(Calendar.SECOND,0);
				}
				else{
					fechaInicio.set(Calendar.HOUR_OF_DAY,tarea.getHoraFin() + 1);
					fechaInicio.set(Calendar.MINUTE,0);
					fechaInicio.set(Calendar.SECOND,0);
				}
			}
			else{
				fechaInicio.add(Calendar.MINUTE,Helper.getAddMinutes(minCurrent));
				fechaInicio.set(Calendar.SECOND,0);
			}

			log.debug("Fecha Inicio " + fechaInicio.getTime());
			log.debug("Fecha Fin " + fechaFin.getTime());

			difDias=fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis();
			if(difDias > 0)
				difMin=(difDias / (1000 * 60));
			else
				difMin=0;
			log.debug("Diferencia de Minutos Fecha Hoy " + difMin);

		}
		catch(Exception e){
			log.debug("Exception getRestMinutesCurrent :" + e);
		}

		return difMin;
	}

	// EMD
	public static String getDescripcionHora(int hour){

		String descHora="";
		if((hour > 0 && hour < 12)){
			descHora=Integer.toString(hour) + "am";
		}
		else if(hour >= 12 && hour < 23){
			if(hour != 12)
				descHora=Integer.toString(hour - 12) + "pm";
			else
				descHora=hour + "pm";
		}
		else if(hour == 24 || hour == 0){
			descHora="12am";
		}
		log.debug("Desc Hora " + descHora);
		return descHora;
	}

	// EMD
	public static RangoHora getRangoHora(String codigo,List<RangoHora> lista){
		RangoHora rango=null;
		if(lista != null && lista.size() > 0){
			for(RangoHora rangoHora : lista){
				if(codigo.equals(rangoHora.getCodHorario())){
					rango=rangoHora;
					break;
				}
			}
		}
		return rango;
	}

	public static RangoHora getRangoHora(Integer horaInicio, Integer horaFin){
		List<RangoHora> rango = Util.getListaHorarios();
		if(rango != null && !rango.isEmpty()){
			for(RangoHora actual : rango){
				if(actual.getHoraInicio() == horaInicio.intValue() && actual.getHoraFin() == horaFin.intValue()){
					return actual;
				}
			}
		}
		return null;
	}
	
	// Obtener consecutivo con formato
	public static String getFormatoConsecutivo(int consecutivo,int numCaracteres){
		String fmtConsecutivo="";
		int cantCompletar=numCaracteres - Integer.toString(consecutivo).length();
		for(int i=0;i < cantCompletar;i++){
			fmtConsecutivo+="0";
		}
		fmtConsecutivo+=Integer.toString(consecutivo);

		return fmtConsecutivo;
	}

	public static int getMinutesDifference(Calendar calInicio,Calendar calFin){
		int minutos=0;
		int horas=0;

		horas=calFin.get(Calendar.HOUR_OF_DAY) - calInicio.get(Calendar.HOUR_OF_DAY);
		minutos=horas * 60;
		minutos+=calFin.get(Calendar.MINUTE) - calInicio.get(Calendar.MINUTE);
		return minutos;
	}
}
