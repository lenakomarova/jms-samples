package com.samples.spring.consumer;

import com.samples.spring.model.Email;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class EmailMessagesReceiver {

    @JmsListener(destination = "test.queue")
    public void receiveMessage(TextMessage email) {
        System.out.println("Received <" + email + ">");
    }
}
