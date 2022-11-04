package com.saber.spring_boot_web_demo.services.impl;

import com.saber.spring_boot_web_demo.dto.hi.HelloDto;
import com.saber.spring_boot_web_demo.dto.hi.HelloRequestDto;
import com.saber.spring_boot_web_demo.services.HelloService;
import com.saber.spring_boot_web_demo.services.routes.Headers;
import com.saber.spring_boot_web_demo.services.routes.Routes;
import com.saber.spring_boot_web_demo.utils.CheckExceptionHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class HelloServiceImpl implements HelloService {

    private final ProducerTemplate producerTemplate;
    private final CheckExceptionHelper exceptionHelper;
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
        exceptionHelper.checkException(responseExchange);
        return responseExchange.getIn().getBody(HelloDto.class);
    }


}
