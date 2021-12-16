package com.example.fuzzymatch.todo;

import com.example.fuzzymatch.todo.control.ToDoControl;
import com.example.fuzzymatch.todo.dto.ToDoDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Validated
public class ToDosV1Boundary {

    private final ToDoControl toDoControl;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<ToDoDetailsResponseDto>> findAll(@RequestParam(value = "partial_title", required = false) final String partialTitle,
                                                                @RequestParam(value = "like_partial_title", required = false) final String likePartialTitle,
                                                                @RequestParam(value = "starting_partial_title", required = false) final String startingPartialTitle) {
        if (StringUtils.hasText(partialTitle)) {
            return ResponseEntity.status(OK).body(this.toDoControl.findAll(partialTitle));
        } else if (StringUtils.hasText(likePartialTitle)) {
            return ResponseEntity.status(OK).body(this.toDoControl.findAllLike(likePartialTitle));
        } else if (StringUtils.hasText(startingPartialTitle)) {
            return ResponseEntity.status(OK).body(this.toDoControl.findAllStartingWith(startingPartialTitle));
        }
        return ResponseEntity.status(OK).body(this.toDoControl.findAll());
    }
}
