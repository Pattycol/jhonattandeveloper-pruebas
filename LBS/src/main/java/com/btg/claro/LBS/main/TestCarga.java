package com.btg.claro.LBS.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.btg.claro.LBS.util.ApplicationContextUtil;

public class TestCarga{

	/**
	 * @param args
	 */
	public static void main(String[] args){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("/META-INF/spring/carga.xml");
		ApplicationContextUtil.setContext(ctx);
		/*InterfazSMS interfazSMS=ctx.getBean(InterfazSMS.class);
		while(true){
			interfazSMS.ejecutar("972737893","986639533",new Date());
			interfazSMS.ejecutar("986639533","972737893",new Date());
			interfazSMS.ejecutar("972737893","986639533",new Date());
			interfazSMS.ejecutar("986639533","972737893",new Date());
			interfazSMS.ejecutar("986639533","975027919",new Date());
			System.out.println("Hilos en ejecucion: "+Ejecucion.getTotalSMS());
			try{
				Thread.sleep(2000);
			}
			catch(InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

}
