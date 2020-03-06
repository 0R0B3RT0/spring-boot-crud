package com.spring.springbootcrud.service;

import static java.util.Optional.ofNullable;

import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springbootcrud.controller.dto.RequestProposalDTO;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
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
    return ofNullable(requestProposalDTO)
        .map(this::findOrCreateNewPerson)
        .map(person -> buildNewProposal(requestProposalDTO, person))
        .map(proposalRepository::save)
        .map(Proposal::getId)
        .orElseThrow(() -> new RuntimeException("Falha salvar a Proposta"));
  }

  public Optional<UUID> findById(UUID id) {
    return ofNullable(id)
        .flatMap(proposalRepository::findByIdAndEnabledTrue)
        .map(Proposal::getId)
        .map(Optional::of)
        .orElseThrow(() -> new RuntimeException("Falha ao consulta a Proposta"));
  }

  public Optional<UUID> cancelById(UUID id) {
    return Optional.empty();
  }

  private Person findOrCreateNewPerson(RequestProposalDTO requestProposalDTO) {
    return personRepository
        .findByCpfAndEnabledTrue(requestProposalDTO.getCpf())
        .orElseGet(() -> personService.saveAndReturnEntity(buildNewPerson(requestProposalDTO)));
  }

  private Proposal buildNewProposal(RequestProposalDTO requestProposalDTO, Person person) {
    return Proposal.builder()
        .person(person)
        .amountOfLoan(requestProposalDTO.getAmountOfLoan())
        .income(requestProposalDTO.getIncome())
        .termsInstallment(requestProposalDTO.getTermsInstallment())
        .build();
  }

  private PersonDTO buildNewPerson(RequestProposalDTO requestProposalDTO) {
    return PersonDTO.builder()
        .cpf(requestProposalDTO.getCpf())
        .name(requestProposalDTO.getName())
        .bornDate(requestProposalDTO.getBornDate())
        .build();
  }
}
