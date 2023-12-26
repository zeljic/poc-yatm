package com.zeljic.poc.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
	name = "tasks",
	indexes = @Index(name = "task_name_index", columnList = "name"),
	uniqueConstraints = @UniqueConstraint(name = "task_name_unique", columnNames = "name")
)
public class Task extends EntityBase
{
	@Column(name = "name", nullable = false)
	public String name;

	@Column(name = "description", columnDefinition = "TEXT")
	public String description;

	@Check(constraints = "LENGTH(color) = 6 OR LENGTH(color) = 8")
	public String color;

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Generated(event = EventType.INSERT)
	public LocalDateTime createdAt;

	@Column(name = "updated_at")
	public LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	public LocalDateTime deletedAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", nullable = false, foreignKey = @ForeignKey(name = "task_status_id_fk"))
	public Status status;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "tasks")
	public List<User> users;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "tasks")
	public List<Silo> silos;
}
