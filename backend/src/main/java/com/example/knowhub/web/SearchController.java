package com.example.knowhub.web;

import com.example.knowhub.repo.AnswerRepo;
import com.example.knowhub.repo.ExperienceRepo;
import com.example.knowhub.repo.QuestionRepo;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final ExperienceRepo expRepo; private final QuestionRepo qRepo; private final AnswerRepo aRepo;
    public SearchController(ExperienceRepo e, QuestionRepo q, AnswerRepo a){ this.expRepo=e; this.qRepo=q; this.aRepo=a; }

    @GetMapping
    public Map<String,Object> search(@RequestParam String q){
        String qBool = toBooleanMode(q);
        return Map.of(
            "experiences", expRepo.search(qBool, 10),
            "questions", qRepo.search(qBool, 10),
            "answers", aRepo.search(qBool, 10)
        );
    }

    private String toBooleanMode(String raw){
        String[] parts = raw.trim().split("\s+");
        StringBuilder sb = new StringBuilder();
        for(String p: parts){
            if(p.length()>0){ sb.append("+").append(p).append("* ").toString(); }
        }
        return sb.toString().trim();
    }
}
