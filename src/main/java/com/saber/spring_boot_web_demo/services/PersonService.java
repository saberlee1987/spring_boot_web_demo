package com.saber.spring_boot_web_demo.services;

import com.saber.spring_boot_web_demo.dto.persons.PersonDto;
import com.saber.spring_boot_web_demo.dto.persons.PersonResponse;
import com.saber.spring_boot_web_demo.entities.persons.PersonEntity;

public interface PersonService {

    PersonResponse findAllPerson(String correlation);
    PersonDto findPersonByNationalCode(String correlation,String nationalCode);
    PersonEntity savePerson(String correlation, PersonDto dto);
}
