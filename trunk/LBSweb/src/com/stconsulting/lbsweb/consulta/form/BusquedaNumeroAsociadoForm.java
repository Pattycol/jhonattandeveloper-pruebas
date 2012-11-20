/*
 * ParametrosBusquedaNumeroForm.java
 *
 * Created on 2 de junio de 2005, 10:50 AM
 */

package com.stconsulting.lbsweb.consulta.form;

import org.apache.struts.action.ActionForm;
import java.util.ArrayList;
import java.util.List;

import com.stconsulting.lbsweb.consulta.bean.ResultadoBusquedaNumero;
import com.stconsulting.common.form.ControlsForm;

/**
 * 
 * @author STCosulting
 */
public class BusquedaNumeroAsociadoForm extends ActionForm implements ControlsForm{

	/**
	 * 
	 */
	private static final long serialVersionUID=-9146105618256299281L;
	private String mobile;
	private List<ResultadoBusquedaNumero> listaResultadoBusqueda;
	// manejo de checks
	private String[] mobiles;
	private String[][] checkControl=new String[1][];

	/** Creates a new instance of BusquedaNumeroAsociadoForm */
	public BusquedaNumeroAsociadoForm(){
		listaResultadoBusqueda=new ArrayList<ResultadoBusquedaNumero>();
	}

	public void reset(org.apache.struts.action.ActionMapping actionMapping,javax.servlet.http.HttpServletRequest httpServletRequest){
		super.reset(actionMapping,httpServletRequest);
		mobile="";
		// listaResultadoBusqueda=new ArrayList();
		mobiles=null;
	}

	public void inicializa(){
		mobile="";
		listaResultadoBusqueda=new ArrayList<ResultadoBusquedaNumero>();
	}

	/**
	 * Getter for property mobile.
	 * 
	 * @return Value of property mobile.
	 */
	public java.lang.String getMobile(){
		return mobile;
	}

	/**
	 * Setter for property mobile.
	 * 
	 * @param mobile
	 *            New value of property mobile.
	 */
	public void setMobile(java.lang.String mobile){
		this.mobile=mobile;
	}

	/**
	 * Getter for property listaResultadoBusqueda.
	 * 
	 * @return Value of property listaResultadoBusqueda.
	 */
	public List<ResultadoBusquedaNumero> getListaResultadoBusqueda(){
		return listaResultadoBusqueda;
	}

	/**
	 * Setter for property listaResultadoBusqueda.
	 * 
	 * @param listaResultadoBusqueda
	 *            New value of property listaResultadoBusqueda.
	 */
	public void setListaResultadoBusqueda(List<ResultadoBusquedaNumero> listaResultadoBusqueda){
		this.listaResultadoBusqueda=listaResultadoBusqueda;
	}

	public String[][] getCheckControl(){
		return checkControl;
	}

	public void setCheckControl(int index,String[] strings){
		checkControl[index]=strings;
		if(index == 0)
			mobiles=strings;
	}

	/**
	 * Getter for property mobiles.
	 * 
	 * @return Value of property mobiles.
	 */
	public java.lang.String[] getMobiles(){
		return this.mobiles;
	}

	/**
	 * Setter for property mobiles.
	 * 
	 * @param mobiles
	 *            New value of property mobiles.
	 */
	public void setMobiles(java.lang.String[] mobiles){
		this.mobiles=mobiles;
	}

}
