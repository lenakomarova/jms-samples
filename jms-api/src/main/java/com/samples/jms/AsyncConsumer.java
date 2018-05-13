package com.samples.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AsyncConsumer {
    public static void main(String[] args) throws JMSException, InterruptedException {
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
            consumer = session.createConsumer(destination);
            MessageListener listener = new TestMessageListener();
            consumer.setMessageListener(listener);
            Thread.sleep(60_000);

        } finally {
            consumer.close();
            session.close();
            connection.close();
        }
    }
}
