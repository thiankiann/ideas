package com.mariusz.ideas.guestion.domain.model;




public class Question {

    private String name;

    public Question() {
    }

    public Question(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                '}';
    }
}
