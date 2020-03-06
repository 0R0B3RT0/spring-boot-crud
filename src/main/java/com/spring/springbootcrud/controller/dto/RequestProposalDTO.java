package com.spring.springbootcrud.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequestProposalDTO {

  String name;
  String cpf;
  LocalDate bornDate;
  BigDecimal amountOfLoan;
  Integer termsInstallment;
  BigDecimal income;
}
