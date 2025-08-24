package com.example.knowhub.domain;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;
import java.time.Instant;

@Entity @Table(name="audit_logs")
@Getter @Setter
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant occurredAt = Instant.now();
    private Long userId;
    private String ip;
    private String userAgent;
    @Column(nullable=false) private String action;
    private String entity;
    private Long entityId;
    @Lob private String details; // JSON string
}
