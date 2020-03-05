package com.spring.springbootcrud.domain.validation;

import com.spring.springbootcrud.service.DocumentService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsValidCPFValidator implements ConstraintValidator<IsValidCPF, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return new DocumentService().isValid(s);
  }
}
