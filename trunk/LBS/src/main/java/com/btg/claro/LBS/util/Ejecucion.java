package com.btg.claro.LBS.util;

import java.util.LinkedList;
import java.util.List;

import com.btg.claro.LBS.interfaz.impl.InterfazSMS;
import com.btg.claro.LBS.interfaz.impl.InterfazWEBImpl;

public class Ejecucion{
	
	private static List<InterfazSMS> listaSMS;
	
	private static List<InterfazWEBImpl> listaWEB;
	
	private static int[] consultasHLR=new int[Config.getPropiedadInt("numero.hlrs")];
	
	private static int[] consultasVLR=new int[Config.getPropiedadInt("numero.vlrs")];
	
	public static synchronized void agregarHiloSMS(InterfazSMS hilo){
		if(listaSMS==null){
			listaSMS=new LinkedList<InterfazSMS>();
		}
		listaSMS.add(hilo);
	}

	public static synchronized void removerHiloSMS(InterfazSMS interfazSMS){
		if(listaSMS!=null){
			listaSMS.remove(interfazSMS);
		}
	}
	
	public static synchronized void agregarHiloWEB(InterfazWEBImpl hilo){
		if(listaWEB==null){
			listaWEB=new LinkedList<InterfazWEBImpl>();
		}
		listaWEB.add(hilo);
	}

	public static synchronized void removerHiloWEB(InterfazWEBImpl interfazWEB){
		if(listaWEB!=null){
			listaWEB.remove(interfazWEB);
		}
	}
	
	public static synchronized int getTotalSMS(){
		if(listaSMS==null){
			listaSMS=new LinkedList<InterfazSMS>();
		}
		return listaSMS.size();
	}
	
	public static synchronized int getTotalWEB(){
		if(listaWEB==null){
			listaWEB=new LinkedList<InterfazWEBImpl>();
		}
		return listaWEB.size();
	}
	
	public static synchronized int getConsultasHLR(int hlr){
		return consultasHLR[hlr];
	}
	
	public static synchronized void agregarConsultasHLR(int hlr){
		consultasHLR[hlr]++;
	}
	
	public static synchronized void quitarConsultasHLR(int hlr){
		consultasHLR[hlr]--;
	}
	
	public static synchronized int getConsultasVLR(int vlr){
		return consultasVLR[vlr];
	}
	
	public static synchronized void agregarConsultasVLR(int vlr){
		consultasVLR[vlr]++;
	}
	
	public static synchronized void quitarConsultasVLR(int vlr){
		consultasVLR[vlr]--;
	}

}
