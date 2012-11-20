package pe.com.claro.caef.web.action.filter;

import java.util.ArrayList;
import java.util.List;

import pe.com.claro.caef.web.beans.DirectorioAbonado;

public class RegistrarPublicacionDirectorioAbonadoFilter {
	
	private List<DirectorioAbonado> lstDirectorioAbonado;
	private String numero;
	private String flag;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<DirectorioAbonado> getLstDirectorioAbonado() {
		return lstDirectorioAbonado;
	}

	public void setLstDirectorioAbonado(List<DirectorioAbonado> lstDirectorioAbonado) {
		this.lstDirectorioAbonado = lstDirectorioAbonado;
	}

}
