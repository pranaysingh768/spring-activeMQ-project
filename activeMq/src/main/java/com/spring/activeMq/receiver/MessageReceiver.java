package com.spring.activeMq.receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver implements MessageListener{
	
	private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	private static final String queue_Name = "MESSAGE_QUEUE";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private MessageConverter  messageConverter;
	
//	public static void main(String[] args) throws JMSException {
//		
//		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//		Connection connection = connectionFactory.createConnection();
//		connection.start();
//		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		
//		Destination destination = session.createQueue(queue_Name);
//		
//		MessageConsumer messageConsumer = session.createConsumer(destination);
//		
//		Message message = messageConsumer.receive();
//		
//		if(message instanceof TextMessage) {
//			TextMessage textMessage = (TextMessage)message;
//			String msg = textMessage.getText();
//			System.out.println("Received Message : "+msg);
//		}
//		
//		connection.close();
//	}
	
	public String receiveMessage() {
	
		try {
			Message message = jmsTemplate.receive();
			String msg = (String) messageConverter.fromMessage(message);
			return msg;
		}catch(Exception e) {
			
		}
		return "Failed to received";
		
	}

@Override
public void onMessage(Message message) {
	try {
		String msg = (String) messageConverter.fromMessage(message);
		LOGGER.info("Received message {} ",msg);

	}catch(Exception e) {
		LOGGER.error("Error in onMessage");;
	}
}

}
