package com.samples.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:test.queue")
                .to("log:com.samples.camel");

        from("timer:bar")
                .setBody(constant("Hello from Camel"))
                .to("activemq:test.queue");
    }
}
