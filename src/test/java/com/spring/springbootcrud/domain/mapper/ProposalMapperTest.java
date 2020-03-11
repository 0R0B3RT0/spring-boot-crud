package com.spring.springbootcrud.domain.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.controller.dto.ResponseProposalDTO;
import com.spring.springbootcrud.domain.entity.Proposal;
import org.junit.Test;
import org.mockito.InjectMocks;

public class ProposalMapperTest extends BaseUnitTest {

  @InjectMocks private ProposalMapper proposalMapper;

  @Test
  public void mustProposalWhenHasRequestProposalDTOAndPerson() {
    Proposal actual = proposalMapper.toEntity(requestProposalDTO, person);

    assertThat(actual, equalTo(proposal));
  }

  @Test
  public void mustResponseProposalDTOWhenHasProposal() {
    ResponseProposalDTO actual = proposalMapper.toResponseProposalDTO(proposal);

    assertThat(actual, equalTo(responseProposalDTO));
  }
}
