package com.spring.springbootcrud.service;

import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springbootcrud.controller.dto.RequestProposalDTO;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Proposal;
import com.spring.springbootcrud.domain.repository.PersonRepository;
import com.spring.springbootcrud.domain.repository.ProposalRepository;

@Slf4j
@Service
public class ProposalService {

  @Autowired private ProposalRepository proposalRepository;
  @Autowired private PersonRepository personRepository;
  @Autowired private PersonService personService;

  public UUID save(RequestProposalDTO requestProposalDTO) {
    PersonDTO personDTO =
        personService
            .findByCpf(requestProposalDTO.getCpf())
            .orElseGet(() -> personService.save(buildNewPerson(requestProposalDTO)));

    Proposal proposal =
        Proposal.builder()
            .person(personRepository.findById(personDTO.getId()).get())
            .amountOfLoan(requestProposalDTO.getAmountOfLoan())
            .income(requestProposalDTO.getIncome())
            .termsInstallment(requestProposalDTO.getTermsInstallment())
            .build();

    return proposalRepository.save(proposal).getId();
  }

  private PersonDTO buildNewPerson(RequestProposalDTO requestProposalDTO) {
    return PersonDTO.builder()
        .cpf(requestProposalDTO.getCpf())
        .name(requestProposalDTO.getName())
        .bornDate(requestProposalDTO.getBornDate())
        .build();
  }

  public Optional<UUID> findById(UUID id) {
    return Optional.empty();
  }

  public Optional<UUID> cancelById(UUID id) {
    return Optional.empty();
  }
}
