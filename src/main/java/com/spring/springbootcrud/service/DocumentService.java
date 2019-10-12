package com.spring.springbootcrud.service;

import org.springframework.stereotype.Service;

@Service
public class DocumentService {

  private static String REGEX_ONLY_NUMBER = "[^\\d]";

  public String cleanDocument(String document) {
    return document.replaceAll(REGEX_ONLY_NUMBER, "");
  }
}
