package com.spring.springbootcrud.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.springbootcrud.controller.dto.RequestProposalDTO;
import com.spring.springbootcrud.controller.dto.ResponseProposalDTO;
import com.spring.springbootcrud.service.ProposalService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@DefaultProperties(
    groupKey = "loan",
    commandProperties =
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "800"))
@RestController
@RequestMapping(value = "loan", produces = APPLICATION_JSON_VALUE)
public class ProposalController {

  @Autowired private ProposalService proposalService;

  @PostMapping
  @HystrixCommand(commandKey = "save-new")
  public ResponseEntity<ResponseProposalDTO> saveNew(
      @RequestBody RequestProposalDTO requestProposalDTO) {
    return ResponseEntity.ok(proposalService.save(requestProposalDTO));
  }

  @GetMapping("{id}")
  @HystrixCommand(commandKey = "find-by-id")
  public ResponseEntity<ResponseProposalDTO> findById(@PathVariable("id") UUID id) {
    return proposalService
        .findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}
