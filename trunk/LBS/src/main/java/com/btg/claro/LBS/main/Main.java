package com.btg.claro.LBS.main;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

import com.btg.claro.LBS.sms.ClienteSMSC;
import com.btg.claro.LBS.sms.SMPPTestPDUEventListener;
import com.btg.claro.LBS.util.ApplicationContextUtil;
import com.btg.claro.LBS.util.Config;
import com.btg.claro.LBS.util.SesionSMSC;

public class Main{
	
	private static Logger log;
	
	private static ClienteSMSC cliente;

	public static void main(String[] args){
		boolean habilitado=true;
		int l=args.length;
		if(l>0){
			if(l==1 && args[0].equals("-h")){
				uso();
				habilitado=false;
			}
			else{
				for(int i=0;i<l;i++){
					String comando=args[i];
					if(comando.equals("-c")){
						i++;
						if(l>i){
							Config.cargarConfiguracion(args[i]);
						}
						else{
							System.out.println("Se debe especificar un archivo de configuracion.");
							habilitado=false;
						}
					}
					else if(comando.equals("-l")){
						i++;
						if(l>i){
							try{
								Log4jConfigurer.initLogging(args[i]);
							}
							catch(FileNotFoundException e){
								System.out.println("Archivo de configuracon incorrecto. Se usa la configuracion por defecto");
							}
						}
						else{
							System.out.println("Se debe especificar un archivo de configuracion para el log (eg. log4j.xml).");
							habilitado=false;
						}
					}
					else{
						System.out.println("Comando ["+comando+"] no reconocido");
						uso();
						i=l;
						habilitado=false;
					}
				}
			}
		}
		if(habilitado){
			log=LoggerFactory.getLogger(Main.class);
			log.info("Iniciando aplicacion LBS");
			boolean conectado=false;
			if(!Config.getPropiedadBoolean("smsc.synchronized")){
				conectado=SesionSMSC.conectar(new SMPPTestPDUEventListener());
			}
			else{
				conectado=SesionSMSC.conectar();
			}
			if(conectado){
				ApplicationContext ctx=new ClassPathXmlApplicationContext("/META-INF/spring/app-context.xml");
				ApplicationContextUtil.setContext(ctx);
				cliente=ctx.getBean(ClienteSMSC.class);
				cliente.iniciar();
			}
		}
	}

	private static void uso(){
		System.out.println("Bienvenido al sistema LBS");
		System.out.println("Uso: java -jar LBS.jar [opciones]");
		System.out.println("Opciones:");
		System.out.println("-c <archivo>\t\tAplica la configuracion proporcionada desde el archivo <archivo>");
		System.out.println("-l <archivo>\t\tConfigura el log desde el archivo <archivo>");
		System.out.println("-h\t\t\tMuestra esta ayuda");
	}

}
