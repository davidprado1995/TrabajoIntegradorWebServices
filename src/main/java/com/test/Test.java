/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author USER
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    
        private static ActiveMQConnectionFactory connectionFactory;
	private static Connection connection;
	private static Session session;
	private static Destination destination;
	private static boolean transacted = false;
        
    public static void main(String[] args) {
        // TODO code application logic here
        try {
			connectionFactory = new ActiveMQConnectionFactory(
			ActiveMQConnection.DEFAULT_USER,
			ActiveMQConnection.DEFAULT_PASSWORD,
			ActiveMQConnection.DEFAULT_BROKER_URL);
			
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(transacted,Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("ColaPedidos");
			
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			
			//Enviamos un mensaje
                        TextMessage message = session.createTextMessage("Holi");			
			//Enviamos un mensaje
			producer.send(message);

		} 
		catch (JMSException e) {
			System.out.print(e);
		}
    }
    
}
