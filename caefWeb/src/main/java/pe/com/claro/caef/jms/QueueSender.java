package pe.com.claro.caef.jms;



import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;


@Component
public class QueueSender {

	
	private final JmsTemplate jmsTemplate;
	
	@Autowired
    public QueueSender( final JmsTemplate jmsTemplate )
    {
        this.jmsTemplate = jmsTemplate;
    }
	
	public void send( final DetalleAuditoriaBean message ) throws JMSException
    {
        jmsTemplate.convertAndSend( "pe.com.claro.jms.distributedQueue.celFijaAuditoria", message );
    }
}
