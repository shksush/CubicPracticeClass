package com.cubic.jms;

import java.util.Properties;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.messaging.jmq.jmsserver.core.Session;

public class JMSQueueReceiver {

	public static void main(String[] args) throws Exception {
		
		final Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
		props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
		final InitialContext ctx = new InitialContext(props);
		
		final QueueConnectionFactory qcFactory = (QueueConnectionFactory) ctx.lookup("jms/qcf");
		QueueConnection qc = null;
		QueueSession session = null;
		Queue queue = null;
		
		try {
			qc = qcFactory.createQueueConnection();
			qc.start();
			System.out.println("Got the Connection successfully");
			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup("jms/MyQueue");
			QueueReceiver receiver = session.createReceiver(queue);
			System.out.println("Receiver 1 waiting for message");
			TextMessage jmsMessage = (TextMessage) receiver.receive();
			System.out.println("Message received Receiver 1");
			System.out.println(jmsMessage);
			
			qc.stop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if(qc !=null) {
				qc.close();
			}
		}
	}

}
