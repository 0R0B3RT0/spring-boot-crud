Feature: Save valid Person

  Scenario: Request save person
    Given the persons with these fields
      | name            | cpf           | bornDate   | address           |
      | Roberto Candido | 13.456.789.10 | 1989-11-01 | Rua da minha casa |
    When the register of this product is requested
    Then the result should be a persisted person