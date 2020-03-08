package com.spring.springbootcrud.domain.mapper;

import com.spring.springbootcrud.controller.dto.RequestProposalDTO;
import com.spring.springbootcrud.controller.dto.ResponseProposalDTO;
import com.spring.springbootcrud.domain.entity.Person;
import com.spring.springbootcrud.domain.entity.Proposal;
import org.springframework.stereotype.Component;

@Component
public class ProposalMapper {

  public Proposal toEntity(RequestProposalDTO requestProposalDTO, Person person) {
    return Proposal.builder()
        .person(person)
        .amountOfLoan(requestProposalDTO.getAmount())
        .income(requestProposalDTO.getIncome())
        .termsInstallment(requestProposalDTO.getTerms())
        .build();
  }

  public ResponseProposalDTO toResponseProposalDTO(Proposal proposal) {
    return ResponseProposalDTO.builder()
        .id(proposal.getId())
        .status(proposal.getStatus())
        .result(proposal.getResult())
        .refusedPolicy(proposal.getRefusedPolicy())
        .amount(proposal.getAmountOfLoan())
        .terms(proposal.getTermsInstallment())
        .build();
  }
}
