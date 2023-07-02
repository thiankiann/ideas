package com.mariusz.ideas.guestion.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



import java.util.UUID;
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    private UUID id;
    private String name;

    @ManyToOne
    private Question question;

    public Answer() {
        this.id = UUID.randomUUID();
    }

    public Answer(String name) {
        this();
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

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
