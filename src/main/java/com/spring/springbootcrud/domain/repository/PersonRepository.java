package com.spring.springbootcrud.domain.repository;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository
    extends CrudRepository<Person, UUID>, JpaSpecificationExecutor<Person> {

  List<Person> findAllByEnabledTrue();

  static Specification<Person> hasCpf(String cpf) {
    return (book, cq, cb) -> cb.equal(book.get("cpf"), cpf);
  }

  static Specification<Person> nameContains(String name) {
    return (book, cq, cb) -> cb.equal(book.get("name"), "%" + name + "%");
  }

  static Specification<Person> addresContains(String address) {
    return (book, cq, cb) -> cb.equal(book.get("address"), "%" + address + "%");
  }

  static Optional<Specification<Person>> getSpecification(PersonDTO filter) {
    if (filter == null) return empty();
    Specification<Person> specification = null;
    List<Specification<Person>> specifications = newArrayList();

    if (filter.getCpf() != null) specifications.add(hasCpf(filter.getCpf()));
    if (filter.getName() != null) specifications.add(nameContains(filter.getName()));
    if (filter.getAddress() != null) specifications.add(addresContains(filter.getAddress()));

    for (Specification<Person> personSpecification : specifications) {
      if (specification == null) specification = personSpecification;
      else specification.and(personSpecification);
    }
    return ofNullable(specification);
  }
}
