package com.example.fuzzymatch.todo.dto.mapper;

import com.example.fuzzymatch.todo.dto.ToDoDetailsResponseDto;
import com.example.fuzzymatch.todo.persistence.ToDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ToDoMapper {

    @Mappings({
            @Mapping(target = "extRef", source = "extRef"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "created", source = "createdAt"),
            @Mapping(target = "updated", source = "updatedAt")
    })
    ToDoDetailsResponseDto toDoToToDoDetailsResponseDto(ToDo toDo);

}
