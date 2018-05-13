package com.samples.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    public static void main(String[] args) throws JMSException {
        final ConnectionFactory factory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            connection = factory.createConnection();
            connection.start();
            session =
                    connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("test.queue");
            producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage("test message");
            message.setBooleanProperty("test", true);
            producer.send(message);
            System.out.println("Message sent");
        } finally {
            producer.close();
            session.close();
            connection.close();
        }
    }
}
