package com.spring.springbootcrud.service;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.controller.dto.ResponseProposalDTO;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Proposal;
import com.spring.springbootcrud.domain.mapper.ProposalMapper;
import com.spring.springbootcrud.domain.repository.ProposalRepository;
import java.util.Optional;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProposalServiceTest extends BaseUnitTest {

  private PersonDTO personDTO;

  @InjectMocks private ProposalService proposalService;
  @Mock private ProposalRepository proposalRepository;
  @Mock private ProposalMapper proposalMapper;
  @Mock private PersonService personService;
  @Mock private ValidationService<Proposal> validationService;
  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Override
  public void setup() {
    super.setup();
    personDTO = buildPersonDTO();

    doReturn(of(person)).when(personService).findByCpfAndEnabledTrue(VALID_CPF);
    doReturn(proposal).when(proposalRepository).save(proposal);
    doReturn(proposal).when(proposalMapper).toEntity(requestProposalDTO, person);
    doReturn(proposal).when(validationService).validateAndThrow(proposal);
    doReturn(responseProposalDTO).when(proposalMapper).toResponseProposalDTO(proposal);
    doReturn(person).when(personService).saveAndReturnEntity(personDTO);
    doReturn(of(proposal)).when(proposalRepository).findByIdAndEnabledTrue(ID);
  }

  @Test
  public void saveNewProposalWhenPersonExists() {
    ResponseProposalDTO actual = proposalService.save(requestProposalDTO);

    assertThat(actual, equalTo(responseProposalDTO));
    verify(personService, only()).findByCpfAndEnabledTrue(VALID_CPF);
    verify(proposalRepository, only()).save(proposal);
    verify(proposalMapper, times(1)).toEntity(requestProposalDTO, person);
    verify(validationService, only()).validateAndThrow(proposal);
    verify(proposalMapper, times(1)).toResponseProposalDTO(proposal);
    verify(personService, never()).saveAndReturnEntity(personDTO);
  }

  @Test
  public void saveNewProposalWhenPersonDoesNotExists() {
    doReturn(empty()).when(personService).findByCpfAndEnabledTrue(VALID_CPF);

    ResponseProposalDTO actual = proposalService.save(requestProposalDTO);

    assertThat(actual, equalTo(responseProposalDTO));
    verify(personService, times(1)).findByCpfAndEnabledTrue(VALID_CPF);
    verify(personService, times(1)).saveAndReturnEntity(personDTO);
    verify(proposalRepository, only()).save(proposal);
    verify(proposalMapper, times(1)).toEntity(requestProposalDTO, person);
    verify(validationService, only()).validateAndThrow(proposal);
    verify(proposalMapper, times(1)).toResponseProposalDTO(proposal);
  }

  @Test
  public void saveNewProposalWhenRequestProposalDTOIsNull() {
    expectedException.expect(RuntimeException.class);
    expectedException.expectMessage("Falha salvar a Proposta");

    proposalService.save(null);
  }

  @Test
  public void findProposal() {
    Optional<ResponseProposalDTO> actual = proposalService.findById(ID);

    assertThat(actual.get(), equalTo(responseProposalDTO));
    verify(proposalRepository, only()).findByIdAndEnabledTrue(ID);
    verify(proposalMapper, only()).toResponseProposalDTO(proposal);
  }

  @Test
  public void findProposalWhenProposalDoesNotExists() {
    doReturn(empty()).when(proposalRepository).findByIdAndEnabledTrue(ID);
    expectedException.expect(RuntimeException.class);
    expectedException.expectMessage("Falha ao consulta a Proposta");

    proposalService.findById(ID);
  }

  private PersonDTO buildPersonDTO() {
    return PersonDTO.builder().cpf(VALID_CPF).name(NAME).bornDate(bornDate).build();
  }
}
