package com.spring.springbootcrud.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonDTO implements Serializable {

	UUID id;
	@NotBlank
	String name;
	@NotEmpty
	String cpf;
	LocalDate bornDate;
	String address;
}
