package com.spring.activeMq.sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
//	public static void main(String[] args) throws JMSException {
//		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//		Connection connection = connectionFactory.createConnection();
//		connection.start();
//		
//		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		
//		Destination  destination = session.createQueue("MESSAGE_QUEUE");
//		
//		MessageProducer messageProducer = session.createProducer(destination);
//		
//		TextMessage textMessage = session.createTextMessage("first message");
//		
//		messageProducer.send(textMessage);
//		System.out.println(textMessage.getText()+" send successfully");
//		connection.close();
//	}
	
	public void sendMessage(String message) {
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});
		
		//return "Message send successfully";
	}
	
}
