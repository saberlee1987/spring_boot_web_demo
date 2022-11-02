package com.saber.spring_boot_web_demo.services.impl;

import com.saber.spring_boot_web_demo.dto.ErrorResponseDto;
import com.saber.spring_boot_web_demo.dto.hi.HelloDto;
import com.saber.spring_boot_web_demo.dto.hi.HelloRequestDto;
import com.saber.spring_boot_web_demo.exceptions.GatewayException;
import com.saber.spring_boot_web_demo.services.HelloService;
import com.saber.spring_boot_web_demo.services.routes.Headers;
import com.saber.spring_boot_web_demo.services.routes.Routes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class HelloServiceImpl implements HelloService {

    private final ProducerTemplate producerTemplate;
    @Override
    public HelloDto sayHello(HelloRequestDto dto,String correlation) {
        log.info("Request for correlation : {} sayHello with body ===> {}",correlation,dto);
        String message = String.format("Hello %s %s",dto.getFirstName(),dto.getLastName());
        HelloDto helloDto = HelloDto.builder()
                .message(message)
                .build();
        log.info("Response for correlation : {} sayHello with body ===> {}",correlation,helloDto);
        return helloDto;
    }

    @Override
    public HelloDto sayHello(String firstName, String lastName,String correlation) {

        Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.SAY_HELLO_ROUTE), exchange -> {
            exchange.getIn().setHeader(Headers.correlation,correlation);
            exchange.getIn().setHeader(Headers.firstName,firstName);
            exchange.getIn().setHeader(Headers.lastName,lastName);
        });
        checkException(responseExchange);
        return responseExchange.getIn().getBody(HelloDto.class);
    }

    private void checkException(Exchange exchange){
        int statusCode = exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        if (statusCode != HttpStatus.OK.value() && exchange.getIn().getBody() instanceof ErrorResponseDto) {
            String correlation = exchange.getIn().getHeader(Headers.correlation,String.class);
            ErrorResponseDto errorResponse = exchange.getIn().getBody(ErrorResponseDto.class);
            throw new GatewayException(statusCode, correlation, errorResponse);
        }
    }
}
