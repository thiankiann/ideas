package com.mariusz.ideas.guestion.domain.repository;

import com.mariusz.ideas.common.dto.StatisticsDto;
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

    List<Question> findAllByCategoryId(UUID id, Pageable pageable);

    @Query("from Question q order by q.answers.size desc")
    Page<Question> findHot(Pageable pageable);

    @Query("from Question q where q.answers.size = 0")
    Page<Question> findUnanswered(Pageable pageable);

    @Query(
            value = "select * from questions q where upper(q.name) like upper('%' || :query || '%') ",
            countQuery = "select count(*) from questions q where upper(q.name) like upper('%' || :query || '%') ",
            nativeQuery = true
    )
    Page<Question> findByQuery(String query, Pageable pageable);

    @Query(value = "select * from questions q order by random() limit :limit", nativeQuery = true)
    List<Question> findRandomQuestions(int limit);

    @Query(value = "select new com.mariusz.ideas.common.dto.StatisticsDto(count(q), count(a)) from Question q join q.answers a")
    StatisticsDto statistics();
}



