package com.saber.spring_boot_web_demo.services.routes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.saber.spring_boot_web_demo.dto.ErrorResponseDto;
import com.saber.spring_boot_web_demo.dto.ServiceResponseErrorEnum;
import com.saber.spring_boot_web_demo.exceptions.ResourceDuplicationException;
import com.saber.spring_boot_web_demo.exceptions.ResourceNotFoundException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionResponseRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from(String.format("direct:%s", Routes.RESOURCE_NOTFOUND_EXCEPTION_ROUTE))
                .routeId(Routes.RESOURCE_NOTFOUND_EXCEPTION_ROUTE)
                .routeGroup(Routes.EXCEPTION_HANDLER_ROUTE_GROUP)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.NOT_ACCEPTABLE.value()))
                .process(exchange -> {
                    ResourceNotFoundException ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, ResourceNotFoundException.class);
                    ErrorResponseDto error = new ErrorResponseDto();
                    error.setCode(ServiceResponseErrorEnum.PERSON_SERVICE_PROVIDER_ERROR.getCode());
                    error.setText(ServiceResponseErrorEnum.PERSON_SERVICE_PROVIDER_ERROR.getText());
                    error.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}"
                            , HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage()));
                    log.error("Error for ResourceNotFoundException with body {}", error);
                    exchange.getIn().setBody(error);
                });

        from(String.format("direct:%s", Routes.RESOURCE_DUPLICATION_EXCEPTION_ROUTE))
                .routeId(Routes.RESOURCE_DUPLICATION_EXCEPTION_ROUTE)
                .routeGroup(Routes.EXCEPTION_HANDLER_ROUTE_GROUP)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.NOT_ACCEPTABLE.value()))
                .process(exchange -> {
                    ResourceDuplicationException ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, ResourceDuplicationException.class);
                    ErrorResponseDto error = new ErrorResponseDto();
                    error.setCode(ServiceResponseErrorEnum.PERSON_SERVICE_PROVIDER_ERROR.getCode());
                    error.setText(ServiceResponseErrorEnum.PERSON_SERVICE_PROVIDER_ERROR.getText());
                    error.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}"
                            , HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage()));
                    log.error("Error for ResourceDuplicationException with body {}", error);
                    exchange.getIn().setBody(error);
                });


        from(String.format("direct:%s", Routes.JSON_MAPPING_EXCEPTION_ROUTE))
                .routeId(Routes.JSON_MAPPING_EXCEPTION_ROUTE)
                .routeGroup(Routes.EXCEPTION_HANDLER_ROUTE_GROUP)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.NOT_ACCEPTABLE.value()))
                .process(exchange -> {
                    JsonMappingException ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, JsonMappingException.class);
                    ErrorResponseDto error = new ErrorResponseDto();
                    error.setCode(ServiceResponseErrorEnum.JSON_MAPPING_ERROR.getCode());
                    error.setText(ServiceResponseErrorEnum.JSON_MAPPING_ERROR.getText());
                    error.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}"
                            , HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage()));
                    log.error("Error for JsonMappingException with body {}", error);
                    exchange.getIn().setBody(error);
                });

        from(String.format("direct:%s", Routes.JSON_PARSER_EXCEPTION_ROUTE))
                .routeId(Routes.JSON_PARSER_EXCEPTION_ROUTE)
                .routeGroup(Routes.EXCEPTION_HANDLER_ROUTE_GROUP)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.NOT_ACCEPTABLE.value()))
                .process(exchange -> {
                    JsonParseException ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, JsonParseException.class);
                    ErrorResponseDto error = new ErrorResponseDto();
                    error.setCode(ServiceResponseErrorEnum.JSON_PARSER_ERROR.getCode());
                    error.setText(ServiceResponseErrorEnum.JSON_PARSER_ERROR.getText());
                    error.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}"
                            , HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage()));
                    log.error("Error for JsonParseException with body {}", error);
                    exchange.getIn().setBody(error);
                });


    }
}
