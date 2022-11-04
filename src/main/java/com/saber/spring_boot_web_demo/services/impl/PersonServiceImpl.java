package com.saber.spring_boot_web_demo.services.impl;

import com.saber.spring_boot_web_demo.dto.persons.PersonDto;
import com.saber.spring_boot_web_demo.dto.persons.PersonResponse;
import com.saber.spring_boot_web_demo.entities.persons.PersonEntity;
import com.saber.spring_boot_web_demo.exceptions.ResourceDuplicationException;
import com.saber.spring_boot_web_demo.repositories.PersonRepository;
import com.saber.spring_boot_web_demo.services.PersonService;
import com.saber.spring_boot_web_demo.services.routes.Headers;
import com.saber.spring_boot_web_demo.services.routes.Routes;
import com.saber.spring_boot_web_demo.utils.CheckExceptionHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final ProducerTemplate producerTemplate;
    private final CheckExceptionHelper exceptionHelper;
    private final PersonRepository personRepository;
    @Override
    public PersonResponse findAllPerson(String correlation) {
        Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.FIND_ALL_PERSONS_ROUTE_GATEWAY), exchange -> {
            exchange.getIn().setHeader(Headers.correlation,correlation);
        });
        exceptionHelper.checkException(responseExchange);
        return responseExchange.getIn().getBody(PersonResponse.class);
    }

    @Override
    public PersonDto findPersonByNationalCode(String correlation, String nationalCode) {
        Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.FIND_PERSON_BY_NationalCode_ROUTE), exchange -> {
            exchange.getIn().setHeader(Headers.correlation,correlation);
            exchange.getIn().setHeader(Headers.nationalCode,nationalCode);
        });
        exceptionHelper.checkException(responseExchange);
        return responseExchange.getIn().getBody(PersonDto.class);
    }

    @Override
    @Transactional
    public PersonEntity savePerson(String correlation, PersonDto dto) {

        log.info("Request for correlation {} save Person with body ===> {}",correlation,dto);
        Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.FIND_PERSON_BY_NationalCode_ROUTE_GATEWAY), exchange -> {
            exchange.getIn().setHeader(Headers.correlation,correlation);
            exchange.getIn().setHeader(Headers.nationalCode,dto.getNationalCode());
        });
        exceptionHelper.checkException(responseExchange);
        if (responseExchange.getIn().getBody()!=null && responseExchange.getIn().getBody() instanceof PersonDto){
            throw new ResourceDuplicationException(String.format("person with nationalCode %s already exits",dto.getNationalCode()));
        }
        PersonEntity entity = personRepository.save(dto);
        log.info("Response for correlation {} save Person with body ===> {}",correlation,entity);
        return entity;
    }
}