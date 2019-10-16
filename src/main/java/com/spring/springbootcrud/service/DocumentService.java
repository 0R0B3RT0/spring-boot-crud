package com.spring.springbootcrud.service;

import static java.lang.Character.getNumericValue;
import static java.lang.String.valueOf;
import static java.util.Optional.ofNullable;

import org.springframework.stereotype.Service;

@Service
public class DocumentService {

  private static String REGEX_ONLY_NUMBER = "[^\\d]";
  private static final int FIRST_SEQUENCE_TO_CALCULATE = 9;
  private static final int SECOND_SEQUENCE_TO_CALCULATE = 10;
  private static final int FIRST_MULTIPLY_VALUE = 10;
  private static final int SECOND_MULTIPLY_VALUE = 11;
  private static final int MULTIPLIER = 10;
  private static final int DIVIDER = 11;

  public String cleanDocument(String document) {
    return ofNullable(document).map(s -> s.replaceAll(REGEX_ONLY_NUMBER, "")).orElse(document);
  }

  public boolean isValid(String cpf) {
    final String safeCpf = cleanDocument(cpf);

    return getFirstVerificationDigit(safeCpf).equals(getFirstValidationDigit(safeCpf))
        && getSecondVerificationDigit(safeCpf).equals(getSecondValidationDigit(safeCpf));
  }

  private String getFirstVerificationDigit(String cpf) {
    return cpf.substring(9, 10);
  }

  private String getSecondVerificationDigit(String cpf) {
    return cpf.substring(10, 11);
  }

  private String getFirstValidationDigit(String safeCpf) {
    int count = FIRST_MULTIPLY_VALUE;
    long cpfInitialDigitsResult = 0;
    cpfInitialDigitsResult =
        calculateDecresedSequence(
            safeCpf, count, cpfInitialDigitsResult, FIRST_SEQUENCE_TO_CALCULATE);
    return calculateVerificationDigit(cpfInitialDigitsResult);
  }

  private String getSecondValidationDigit(String safeCpf) {
    int count = SECOND_MULTIPLY_VALUE;
    long cpfInitialDigitsResult = 0;
    cpfInitialDigitsResult =
        calculateDecresedSequence(
            safeCpf, count, cpfInitialDigitsResult, SECOND_SEQUENCE_TO_CALCULATE);
    return calculateVerificationDigit(cpfInitialDigitsResult);
  }

  private long calculateDecresedSequence(
      String safeCpf, int count, long cpfInitialDigitsResult, int i) {
    for (char digit : safeCpf.substring(0, i).toCharArray()) {
      cpfInitialDigitsResult += getNumericValue(digit) * count--;
    }
    return cpfInitialDigitsResult;
  }

  private String calculateVerificationDigit(long cpfInitialDigitsResult) {
    //    final long verificationDigit = cpfInitialDigitsResult * MULTIPLIER % DIVIDER;
    final long verificationDigit = DIVIDER - (cpfInitialDigitsResult % DIVIDER);
    return verificationDigit > 9 ? "0" : valueOf(verificationDigit);
  }
}
