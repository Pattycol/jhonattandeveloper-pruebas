package com.btg.claro.LBS.vlr;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.btg.claro.LBS.util.Config;

public class DatosMovil{
	
	private String idCelda;
	
	private boolean mnr;
	
	private boolean imsid;
	
	private String HLR;
	
	private String VLR;
	
	private Date fechaInterna;
	
	private int error;

	public String getIdCelda(){
		return idCelda;
	}

	public void setIdCelda(String idCelda){
		this.idCelda=idCelda;
	}

	public boolean isMnr(){
		return mnr;
	}

	public void setMnr(boolean mnr){
		this.mnr=mnr;
	}

	public boolean isImsid(){
		return imsid;
	}

	public void setImsid(boolean imsid){
		this.imsid=imsid;
	}

	public String getHLR(){
		return HLR;
	}

	public void setHLR(String hLR){
		HLR=hLR;
	}

	public String getVLR(){
		return VLR;
	}

	public void setVLR(String vLR){
		VLR=vLR;
	}

	public Date getFechaInterna(){
		return fechaInterna;
	}

	public void setFechaInterna(Date fechaInterna){
		this.fechaInterna=fechaInterna;
	}

	public String getFecha(){
		if(fechaInterna!=null){
			Format f=new SimpleDateFormat(Config.getPropiedad("lbs.formatoFecha"));
			try{
				return f.format(fechaInterna);
			}
			catch(IllegalArgumentException e){
				return fechaInterna.toString();
			}
		}
		return "";
	}

	public int getError(){
		return error;
	}

	public void setError(int error){
		this.error=error;
	}

}
