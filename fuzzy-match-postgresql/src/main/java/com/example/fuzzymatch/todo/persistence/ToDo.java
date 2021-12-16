package com.example.fuzzymatch.todo.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_todo")
public class ToDo {

    @Id
    @GenericGenerator(name = "todo_id_gen", strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "todo_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "10")
            })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_id_gen")
    private Long id;

    @Column(updatable = false)
    private String extRef;

    private String title;

    private Boolean active;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void auditBeforeCreate() {
        updatedAt = createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void auditBeforeUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
