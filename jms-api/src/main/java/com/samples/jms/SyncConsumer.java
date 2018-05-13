package com.samples.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SyncConsumer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory;
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;

        try {
            factory =
                    new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = factory.createConnection();
            connection.start();
            session =
                    connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("test.queue");
            consumer = session.createConsumer(destination, "test = true");

            while (true) {
                Message message = consumer.receive(10_000);
                if (message != null)
                    System.out.println("Message received: " + ((TextMessage) message).getText());
                else
                    System.out.println("Nothing received");
            }

        } finally {
            consumer.close();
            session.close();
            connection.close();
        }
    }
}
