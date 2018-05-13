package com.samples.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

public class MessageBrowser {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory;
        Connection connection = null;
        Session session = null;
        QueueBrowser browser = null;

        try {
            factory =
                    new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = factory.createConnection();
            connection.start();
            session =
                    connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue destination = session.createQueue("test.queue");
            browser = session.createBrowser(destination);

            Enumeration msgs = browser.getEnumeration();

            if (!msgs.hasMoreElements()) {
                System.out.println("No messages in queue");
            } else {
                while (msgs.hasMoreElements()) {
                    Message tempMsg = (Message) msgs.nextElement();
                    System.out.println("Message: " + tempMsg);
                }
            }
        } finally {
            browser.close();
            session.close();
            connection.close();
        }
    }
}
