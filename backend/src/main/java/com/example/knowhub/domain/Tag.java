package com.example.knowhub.domain;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

@Entity @Table(name="tags")
@Getter @Setter
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true, length=64)
    private String name;
}
