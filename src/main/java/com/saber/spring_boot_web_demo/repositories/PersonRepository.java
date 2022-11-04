package com.saber.spring_boot_web_demo.repositories;

import com.saber.spring_boot_web_demo.dto.persons.PersonDto;
import com.saber.spring_boot_web_demo.entities.persons.PersonEntity;

public interface PersonRepository {

    PersonEntity save(PersonDto dto);
}
