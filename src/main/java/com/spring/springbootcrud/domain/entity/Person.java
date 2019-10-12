package com.spring.springbootcrud.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Person extends Model {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String cpf;

	@Column
	private LocalDate bornDate;

	@Column
	private String address;

}
