package com.mariusz.ideas.guestion.domain.repository;

import com.mariusz.ideas.guestion.domain.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findByCategoryId(UUID id);

    @Query("from Question q order by q.answers.size desc")
    Page<Question> findHot(Pageable pageable);

    @Query("from Question q where q.answers.size = 0")
    Page<Question> findUnanswered(Pageable pageable);

    @Query(value = "select * from questions g where upper(g.name) like upper('%' || :query || '%')" ,
           countQuery = "select count(*) from q where upper(q.name) like upper('%' || :query || '%')",
            nativeQuery = true)
    Page<Question> findByQuery(String query, Pageable pageable);
}