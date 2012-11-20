package pe.com.claro.caef.web.action;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.command.servicio.SmsForm;

import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import sun.misc.BASE64Encoder;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class EnvioSmsFormAction extends GeneralAction implements Preparable {
	
	
	
	SmsForm smsFrom;

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public String sendSMS(){
		
		
		smsFrom =  new SmsForm();
		
		Usuario usuario = new Usuario();
		/*Map codServicio = ActionContext.getContext().getSession();
		usuario = (Usuario)codServicio.get("codServicio");*/
		usuario=getUsuario();
		
		
		if(usuario!=null){
			smsFrom.setDominio(MENSAJES_CONFIG.DOMINIO_PERMITIDO_SMS);
			String nroEnc=null;
			try {
				nroEnc = encriptar(usuario.getTelefonoMiClaroFija(),MENSAJES_CONFIG.SEMILLA_ENCRIPTACION_SMS);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				LOG.info(e.toString());
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				LOG.info(e.toString());
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				LOG.info(e.toString());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				LOG.info(e.toString());
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				LOG.info(e.toString());
			}
			smsFrom.setFonoOrigen(nroEnc);
			
			HttpServletRequest request=  (HttpServletRequest)ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
			
			smsFrom.setIp(request.getRemoteAddr());
			
			return Action.SUCCESS;
	        
		}
		
		return "ERROR";
		
		
	}
	private String encriptar(String claveOriginal, String semilla) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
		String claveEncriptada = null;
		// Generamos una clave secreta.
		SecretKeySpec desKey = new SecretKeySpec(new String((semilla.trim().concat("99999999")).substring(0, 8)).getBytes(), "DES");
		Cipher cipher;
		
		cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, desKey);
		byte[] claveEncriptadaBytes = cipher.doFinal( claveOriginal.getBytes() );
		claveEncriptada = new BASE64Encoder().encode( claveEncriptadaBytes );
		
		return claveEncriptada;
	}

	public SmsForm getSmsFrom() {
		return smsFrom;
	}

	public void setSmsFrom(SmsForm smsFrom) {
		this.smsFrom = smsFrom;
	}

	
	

}
