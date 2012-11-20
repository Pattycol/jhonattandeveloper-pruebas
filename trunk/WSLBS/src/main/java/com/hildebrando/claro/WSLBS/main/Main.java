package com.hildebrando.claro.WSLBS.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private static Logger logger;
	
	public static void main(String[] args) {
		logger= LoggerFactory.getLogger(Main.class);
		logger.info("Iniciando WS Usuario");
		
		new ClassPathXmlApplicationContext("/META-INF/applicationContext.xml");
		
		while(true){

			try{
			
				Thread.sleep(1000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		
	}

}
