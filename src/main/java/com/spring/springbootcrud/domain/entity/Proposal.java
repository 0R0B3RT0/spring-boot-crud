package com.spring.springbootcrud.domain.entity;

import com.spring.springbootcrud.domain.enumeration.ProcessStatus;
import com.spring.springbootcrud.domain.enumeration.ProposalResult;
import com.spring.springbootcrud.domain.enumeration.RefusedPolicy;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

  @NotNull(message = "must not be null")
  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

  @DecimalMin(value = "1000.00")
  @DecimalMax(value = "4000.00")
  @NotNull(message = "must not be null")
  @Column(name = "amount_of_loan", nullable = false)
  private BigDecimal amountOfLoan;

  @NotNull(message = "must not be null")
  @Pattern(regexp = "^(6|9|12)$", message = "must be 6, 9 or 12")
  @Column(name = "terms_installment", nullable = false)
  private String termsInstallment;

  @DecimalMin(value = "0.0")
  @DecimalMax(value = "9999999999999.999999")
  @NotNull(message = "must not be null")
  private BigDecimal income;

  @Enumerated(EnumType.STRING)
  @Column(name = "refused_policy")
  private RefusedPolicy refusedPolicy;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  private ProcessStatus status = ProcessStatus.PROCESSING;

  @Enumerated(EnumType.STRING)
  private ProposalResult result;
}
