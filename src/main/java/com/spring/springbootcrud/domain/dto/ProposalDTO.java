package com.spring.springbootcrud.domain.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(NON_NULL)
public class ProposalDTO {
  PersonDTO personDTO;
  BigDecimal amountOfLoan;
  Integer termsInstallment;
  BigDecimal income;
}
