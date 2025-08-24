package com.example.knowhub.web;

import com.example.knowhub.domain.Question;
import com.example.knowhub.repo.QuestionRepo;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionRepo repo;
    public QuestionController(QuestionRepo repo){ this.repo = repo; }

    @GetMapping
    public List<Question> list(){ return repo.findAll(); }
}
