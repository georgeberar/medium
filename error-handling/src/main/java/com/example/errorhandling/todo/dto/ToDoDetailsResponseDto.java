package com.example.errorhandling.todo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDetailsResponseDto {

    private String extRef;
    private String title;
    private LocalDateTime created;

}
