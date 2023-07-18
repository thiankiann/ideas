package com.mariusz.ideas.guestion.domain.repository;

import com.mariusz.ideas.guestion.domain.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findByCategoryId(UUID id);
}
