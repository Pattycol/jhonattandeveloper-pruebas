package pe.com.claro.caef.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaServicioClienteTotal;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;

@Component
@Scope(value="prototype")
public class SeleccionarServicioAction extends GeneralAction implements Preparable {
	
	@Autowired
	private ComunService comunService;
	
	private List<ConsultaServicioClienteTotal> lstConsultaServicioClienteTotal;
	private Map<Integer, Boolean> checkboxes;
	private int posicionCheck;

	public Map<Integer, Boolean> getCheckboxes() {
		return checkboxes;
	}

	public void setCheckboxes(Map<Integer, Boolean> checkboxes) {
		this.checkboxes = checkboxes;
	}

	private List<String> lstRadio;
	
	public List<String> getLstRadio() {
		return lstRadio;
	}

	public void setLstRadio(List<String> lstRadio) {
		this.lstRadio = lstRadio;
	}

	public List<ConsultaServicioClienteTotal> getLstConsultaServicioClienteTotal() {
		return lstConsultaServicioClienteTotal;
	}

	public void setLstConsultaServicioClienteTotal(
			List<ConsultaServicioClienteTotal> lstConsultaServicioClienteTotal) {
		this.lstConsultaServicioClienteTotal = lstConsultaServicioClienteTotal;
	}
	
	public SeleccionarServicioAction()
	{
		lstRadio = new ArrayList<String>();
	}
	
	public void prepare() throws Exception
	{
		//SOLO PARA ESTE CASO DEBERIAMOS DE EL GETUSUARIO() PARA PODER LLENAR LA SESSION DE CODSRV 
		lstConsultaServicioClienteTotal = comunService.ConsultarServiciosClienteTotal(getUsuario());
		
	}
	public String getConsultarServiciosClienteTotal()
	{
		return Action.INPUT;
	}
	
	/*public String execute()
	{
		int j = 0;
		for(int i = 0; i < lstConsultaServicioClienteTotal.size(); i++)
		{
			if(checkboxes.get(i).booleanValue() == true)
				j++;
		}
		
		
		if(j > 1)
		{
			addActionError( getText("Solo debe de seleccionar un servicio"));
			return Action.INPUT;
		}
		else if(j == 0)
		{
			addActionError( getText("Debe de seleccionar un servicio"));
			return Action.INPUT;
		}
		else
		{
			for(int i = 0; i < lstConsultaServicioClienteTotal.size(); i++)
			{
				if(checkboxes.get(i).booleanValue() == true)
				{
					
					Map codServicio = ActionContext.getContext().getSession();
					Usuario usuario = new Usuario();
					usuario.setCodigoServicio(lstConsultaServicioClienteTotal.get(i).getCodigoServicio().toString());
					usuario.setCodigoProducto(lstConsultaServicioClienteTotal.get(i).getIdProducto().toString());
					codServicio.put("codServicio", usuario);

				}
			}
			return Action.SUCCESS;
		}
	}*/
	public String execute(){
		
		
		if(posicionCheck == -1)
		{
			addActionError( MENSAJES_CONFIG.SELECCIONAR_SERVICIO_MENSAJE1);
			return Action.INPUT;
		}
		else
		{
			Map codServicio = ActionContext.getContext().getSession();
			Usuario usuario = new Usuario();
			usuario.setCodigoServicio(lstConsultaServicioClienteTotal.get(posicionCheck).getCodigoServicio().toString());
			usuario.setCodigoProducto(lstConsultaServicioClienteTotal.get(posicionCheck).getIdProducto().toString());
			codServicio.put("codServicio", usuario);
		}

		return Action.SUCCESS;
	}

	public int getPosicionCheck() {
		return posicionCheck;
	}

	public void setPosicionCheck(int posicionCheck) {
		this.posicionCheck = posicionCheck;
	}

}
