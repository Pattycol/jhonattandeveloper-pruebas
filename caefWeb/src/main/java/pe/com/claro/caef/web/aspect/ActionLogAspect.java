package pe.com.claro.caef.web.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.web.action.ConsultaRecargaAction;

@Component
@Aspect
public class ActionLogAspect {

	
	@Around("pe.com.claro.caef.web.aspect.ActionLogPointcut.actionPointcut()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable
	{
		 
		
		 Object action = pjp.getTarget();
		 Object filter = null;
		 
		 Logger log = Logger.getLogger(action.getClass());
		 //LA CABECERA DEL ACTION
		 log.info("Se inicia el proceso " + action.getClass().getName().substring(action.getClass().getName().lastIndexOf(".")+1, action.getClass().getName().length() - 6 ));
		 log.info("Ingresando al metodo execute ");
		 Class myClass = pjp.getStaticPart().getSignature().getDeclaringType();
		 
		 for(Method method : myClass.getDeclaredMethods())
		    {
		       
		        if(method.toString().indexOf("Filter") >0  && method.toString().indexOf("get") > 0)
		        {
		        	filter = method.invoke(action, null);
		        	break;
		        }
		    }
		 
		 StringBuffer sf = new StringBuffer();
		 
		 if(filter != null)
		 {
			 sf.append("\n[\n ");
			 
			 //LISTA DE PARAMETROS - ACCEDEMOS A ELLOS POR MEDIO DE SUS METODOS GET
			 for(Method method : filter.getClass().getDeclaredMethods())
			    {
			        if(method.toString().indexOf("get") >0  )
			        { 
			        	sf.append(method.getName().substring(3, method.getName().length())).append(" : ").append( method.invoke(filter, null) ).append(",\n");
			        	
			        }
			    }
			
			
			 sf.append("]\n");
		 }
		
		
		
		 log.info("Parametros: " + sf.toString());
		 
		 //SE EJECUTA FINALMENTE EL METODO EXECUTE
         Object output = pjp.proceed();
         
         log.info("Se culmino el proceso " + action.getClass().getName().substring(action.getClass().getName().lastIndexOf(".")+1, action.getClass().getName().length() - 6 ));
 		
         return output;
	}
	
}
