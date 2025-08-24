package com.example.knowhub.repo;

import com.example.knowhub.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Long> {
    @Query(value="SELECT * FROM questions WHERE MATCH(title, body) AGAINST (?1 IN BOOLEAN MODE) AND is_deleted=false ORDER BY id DESC LIMIT ?2", nativeQuery=true)
    List<Question> search(String q, int limit);
}
