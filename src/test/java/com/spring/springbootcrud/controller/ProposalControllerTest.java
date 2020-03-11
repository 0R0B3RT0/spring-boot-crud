package com.spring.springbootcrud.controller;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.controller.dto.ResponseProposalDTO;
import com.spring.springbootcrud.service.ProposalService;
import java.lang.reflect.Method;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class ProposalControllerTest extends BaseUnitTest {

  @InjectMocks private ProposalController proposalController;
  @Mock private ProposalService proposalService;

  @Override
  public void setup() {
    super.setup();
    doReturn(responseProposalDTO).when(proposalService).save(requestProposalDTO);
    doReturn(of(responseProposalDTO)).when(proposalService).findById(PROPOSAL_ID);
  }

  @Test
  public void mustHttpStatusOKWhenSaveRequestProposalDTOValid() {
    final ResponseEntity<ResponseProposalDTO> actual =
        proposalController.saveNew(requestProposalDTO);

    verify(proposalService, only()).save(requestProposalDTO);
    assertThat(actual.getStatusCode(), equalTo(OK));
    assertThat(actual.getBody(), equalTo(responseProposalDTO));
  }

  @Test
  public void saveNewMustBeAnnotatedWithHystrixCommand() {
    final Method method = getMethod(ProposalController.class, "saveNew");

    final HystrixCommand annotation = getAnnotation(method, HystrixCommand.class);

    assertThat(annotation, notNullValue());
    assertThat(annotation.commandKey(), equalTo("save-new"));
  }

  @Test
  public void mustHttpStatusOKWhenFindProposalById() {
    final ResponseEntity<ResponseProposalDTO> actual = proposalController.findById(PROPOSAL_ID);

    verify(proposalService, only()).findById(PROPOSAL_ID);
    assertThat(actual.getStatusCode(), equalTo(OK));
    assertThat(actual.getBody(), equalTo(responseProposalDTO));
  }

  @Test
  public void mustHttpStatusNotFoundWhenFindProposalByIdAndNotFound() {
    doReturn(empty()).when(proposalService).findById(PROPOSAL_ID);

    final ResponseEntity<ResponseProposalDTO> actual = proposalController.findById(PROPOSAL_ID);

    verify(proposalService, only()).findById(PROPOSAL_ID);
    assertThat(actual.getStatusCode(), equalTo(NOT_FOUND));
    assertThat(actual.getBody(), is(nullValue()));
  }

  @Test
  public void findByIdMustBeAnnotatedWithHystrixCommand() {
    final Method method = getMethod(PersonController.class, "findById");

    final HystrixCommand annotation = getAnnotation(method, HystrixCommand.class);

    assertThat(annotation, notNullValue());
    assertThat(annotation.commandKey(), equalTo("find-by-id"));
  }
}
