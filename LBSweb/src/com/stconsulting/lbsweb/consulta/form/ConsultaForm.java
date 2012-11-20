/*
 * ConsultaForm.java
 *
 * Created on 26 de mayo de 2005, 04:49 PM
 */

package com.stconsulting.lbsweb.consulta.form;

import org.apache.struts.action.ActionForm;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.stconsulting.common.bean.Option;
import com.stconsulting.lbsweb.util.Util;
import com.stconsulting.lbsweb.consulta.bean.Mobile;
import com.stconsulting.lbsweb.consulta.bean.ResultadoConsultaWeb;
import com.stconsulting.lbsweb.consulta.bean.ResultadoReporteWeb;
/**
 *
 * @author STconsulting
 */
public class ConsultaForm extends ActionForm{
    
    /**
	 * 
	 */
	private static final long serialVersionUID=4199296137861480225L;
	
	private String tipoRespuesta;
	
    //private int cantidadMobilesConsulta;
    private static List<Option> listaTipoRespuesta;
    
    private List<Mobile> listaMobiles = null;
    
    private ResultadoConsultaWeb resultadoConsulta;
    
    private ResultadoReporteWeb resultadoReporte;
    
    private Date fechaInicio;
    
    private String fechaInicioString;
	
	private Date fechaFin;
	
	private String fechaFinString;
    
	private String queryMapa="";
    
    /** Creates a new instance of ConsultaForm */
    public ConsultaForm() {
        listaMobiles=Util.inicializaMobiles();
    }
    
    public void inicializa() {
        fechaInicio=null;
        fechaFin=null;
        tipoRespuesta="";
        listaMobiles=Util.inicializaMobiles();
        resultadoConsulta=null;
    }
    /**
     * Getter for property listaMobiles.
     * @return Value of property listaMobiles.
     */
    public List<Mobile> getListaMobiles() {
        return listaMobiles;
    }
    
    /**
     * Setter for property listaMobiles.
     * @param listaMobiles New value of property listaMobiles.
     */
    public void setListaMobiles(List<Mobile> listaMobiles) {
        this.listaMobiles = listaMobiles;
    }
    
    /**
     * Getter for property tipoRespuesta.
     * @return Value of property tipoRespuesta.
     */
    public java.lang.String getTipoRespuesta() {
        return tipoRespuesta;
    }
    
    /**
     * Setter for property tipoRespuesta.
     * @param tipoRespuesta New value of property tipoRespuesta.
     */
    public void setTipoRespuesta(java.lang.String tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
    }
    
    /**
     * Getter for property listaTipoRespuesta.
     * @return Value of property listaTipoRespuesta.
     */
    public List<Option> getListaTipoRespuesta() {
        if(listaTipoRespuesta==null){
            listaTipoRespuesta=Util.getListaTipoRpta();            
        }
        return listaTipoRespuesta;
    }
    
    /**
     * Setter for property listaTipoRespuesta.
     * @param listaMobiles New value of property listaTipoRespuesta.
     */
    public void setListaTipoRespuesta(List<Option> listaTipoRespuesta) {
        ConsultaForm.listaTipoRespuesta = listaTipoRespuesta;
    }
    
    /**
     * Getter for property resultadoConsulta.
     * @return Value of property resultadoConsulta.
     */
    public com.stconsulting.lbsweb.consulta.bean.ResultadoConsultaWeb getResultadoConsulta() {
        return resultadoConsulta;
    }    
    
    /**
     * Setter for property resultadoConsulta.
     * @param resultadoConsulta New value of property resultadoConsulta.
     */
    public void setResultadoConsulta(com.stconsulting.lbsweb.consulta.bean.ResultadoConsultaWeb resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }
    
    /**
     * Getter for property resultadoReporte.
     * @return Value of property resultadoReporte.
     */
    public com.stconsulting.lbsweb.consulta.bean.ResultadoReporteWeb getResultadoReporte() {
        return resultadoReporte;
    }
    
    /**
     * Setter for property resultadoReporte.
     * @param resultadoReporte New value of property resultadoReporte.
     */
    public void setResultadoReporte(com.stconsulting.lbsweb.consulta.bean.ResultadoReporteWeb resultadoReporte) {
        this.resultadoReporte = resultadoReporte;
    }
    
    /**
     * Getter for property queryMapa.
     * @return Value of property queryMapa.
     */
    public java.lang.String getQueryMapa() {
        return queryMapa;
    }
    
    /**
     * Setter for property queryMapa.
     * @param queryMapa New value of property queryMapa.
     */
    public void setQueryMapa(java.lang.String queryMapa) {
        this.queryMapa = queryMapa;
    }

	public Date getFechaInicio(){
		if(fechaInicioString!=null){
			Format f=new SimpleDateFormat("dd/MM/yyyy");
			try{
				fechaInicio=(Date) f.parseObject(fechaInicioString);
			}
			catch(ParseException e){
				return null;
			}
		}
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio=fechaInicio;
	}

	public Date getFechaFin(){
		if(fechaFinString!=null){
			Format f=new SimpleDateFormat("dd/MM/yyyy");
			try{
				fechaFin=(Date) f.parseObject(fechaFinString);
			}
			catch(ParseException e){
				return null;
			}
		}
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin){
		this.fechaFin=fechaFin;
	}

	public void setFechaInicioString(String fechaInicioString){
		this.fechaInicioString=fechaInicioString;
	}
	
	public String getFechaInicioString(){
		return fechaInicioString;
	}
	
	public String getFechaFinString(){
		return fechaFinString;
	}

	public void setFechaFinString(String fechaFinString){
		this.fechaFinString=fechaFinString;
	}
    
}
