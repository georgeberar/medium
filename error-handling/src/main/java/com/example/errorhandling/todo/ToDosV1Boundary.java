package com.example.errorhandling.todo;

import com.example.errorhandling.todo.control.ToDoControl;
import com.example.errorhandling.todo.dto.CreateToDoRequestDto;
import com.example.errorhandling.todo.dto.CreateToDoResponseDto;
import com.example.errorhandling.todo.dto.ToDoDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Validated
public class ToDosV1Boundary {

    private final ToDoControl toDoControl;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public ResponseEntity<CreateToDoResponseDto> create(@Valid @RequestBody final CreateToDoRequestDto body) {
        return ResponseEntity.status(CREATED).body(this.toDoControl.create(body));
    }

    @GetMapping(value = "/{extRef}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<ToDoDetailsResponseDto> findByExtRef(@PathVariable(name = "extRef") @NotEmpty final String extRef) {
        return ResponseEntity.status(OK).body(this.toDoControl.findByExtRef(extRef));
    }

    @DeleteMapping(value = "/{extRef}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> deleteByExtRef(@PathVariable(name = "extRef") @NotEmpty final String extRef) {
        this.toDoControl.deleteByExtRef(extRef);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
