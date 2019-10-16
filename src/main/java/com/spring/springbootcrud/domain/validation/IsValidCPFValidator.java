package com.spring.springbootcrud.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.spring.springbootcrud.service.DocumentService;

public class IsValidCPFValidator implements ConstraintValidator<IsValidCPF, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return new DocumentService().isValid(s);
  }
}
