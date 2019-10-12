package com.spring.springbootcrud.domain.entity;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.springframework.data.domain.Persistable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(of = "id")
public class Model implements Persistable<UUID> {

	@Id
	@Column(columnDefinition = "uuid")
	protected UUID id;

	@Column(name = "created_at", nullable = false, updatable = false)
	protected LocalDateTime createdAt;

	@Column(nullable = false)
	protected Boolean enabled;

	@Column(name = "updated_at", nullable = false)
	protected LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	protected LocalDateTime deletedAt;

	@PrePersist
	public void beforePersist() {
		id = randomUUID();
		createdAt = now();
		updatedAt = createdAt;
		enabled = true;
	}

	@PreUpdate
	public void beforeUpdate() {
		updatedAt = now();
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}
}
