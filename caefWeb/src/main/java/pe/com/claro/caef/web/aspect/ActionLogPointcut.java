package pe.com.claro.caef.web.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ActionLogPointcut {

	@Pointcut("execution (* pe.com.claro.caef.web.action.*.execute())")
		public void actionPointcut(){
		
	}
}
