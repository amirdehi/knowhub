package com.example.knowhub.repo;

import com.example.knowhub.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExperienceRepo extends JpaRepository<Experience, Long> {
    @Query(value="SELECT * FROM experiences WHERE MATCH(title, body) AGAINST (?1 IN BOOLEAN MODE) AND is_deleted=false ORDER BY id DESC LIMIT ?2", nativeQuery=true)
    List<Experience> search(String q, int limit);
}
