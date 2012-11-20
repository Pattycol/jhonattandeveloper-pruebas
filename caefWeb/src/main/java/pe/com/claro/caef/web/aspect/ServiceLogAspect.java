package pe.com.claro.caef.web.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceLogAspect {

	@Around("pe.com.claro.caef.web.aspect.ServiceLogPointcut.servicePointcut()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable
	{
		 Object service = pjp.getTarget();
		 Logger log = Logger.getLogger(service.getClass());
		 
		 log.info("Invocando al servicio " + service.getClass().getName().substring(service.getClass().getName().lastIndexOf(".")+1, service.getClass().getName().length() - 11 ));
		 log.info("Entrando al metodo " + pjp.getSignature().getName());
		
		 Object output = pjp.proceed();
		 
		 log.info("Finalizado el metodo " + pjp.getSignature().getName());
		 return output;
	}
}
