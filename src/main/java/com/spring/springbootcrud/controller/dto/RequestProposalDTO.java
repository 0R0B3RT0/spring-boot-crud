package com.spring.springbootcrud.controller.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(NON_NULL)
public class RequestProposalDTO {
  String name;
  String cpf;
  LocalDate birthDate;
  BigDecimal amount;
  String terms;
  BigDecimal income;
}
