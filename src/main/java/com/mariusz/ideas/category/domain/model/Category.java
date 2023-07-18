package com.mariusz.ideas.category.domain.model;



import com.mariusz.ideas.guestion.domain.model.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import java.util.UUID;

@Entity
@Table(name = "categories" )
public class Category {

	@Id
	private UUID id;

	private String name;

	@ManyToOne
	private Question question;

	public Category() {
		this.id = UUID.randomUUID();
	}

	public Category(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
