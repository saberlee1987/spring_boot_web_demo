package com.saber.spring_boot_web_demo.services.routes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.saber.spring_boot_web_demo.exceptions.ResourceDuplicationException;
import com.saber.spring_boot_web_demo.exceptions.ResourceNotFoundException;
import org.apache.camel.builder.RouteBuilder;

public class AbstractRestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(ResourceNotFoundException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for ResourceNotFoundException with error ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.RESOURCE_NOTFOUND_EXCEPTION_ROUTE));

        onException(ResourceDuplicationException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for ResourceDuplicationException with error ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.RESOURCE_DUPLICATION_EXCEPTION_ROUTE));



        onException(JsonMappingException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for JsonMappingException with error ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.JSON_MAPPING_EXCEPTION_ROUTE));

        onException(JsonParseException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for JsonParseException with error ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.JSON_PARSER_EXCEPTION_ROUTE));


        from(String.format("direct:%s", Routes.RESOURCE_NOTFOUND_RESPONSE_ROUTE))
                .routeId(Routes.RESOURCE_NOTFOUND_RESPONSE_ROUTE)
                .routeGroup(Routes.RESOURCE_NOTFOUND_RESPONSE_ROUTE_GROUP)
                .process(exchange -> {
                    String nationalCode = exchange.getIn().getHeader(Headers.nationalCode, String.class);
                    String message = String.format("person with nationalCode %s does not exist", nationalCode);
                    throw new ResourceNotFoundException(message);
                });

        from(String.format("direct:%s", Routes.RESOURCE_DUPLICATION_RESPONSE_ROUTE))
                .routeId(Routes.RESOURCE_DUPLICATION_RESPONSE_ROUTE)
                .routeGroup(Routes.RESOURCE_DUPLICATION_RESPONSE_ROUTE_GROUP)
                .process(exchange -> {
                    String nationalCode = exchange.getIn().getHeader(Headers.nationalCode, String.class);
                    String message = String.format("person with nationalCode %s already exist", nationalCode);
                    throw new ResourceDuplicationException(message);
                });

    }
}