package com.spring.activeMq.config;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import com.spring.activeMq.receiver.MessageReceiver;

import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
public class MessagingConfig {
	
	private static final String URL = "tcp://localhost:61616";
	
	private static final String MESSAGE_QUEUE = "message_queue";
	
	@Autowired
	private MessageReceiver messageReceiver;

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(URL);
		return connectionFactory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestinationName(MESSAGE_QUEUE);
		return jmsTemplate;
	}
	
	@Bean
	public DefaultMessageListenerContainer container() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setDestinationName(MESSAGE_QUEUE);
		container.setMessageListener(messageReceiver);
		return container;
	}
	
	@Bean
	public MessageConverter converter() {
		return new SimpleMessageConverter();
	}
}
