package com.spring.springbootcrud.domain.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProposalDTO {
  PersonDTO personDTO;
  BigDecimal amountOfLoan;
  Integer termsInstallment;
  BigDecimal income;
}
