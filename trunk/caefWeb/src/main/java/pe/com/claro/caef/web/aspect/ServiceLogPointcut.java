package pe.com.claro.caef.web.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ServiceLogPointcut {

	@Pointcut("execution (* pe.com.claro.caef.web.services.impl.*.*(..))")
	public void servicePointcut(){
	
	}
}
