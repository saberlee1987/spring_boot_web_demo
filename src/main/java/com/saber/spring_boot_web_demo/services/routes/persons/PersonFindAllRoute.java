package com.saber.spring_boot_web_demo.services.routes.persons;

import com.saber.spring_boot_web_demo.dto.persons.PersonDto;
import com.saber.spring_boot_web_demo.dto.persons.PersonResponse;
import com.saber.spring_boot_web_demo.services.routes.AbstractRestRouteBuilder;
import com.saber.spring_boot_web_demo.services.routes.Routes;
import org.apache.camel.Exchange;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class PersonFindAllRoute extends AbstractRestRouteBuilder {
    
    @Override
    public void configure() throws Exception {
        super.configure();

        from(String.format("direct:%s", Routes.FIND_ALL_PERSONS_ROUTE_GATEWAY))
                .routeId(Routes.FIND_ALL_PERSONS_ROUTE_GATEWAY)
                .routeGroup(Routes.FIND_ALL_PERSONS_ROUTE_GROUP)
                .log("Request for correlation : ${in.header.correlation} find all person")
                .to("sql:select * from persons?outputClass="+ PersonDto.class.getName())
                .to(String.format("direct:%s", Routes.FIND_ALL_PERSONS_ROUTE_GATEWAY_OUT));

        from(String.format("direct:%s", Routes.FIND_ALL_PERSONS_ROUTE_GATEWAY_OUT))
                .routeId(Routes.FIND_ALL_PERSONS_ROUTE_GATEWAY_OUT)
                .routeGroup(Routes.FIND_ALL_PERSONS_ROUTE_GROUP)
                .marshal().json(JsonLibrary.Jackson)
                .process(exchange -> {
                    String jsonString = exchange.getIn().getBody(String.class);
                    jsonString = String.format("{\"persons\":%s}",jsonString);
                    exchange.getIn().setBody(jsonString);
                })
                .unmarshal().json(JsonLibrary.Jackson, PersonResponse.class)
                .log("Response for correlation : ${in.header.correlation} say Hello ===> ${in.body}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
    }

}
