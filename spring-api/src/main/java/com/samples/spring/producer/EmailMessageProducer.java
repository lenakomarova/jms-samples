package com.samples.spring.producer;

import com.samples.spring.model.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailMessageProducer {

    private final JmsTemplate jmsTemplate;

    public void send(Email email) {
        jmsTemplate.convertAndSend("test.queue", email);
        System.out.println("Message sent: " + email);
    }
}
