package com.example.knowhub.web;

import com.example.knowhub.domain.Experience;
import com.example.knowhub.repo.ExperienceRepo;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {
    private final ExperienceRepo repo;
    public ExperienceController(ExperienceRepo repo){ this.repo = repo; }

    @GetMapping
    public List<Experience> list(){ return repo.findAll(); }
}
