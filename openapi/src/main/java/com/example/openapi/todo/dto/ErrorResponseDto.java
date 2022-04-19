package com.example.openapi.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDto {

    @Schema(description = "The unique identifier of the error")
    private String code;

    @Schema(description = "The detailed description of the error")
    private String message;

    @Schema(description = "The HTTP status associated with the error")
    private Integer status;

    @Schema(description = "The timestamp when the error occurred")
    private LocalDateTime timestamp;

}
