package pe.com.claro.caef.web.beans;

public class ListaCorreos extends AuditTypes{

	private String sCodigoCorreo;
	private String sCorreo;
	
	public ListaCorreos(String sCodigoCorreo,String sCorreo){
		super();
		this.sCodigoCorreo=sCodigoCorreo;
		this.sCorreo = sCorreo;
	}
	
	public String getsCodigoCorreo() {
		return sCodigoCorreo;
	}
	public void setsCodigoCorreo(String sCodigoCorreo) {
		this.sCodigoCorreo = sCodigoCorreo;
	}
	public String getsCorreo() {
		return sCorreo;
	}
	public void setsCorreo(String sCorreo) {
		this.sCorreo = sCorreo;
	}
	
}
