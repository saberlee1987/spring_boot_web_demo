package com.saber.spring_boot_web_demo.repositories.impl;

import com.saber.spring_boot_web_demo.dto.persons.PersonDto;
import com.saber.spring_boot_web_demo.entities.persons.PersonEntity;
import com.saber.spring_boot_web_demo.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final EntityManager entityManager;

    @Override
    public PersonEntity save(PersonDto dto) {
        PersonEntity entity = createEntity(dto);
        entityManager.persist(entity);
        return entity;
    }

    private PersonEntity createEntity(PersonDto dto) {
        PersonEntity entity = new PersonEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setNationalCode(dto.getNationalCode());
        entity.setMobile(dto.getMobile());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
