package com.spring.springbootcrud.domain.mapper;

import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

  public Person toEntity(PersonDTO dto) {
    return Person.builder()
        .id(dto.getId())
        .name(dto.getName())
        .cpf(dto.getCpf())
        .bornDate(dto.getBornDate())
        .address(dto.getAddress())
        .build();
  }

  public PersonDTO toDTO(Person entity) {
    return PersonDTO.builder()
        .id(entity.getId())
        .name(entity.getName())
        .cpf(entity.getCpf())
        .bornDate(entity.getBornDate())
        .address(entity.getAddress())
        .build();
  }
}
