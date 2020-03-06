package com.spring.springbootcrud.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Proposal extends Model {

  @Id
  @Column(columnDefinition = "uuid")
  private UUID id;

  @NotEmpty(message = "must not be null")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;

  @DecimalMin(value = "1000.00")
  @DecimalMax(value = "4000.00")
  @NotEmpty(message = "must not be null")
  @Column(nullable = false)
  private BigDecimal amountOfLoan;

  @Pattern(regexp = "^(6|9|12)$", message = "must be 6, 9 or 12")
  private Integer termsInstallment;

  @DecimalMin(value = "0.0")
  @DecimalMax(value = "9999999999999.999999")
  @NotEmpty(message = "must not be null")
  private BigDecimal income;
}
