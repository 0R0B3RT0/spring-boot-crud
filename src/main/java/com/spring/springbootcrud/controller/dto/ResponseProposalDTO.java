package com.spring.springbootcrud.controller.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResponseProposalDTO {

  UUID uuid;
}
