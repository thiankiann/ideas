package com.mariusz.ideas.guestion.domain.model;




import com.mariusz.ideas.category.domain.model.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString
public class Question {

    @Id
    private UUID id;
    private String name;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    public Question() {
        this.id = UUID.randomUUID();
    }

    public Question addAnswer(Answer answer){
        if(answers == null){
            answers = new LinkedHashSet<>();
        }

        answer.setQuestion(this);
        answers.add(answer);

        return this;
    }

    public Question(String name) {
        this();
        this.name = name;
    }
}
