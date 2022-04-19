package com.example.openapi.todo;

import com.example.openapi.todo.dto.ErrorResponseDto;
import com.example.openapi.todo.dto.ToDoDetailsResponseDto;
import com.example.openapi.todo.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Tag(name = "TODOs", description = "Manage TODOs")
public class ToDoBoundary {

    private final ToDoService toDoService;


    @Operation(summary = "Get TODOs", tags = {"TODOs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The TODOs are returned successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ToDoDetailsResponseDto.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<ToDoDetailsResponseDto>> getAll() {
        return ResponseEntity.ok(this.toDoService.findAll());
    }


    @Operation(summary = "Get TODO by id", tags = {"TODOs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The TODO is returned successfully",
                    content = @Content(schema = @Schema(implementation = ToDoDetailsResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "TODO not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<ToDoDetailsResponseDto> getById(
            @Parameter(name = "id", in = ParameterIn.PATH, required = true, description = "The id of the TODO", example = "00000000-00000000-00000000-00000000")
            @PathVariable("id") final UUID id) {
        return ResponseEntity.ok(this.toDoService.findById(id));
    }


    @Operation(summary = "Delete TODO by id", tags = {"TODOs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The TODO is deleted successfully",
                    content = @Content(schema = @Schema(implementation = ToDoDetailsResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "TODO not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> delete(
            @Parameter(name = "id", in = ParameterIn.PATH, required = true, description = "The id of the TODO", example = "00000000-00000000-00000000-00000000")
            @PathVariable("id") final UUID id) {
        this.toDoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
