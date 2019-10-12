package com.spring.springbootcrud.domain.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Person extends Model {

  @NotNull(message = "must not be null")
  @Column(nullable = false)
  private String name;

  @NotNull(message = "must not be null")
  @Column(nullable = false)
  private String cpf;

  @Column private LocalDate bornDate;

  @Column private String address;
}
