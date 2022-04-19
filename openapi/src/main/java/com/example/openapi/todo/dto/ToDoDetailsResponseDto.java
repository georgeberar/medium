package com.example.openapi.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDetailsResponseDto {

    @Schema(description = "The id of the TODO", example = "00000000-00000000-00000000-00000000")
    private UUID id;

    @Schema(description = "The title of the TODO", example = "Clean House")
    private String title;

    @Schema(description = "The status of the TODO", example = "true")
    private boolean done;

}
