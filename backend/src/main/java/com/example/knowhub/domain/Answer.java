package com.example.knowhub.domain;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;
import java.time.Instant;

@Entity @Table(name="answers")
@Getter @Setter
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) private Question question;
    @ManyToOne(optional=false) private User author;
    @Lob @Column(nullable=false) private String body;
    private Instant createdAt = Instant.now();
    private Instant updatedAt;
    private boolean isDeleted=false;
}
