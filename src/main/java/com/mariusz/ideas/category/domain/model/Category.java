package com.mariusz.ideas.category.domain.model;

import java.util.UUID;

public class Category {

	private UUID id;

	private String name;

	public Category() {
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
