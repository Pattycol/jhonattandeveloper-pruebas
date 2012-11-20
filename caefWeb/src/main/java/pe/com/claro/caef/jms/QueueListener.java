package pe.com.claro.caef.jms;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jfree.util.Log;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;

@Component
public class QueueListener implements MessageListener
{
    private List<DetalleAuditoriaBean> _messages = new ArrayList<DetalleAuditoriaBean>();
     
    public List<DetalleAuditoriaBean> getMessages(){
        // return them then clear recorded cache
        List<DetalleAuditoriaBean> temp = new ArrayList<DetalleAuditoriaBean>(_messages);
        _messages.clear();
         
        return temp;
    }
     
    public void onMessage( final Message message )
    {
        if ( message instanceof ObjectMessage )
        {
        	final ObjectMessage oMessage = (ObjectMessage) message;
            try
            {
            	DetalleAuditoriaBean tehMessage = (DetalleAuditoriaBean) oMessage.getObject();
            	
                _messages.add( tehMessage);
             Log.info( 
                        "Pulled from queue: " + tehMessage.toString() + 
                        ".  (Recorded " + _messages.size() + " messages.)" );
            }
            catch (final JMSException e)
            {
                Log.info(e.toString());
            }
        }
    }    
    
}
