package com.mariusz.ideas.guestion.domain.model;




import com.mariusz.ideas.category.domain.model.Category;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    private UUID id;
    private String name;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "questions")
    private Set<Answer> answers;

    public Question() {
        this.id = UUID.randomUUID();
    }

    public Question(String name) {
        this();
        this.name = name;
    }

    public Question addAnswer(Answer answer){
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
