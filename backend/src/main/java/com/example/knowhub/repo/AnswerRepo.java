package com.example.knowhub.repo;

import com.example.knowhub.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
    @Query(value="SELECT a.* FROM answers a JOIN questions q ON q.id=a.question_id AND q.is_deleted=false WHERE MATCH(a.body) AGAINST (?1 IN BOOLEAN MODE) AND a.is_deleted=false ORDER BY a.id DESC LIMIT ?2", nativeQuery=true)
    List<Answer> search(String q, int limit);
}
