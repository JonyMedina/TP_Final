package com.mensajeria;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.annotation.Resource;

import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;


@Stateless
@LocalBean
public class OfertaProductor implements OfertaProductorRemote, OfertaProductorLocal {


    public OfertaProductor() {

    }
    //java:/jms/queue/OP11Queue
	@Resource(lookup = "java:/jms/queue/OP11Queue")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;

	public void sendMessage(String messageText) {
		TextMessage message = context.createTextMessage("Hola, hay alguien ahí?");
		context.createProducer().send(testQueue, message);
	}

}
