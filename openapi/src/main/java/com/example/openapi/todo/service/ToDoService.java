package com.example.openapi.todo.service;

import com.example.openapi.todo.dto.ToDoDetailsResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ToDoService {

    private static List<ToDoDetailsResponseDto> todos = List.of(
            ToDoDetailsResponseDto.builder().id(UUID.randomUUID()).title("Clean House").done(true).build(),
            ToDoDetailsResponseDto.builder().id(UUID.randomUUID()).title("Walk Dog").done(false).build());

    public List<ToDoDetailsResponseDto> findAll() {
        return todos;
    }

    public ToDoDetailsResponseDto findById(final UUID id) {
        return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public void delete(final UUID id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }

}
