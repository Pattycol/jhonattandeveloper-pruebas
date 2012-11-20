/*
 * Util.java
 *
 * Created on 26 de mayo de 2005, 05:17 PM
 */

package com.stconsulting.lbsweb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.stconsulting.common.bean.Option;
import com.stconsulting.common.util.Constants;
import com.stconsulting.common.util.Helper;

import com.stconsulting.lbsweb.consulta.bean.*;

/**
 * 
 * @author STconsulting
 */
public class Util{
	
	private static Logger log=Logger.getLogger(Util.class);

	public Util(){

	}

	public static List<Mobile> inicializaMobiles(){
		List<Mobile> listaMobiles=new ArrayList<Mobile>();
		int numMax=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_NUM_MOBILES));
		for(int i=0;i < numMax;i++){
			listaMobiles.add(new Mobile(""));
		}
		return listaMobiles;
	}

	public static List<Option> getListaTipoRpta(){
		List<Option> listaTipoRespuesta=new ArrayList<Option>();

		Option opcion1=new Option();
		opcion1.setCodigo(Constants.COD_TIPO_RPTA_PTOREF);
		opcion1.setDescripcion(Constants.DESC_TIPO_RPTA_PTOREF);
		listaTipoRespuesta.add(opcion1);
		/*
		 * Option opcion2=new Option();
		 * opcion2.setCodigo(Constants.COD_TIPO_RPTA_LATLON);
		 * opcion2.setDescripcion(Constants.DESC_TIPO_RPTA_LATLON);
		 * listaTipoRespuesta.add(opcion2); Option opcion3=new Option();
		 * opcion3.setCodigo(Constants.COD_TIPO_RPTA_UTM);
		 * opcion3.setDescripcion(Constants.DESC_TIPO_RPTA_UTM);
		 * listaTipoRespuesta.add(opcion3);
		 */
		return listaTipoRespuesta;
	}

	public static List<Mobile> obtieneListaMobilesValidos(List<Mobile> listaMobiles){
		List<Mobile> listaMobilesResultado=new ArrayList<Mobile>();
		log.debug("Entro a sacar lista de moviles validos");
		for(int i=0;i < listaMobiles.size();i++){
			Mobile mobile=listaMobiles.get(i);
			log.debug("Valida numero " + i + ") : " + mobile.getNumero());
			if(mobile.getNumero() != null && !mobile.getNumero().equals(""))
				listaMobilesResultado.add(mobile);
		}
		return listaMobilesResultado;
	}

	public static boolean verificaExistenciaNumero(String mobile,List<Mobile> listaMobiles){
		boolean existe=false;
		if(listaMobiles != null){
			for(Mobile mobileLista : listaMobiles){
				if(mobileLista != null && mobileLista.equals(mobile)){
					existe=true;
					break;
				}
			}
		}
		return existe;
	}

	public static boolean validaMobiles(List<Mobile> listaMobiles){
		boolean valido=true;

		for(int i=0;i < listaMobiles.size();i++){
			String mobile=listaMobiles.get(i).getNumero();
			boolean existe=false;
			for(int j=0;j < listaMobiles.size();j++){
				if(i != j && listaMobiles.get(j).getNumero().equals(mobile)){
					existe=true;
					break;
				}
			}
			if(existe){
				valido=false;
				break;
			}
		}
		return valido;
	}

	public static void agregaMobile(String mobile,List<Mobile> listaMobiles){
		String mobileLista;
		if(listaMobiles != null){
			for(int i=0;i < listaMobiles.size();i++){
				mobileLista=listaMobiles.get(i).getNumero();
				if(mobileLista == null || mobileLista.equals("")){
					listaMobiles.get(i).setNumero(mobile);
					break;
				}
			}
		}
	}

	// EMD
	public static List<Option> getListaEstadosTarea(String filtro){
		List<Option> listaEstados=new ArrayList<Option>();
		if(Constants.COD_ESTADO_INACTIVO.equals(filtro) || Constants.COD_ESTADO_ACTIVO.equals(filtro) || Constants.COD_TODOS.equals(filtro)){
			Option opcion1=new Option();
			opcion1.setCodigo(Constants.COD_ESTADO_ACTIVO);
			opcion1.setDescripcion(Constants.DESC_ESTADO_ACTIVO);
			listaEstados.add(opcion1);
			Option opcion2=new Option();
			opcion2.setCodigo(Constants.COD_ESTADO_INACTIVO);
			opcion2.setDescripcion(Constants.DESC_ESTADO_INACTIVO);
			listaEstados.add(opcion2);
		}
		if(Constants.COD_ESTADO_INACTIVO.equals(filtro) || Constants.COD_ESTADO_ACTIVO.equals(filtro) || Constants.COD_ESTADO_CERRADO.equals(filtro) || Constants.COD_TODOS.equals(filtro)){
			Option opcion3=new Option();
			opcion3.setCodigo(Constants.COD_ESTADO_CERRADO);
			opcion3.setDescripcion(Constants.DESC_ESTADO_CERRADO);
			listaEstados.add(opcion3);
		}

		return listaEstados;
	}

	public static List<Option> getListaPeriodos(){
		List<Option> listaPeriodos=new ArrayList<Option>();
		Option opcion1=new Option();
		opcion1.setCodigo(Constants.COD_PERIODO_DIARIO);
		opcion1.setDescripcion(Constants.DESC_PERIODO_DIARIO);
		listaPeriodos.add(opcion1);
		Option opcion2=new Option();
		opcion2.setCodigo(Constants.COD_PERIODO_SEMANAL);
		opcion2.setDescripcion(Constants.DESC_PERIODO_SEMANAL);
		listaPeriodos.add(opcion2);
		return listaPeriodos;
	}

	/*
	 * public static ArrayList getListaHorarios() { ArrayList listaHorarios =
	 * new ArrayList();
	 * 
	 * Option opcion1=new Option();
	 * opcion1.setCodigo(Constants.COD_HORARIO_DIA_COMPLETO);
	 * opcion1.setDescripcion(Constants.DESC_HORARIO_DIA_COMPLETO);
	 * listaHorarios.add(opcion1); Option opcion2=new Option();
	 * opcion2.setCodigo(Constants.COD_HORARIO_0_8);
	 * opcion2.setDescripcion(Constants.DESC_HORARIO_0_8);
	 * listaHorarios.add(opcion2); Option opcion3=new Option();
	 * opcion3.setCodigo(Constants.COD_HORARIO_8_16);
	 * opcion3.setDescripcion(Constants.DESC_HORARIO_8_16);
	 * listaHorarios.add(opcion3); Option opcion4=new Option();
	 * opcion4.setCodigo(Constants.COD_HORARIO_16_24);
	 * opcion4.setDescripcion(Constants.DESC_HORARIO_16_24);
	 * listaHorarios.add(opcion4); return listaHorarios; }
	 */

	public static String[] getSelectedIndex(String listaDias){

		String[] listaSeleccionados=null;
		int tamanho=listaDias.length();
		if(tamanho > 0){
			listaSeleccionados=new String[tamanho];
			for(int i=0;i < tamanho;i++){
				listaSeleccionados[i]=listaDias.substring(i,i + 1);
			}
		}
		else{
			listaSeleccionados=new String[]{};
		}

		return listaSeleccionados;
	}

	// Obtener Listas Rango de Horas
	public static List<RangoHora> getListaHorarios(){
		List<RangoHora> listaHorarios=new ArrayList<RangoHora>();
		int rangoHoras=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_RANGO_SEPARACION_HORAS));
		int numsepfinal=0;
		if((Constants.NUM_HORAS_DIA % rangoHoras) == 0){
			numsepfinal=Constants.NUM_HORAS_DIA / rangoHoras;
		}
		else{
			numsepfinal=(Constants.NUM_HORAS_DIA / rangoHoras) + 1;
		}
		int inicio=0,fin=0,multiplo=1;
		RangoHora rangoHora=new RangoHora();
		rangoHora.setCodHorario(Constants.COD_HORARIO_DIA_COMPLETO);
		rangoHora.setDescHorario(Constants.DESC_HORARIO_DIA_COMPLETO);
		listaHorarios.add(rangoHora);
		for(int i=1;i <= numsepfinal;i++){
			rangoHora=new RangoHora();
			multiplo=i * rangoHoras;
			if(multiplo > Constants.NUM_HORAS_DIA)
				fin+=(Constants.NUM_HORAS_DIA - fin);
			else
				fin+=rangoHoras;
			log.debug("Rango Inicio Hora : " + inicio + " Rango Fin :" + fin);
			rangoHora.setCodHorario(Integer.toString(i));
			rangoHora.setDescHorario(Helper.getDescripcionHora(inicio) + " - " + Helper.getDescripcionHora(fin));
			rangoHora.setHoraInicio(inicio);
			rangoHora.setHoraFin(fin);
			listaHorarios.add(rangoHora);
			inicio=fin;
		}
		return listaHorarios;
	}

	public static List<Option> getListaIntervalos(){
		List<Option> listaIntervalos=new ArrayList<Option>();
		int tiempoIntervalo=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_INTERVALO_TIEMPO_CONSULTA));
		int intervalos=60 / tiempoIntervalo;

		for(int i=1;i <= intervalos;i++){
			Option opcion1=new Option();
			int minutos=tiempoIntervalo * i;
			opcion1.setCodigo(minutos + "");
			if(i != intervalos)
				opcion1.setDescripcion(minutos + " minutos");
			else
				opcion1.setDescripcion(" 1 hora");
			listaIntervalos.add(opcion1);
		}

		return listaIntervalos;
	}
	
	public static String toMD5(String entrada){
		try{
			MessageDigest d=MessageDigest.getInstance("MD5");
			d.update(entrada.getBytes());
			byte[] md5=d.digest();
			StringBuffer result=new StringBuffer();
	        for(byte b : md5){
	            int low=b & 0x0F;
	            int high=b & 0xF0;
	            result.append(Integer.toHexString(high).substring(0, 1));
	            result.append(Integer.toHexString(low));
	        }
			return result.toString();
		}
		catch(NoSuchAlgorithmException e){
		}
		return null;
	}
	
	public static String generaAleatorio(){
		
		int numeroAleatorio = 0;
		String pass = "";
		
		for(int i= 0;i<4;i++){
			numeroAleatorio = (int) (Math.random()*9+1);
			pass = pass + numeroAleatorio;
		}
		
		return pass;
	}
}
