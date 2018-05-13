package com.samples.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TestMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                System.out.println(
                        "Reading message: " + ((TextMessage)message).getText());
            } else {
                System.out.println("Message is not a TextMessage");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
