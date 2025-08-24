package com.example.knowhub.domain;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;
import java.time.Instant;
import java.util.*;

@Entity @Table(name="experiences")
@Getter @Setter
public class Experience {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) private User author;
    @Column(nullable=false, length=200) private String title;
    @Lob @Column(nullable=false) private String body;
    private Instant createdAt = Instant.now();
    private Instant updatedAt;
    private boolean isDeleted = false;

    @ManyToMany
    @JoinTable(name="experience_tags",
        joinColumns=@JoinColumn(name="experience_id"),
        inverseJoinColumns=@JoinColumn(name="tag_id"))
    private Set<Tag> tags = new HashSet<>();
}
