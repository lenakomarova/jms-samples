package com.samples.spring;

import com.samples.spring.model.Email;
import com.samples.spring.producer.EmailMessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringApiJmsApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringApiJmsApplication.class);
        context.getBean(EmailMessageProducer.class).send(new Email("Lena", "Hello"));
    }
}
