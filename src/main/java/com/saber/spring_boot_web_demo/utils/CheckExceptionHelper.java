package com.saber.spring_boot_web_demo.utils;

import com.saber.spring_boot_web_demo.dto.ErrorResponseDto;
import com.saber.spring_boot_web_demo.exceptions.GatewayException;
import com.saber.spring_boot_web_demo.services.routes.Headers;
import org.apache.camel.Exchange;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CheckExceptionHelper {
    public void checkException(Exchange exchange){
        int statusCode = exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        if (statusCode != HttpStatus.OK.value() && exchange.getIn().getBody() instanceof ErrorResponseDto) {
            String correlation = exchange.getIn().getHeader(Headers.correlation,String.class);
            ErrorResponseDto errorResponse = exchange.getIn().getBody(ErrorResponseDto.class);
            throw new GatewayException(statusCode, correlation, errorResponse);
        }
    }
}
