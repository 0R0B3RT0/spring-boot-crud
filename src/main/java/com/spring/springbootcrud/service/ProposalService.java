package com.spring.springbootcrud.service;

import static java.util.Optional.ofNullable;

import com.spring.springbootcrud.controller.dto.RequestProposalDTO;
import com.spring.springbootcrud.controller.dto.ResponseProposalDTO;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import com.spring.springbootcrud.domain.entity.Proposal;
import com.spring.springbootcrud.domain.mapper.ProposalMapper;
import com.spring.springbootcrud.domain.repository.ProposalRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.function.UnaryOperator;
import javax.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProposalService {

  @Autowired private ProposalRepository proposalRepository;
  @Autowired private ProposalMapper proposalMapper;
  @Autowired private PersonService personService;
  @Autowired private ValidationService<Proposal> validationService;

  public ResponseProposalDTO save(RequestProposalDTO requestProposalDTO) {
    return ofNullable(requestProposalDTO)
        .map(this::findOrCreateNewPerson)
        .map(person -> proposalMapper.toEntity(requestProposalDTO, person))
        .map(validationService::validateAndThrow)
        .map(persist().andThen(logPersistSuccess()))
        .map(proposalMapper::toResponseProposalDTO)
        .orElseThrow(() -> new RuntimeException("Falha salvar a Proposta"));
  }

  public Optional<ResponseProposalDTO> findById(UUID id) {
    return ofNullable(id)
        .flatMap(proposalRepository::findByIdAndEnabledTrue)
        .map(proposalMapper::toResponseProposalDTO)
        .map(Optional::of)
        .orElseThrow(() -> new RuntimeException("Falha ao consulta a Proposta"));
  }

  private UnaryOperator<Proposal> persist() {
    return proposal -> {
      try {
        return proposalRepository.save(proposal);
      } catch (Exception ex) {
        log.error("Falha ao persistir a proposta", ex);
        throw new PersistenceException("Falha ao persistir a proposta", ex);
      }
    };
  }

  private UnaryOperator<Proposal> logPersistSuccess() {
    return proposal -> {
      log.info(
          "Proposta persistida com sucesso, id:{}, cliente:{}",
          proposal.getId(),
          proposal.getPerson().getName());
      return proposal;
    };
  }

  private Person findOrCreateNewPerson(RequestProposalDTO requestProposalDTO) {
    return personService
        .findByCpfAndEnabledTrue(requestProposalDTO.getCpf())
        .orElseGet(() -> personService.saveAndReturnEntity(buildNewPerson(requestProposalDTO)));
  }

  private PersonDTO buildNewPerson(RequestProposalDTO requestProposalDTO) {
    return PersonDTO.builder()
        .cpf(requestProposalDTO.getCpf())
        .name(requestProposalDTO.getName())
        .bornDate(requestProposalDTO.getBirthDate())
        .build();
  }
}
