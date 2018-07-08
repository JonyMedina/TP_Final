package com.mensajeria;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.annotation.Resource;


import javax.jms.*;

@Stateless
@LocalBean
public class OfertaProductor implements OfertaProductorRemote, OfertaProductorLocal {

	public OfertaProductor() {

	}

	@Resource(lookup = "java:/myJmsTest/MyQueue")
	Destination destination;

	@Resource(lookup = "java:/myJmsTest/MyConnectionFactory1")
	ConnectionFactory connectionFactory;

	public void sendMessage(String messageText) {

		System.out.println(messageText);

		try {
			
			QueueConnection connection = (QueueConnection) connectionFactory.createConnection("integracion",
					"integracion");

			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage(messageText);
			producer.send(message);

			System.out.println("-------------------------------------------");
			System.out.println("\tMensaje enviado a la cola: ");
			System.out.println(message);
			System.out.println("-------------------------------------------");
			
			
			producer.close();
			session.close();
			connection.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
