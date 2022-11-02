package com.saber.spring_boot_web_demo.services.routes;

import com.saber.spring_boot_web_demo.dto.hi.HelloDto;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SayHelloRoute extends AbstractRestRouteBuilder {

    @Override
    public void configure() throws Exception {
        super.configure();

        
        from(String.format("direct:%s", Routes.SAY_HELLO_ROUTE))
                .routeId(Routes.SAY_HELLO_ROUTE)
                .routeGroup(Routes.SAY_HELLO_ROUTE_GROUP)
                .log("Request for correlation : ${in.header.correlation} say Hello with parameter firstName ${in.header.firstName} , lastName : ${in.header.lastName}")
               .process(exchange -> {
                String firstName = exchange.getIn().getHeader(Headers.firstName,String.class);
                String lastName = exchange.getIn().getHeader(Headers.lastName,String.class);
                   String message = String.format("Hello %s %s",firstName,lastName);
                   HelloDto helloDto = HelloDto.builder()
                           .message(message)
                           .build();
                   exchange.getIn().setBody(helloDto);
               })
                .to(String.format("direct:%s", Routes.SAY_HELLO_ROUTE_GATEWAY));

        from(String.format("direct:%s", Routes.SAY_HELLO_ROUTE_GATEWAY))
                .routeId(Routes.SAY_HELLO_ROUTE_GATEWAY)
                .routeGroup(Routes.SAY_HELLO_ROUTE_GROUP)
                .log("Response for correlation : ${in.header.correlation} say Hello ===> ${in.body}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
    }
}
