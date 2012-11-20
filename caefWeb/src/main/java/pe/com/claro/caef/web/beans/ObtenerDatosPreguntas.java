package pe.com.claro.caef.web.beans;

import java.util.ArrayList;
import java.util.List;


public class ObtenerDatosPreguntas extends AuditTypes {
	
	private String nroPregReg;
	private String nroPregRes;
	private List<ListaPreguntas> lstListaPreguntas;
	private List<ListaPreguntasInfoType> lstNumPreguntas;
	

	public List<ListaPreguntasInfoType> getLstNumPreguntas() {
		return lstNumPreguntas;
	}
	public void setLstNumPreguntas(List<ListaPreguntasInfoType> lstNumPreguntas) {
		this.lstNumPreguntas = lstNumPreguntas;
	}
	public List<ListaPreguntas> getLstListaPreguntas() {
		return lstListaPreguntas;
	}
	public void setLstListaPreguntas(List<ListaPreguntas> lstListaPreguntas) {
		this.lstListaPreguntas = lstListaPreguntas;
	}
	public String getNroPregReg() {
		return nroPregReg;
	}
	public void setNroPregReg(String nroPregReg) {
		this.nroPregReg = nroPregReg;
	}
	public String getNroPregRes() {
		return nroPregRes;
	}
	public void setNroPregRes(String nroPregRes) {
		this.nroPregRes = nroPregRes;
	}

}
