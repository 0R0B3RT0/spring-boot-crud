package com.spring.springbootcrud.domain.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(NON_NULL)
public class PersonDTO implements Serializable {

  UUID id;
  String name;
  String cpf;
  LocalDate bornDate;
  String address;
}
