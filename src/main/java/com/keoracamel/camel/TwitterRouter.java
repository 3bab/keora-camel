package com.keoracamel.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spi.RestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TwitterRouter extends RouteBuilder {


    @PostConstruct
    public void initCamelContext() throws Exception {
        // shutdown the producer first that reads from the twitter sample feed:
       // getContext().getShutdownStrategy().setShutdownRoutesInReverseOrder(false);
        // wait max 5 seconds for camel to stop:
       // getContext().getShutdownStrategy().setTimeout(5L);
    }

    @Override
    public void configure() throws Exception {
        //from("twitter://streaming/sample?type=EVENT&consumerKey={{twitter4j.oauth.consumerKey}}&consumerSecret={{twitter4j.oauth.consumerSecret}}&accessToken={{twitter4j.oauth.accessToken}}&accessTokenSecret={{twitter4j.oauth.accessTokenSecret}}")
        //        .log("something");
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest()
                .get("/test")
                .to("direct:logapi");

        from("direct:logapi")
                .log("apiapiapi")
                .bean(this, "createResponse");
    }

    public ResponseObject createResponse() {
        ResponseObject response = new ResponseObject();
        response.setResponse("Hello World");
        response.setName("your name");
        return response;
    }
}
