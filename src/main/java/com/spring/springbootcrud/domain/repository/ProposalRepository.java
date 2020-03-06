package com.spring.springbootcrud.domain.repository;

import com.spring.springbootcrud.domain.entity.Person;
import com.spring.springbootcrud.domain.entity.Proposal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, UUID> {

  List<Person> findAllByEnabledTrue();

  Optional<Person> findByIdAndEnabledTrue(UUID id);
}
