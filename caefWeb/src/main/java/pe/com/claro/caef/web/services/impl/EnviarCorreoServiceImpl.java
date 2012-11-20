package pe.com.claro.caef.web.services.impl;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.services.EnviarCorreoService;
import pe.com.claro.caef.web.util.CorreoDatos;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.util.PropertiesCAEFMail;

@Service("enviarCorreoService")
public class EnviarCorreoServiceImpl implements EnviarCorreoService {

	public void enviarCorreo(CorreoDatos datos) throws Exception
	{
		
			Session session = Session.getDefaultInstance(getPropiedades(),
					null);
			//session.setDebug(true);

			MimeMessage message = new MimeMessage(session);
			String correoEmisor = PropertiesCAEF.CORREO_EMISOR; 

			message.setFrom(new InternetAddress(correoEmisor));
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(datos.getDestinatario()));
			message.setSubject(datos.getAsunto());
			message.setContent(getMultiPartes(datos.getContenido(), datos
					.getArchivo()));
			message.setText(datos.getContenido(),"UTF-8", "html");
			
			Transport transporte = session.getTransport("smtp");
			transporte.connect(getUser(correoEmisor), PropertiesCAEF.CORREO_CLAVE);
			transporte.sendMessage(message, message.getAllRecipients());
			transporte.close();

		
	}
	
	public  String contenidoCorreo(String saludoCompleto,StringBuffer cuerpoCorreo)
	{	
		StringBuffer sb = new StringBuffer();
		sb.append(saludoCompleto);
		sb.append( PropertiesCAEFMail.CABECERA_PRIMERA_PARTE);
		sb.append( PropertiesCAEF.BASE_URL);
		sb.append(PropertiesCAEFMail.CABECERA_SEGUNDA_PARTE);
		sb.append(cuerpoCorreo.toString());
		sb.append( PropertiesCAEFMail.PIE  );
		return sb.toString();
	}
	
	private static Properties getPropiedades() {
		Properties propiedad = new Properties();
		propiedad.put("mail.smtp.host", PropertiesCAEF.CORREO_SERVIDOR_IP);
		//propiedad.setProperty("mail.smtp.starttls.enable", "true");
		propiedad.setProperty("mail.transport.protocol", "smtp");
		propiedad.setProperty("mail.smtp.port", PropertiesCAEF.CORREO_SERVIDOR_PUERTO);
		propiedad.setProperty("mail.smtp.auth", "true");
		return propiedad;
	}
	
	private static MimeMultipart getMultiPartes(String contenido, File archivo)
			throws MessagingException {
		MimeMultipart multiPartes = new MimeMultipart();
		if (contenido != null)
			multiPartes.addBodyPart(getContenido(contenido));
		if (archivo != null)
			multiPartes.addBodyPart(getArchivo(archivo));
		return multiPartes;
	}
	
	private static BodyPart getContenido(String texto)
			throws MessagingException {
		BodyPart parte = new MimeBodyPart();
		parte.setContent(texto,"text/html");
		return parte;
	}

	private static BodyPart getArchivo(File archivo) throws MessagingException {
		BodyPart parte = new MimeBodyPart();
		FileDataSource fileDataSource = new FileDataSource(archivo);
		DataHandler dataHandler = new DataHandler(fileDataSource);
		parte.setDataHandler(dataHandler);
		parte.setFileName(archivo.getName());
		return parte;
	}
	
	private static String getUser(String correo) {
		return correo.substring(0, correo.indexOf('@'));
	}
}
