package com.spring.springbootcrud.controller.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.springbootcrud.domain.enumeration.ProcessStatus;
import com.spring.springbootcrud.domain.enumeration.ProposalResult;
import com.spring.springbootcrud.domain.enumeration.RefusedPolicy;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(NON_NULL)
public class ResponseProposalDTO {
  UUID id;
  ProcessStatus status;
  ProposalResult result;
  RefusedPolicy refusedPolicy;
  BigDecimal amount;
  String terms;
}
