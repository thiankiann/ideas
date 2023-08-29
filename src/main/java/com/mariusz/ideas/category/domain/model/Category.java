package com.mariusz.ideas.category.domain.model;



import com.mariusz.ideas.guestion.domain.model.Question;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories" )
@Getter
@Setter
public class Category {

	@Id
	private UUID id;

	@NotBlank(message = "You need a name of category to save it ")
	@Size(min = 3, max = 255)
	private String name;

	@OneToMany(mappedBy = "category")
	private List<Question> questions;

	public Category() {
		this.id = UUID.randomUUID();
	}

	public Category(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}
}