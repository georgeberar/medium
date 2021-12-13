package com.example.errorhandling.todo.persistence;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {

    private String extRef;
    private String title;
    private LocalDateTime created;

}
