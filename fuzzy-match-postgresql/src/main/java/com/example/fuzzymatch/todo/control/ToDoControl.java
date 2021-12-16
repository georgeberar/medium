package com.example.fuzzymatch.todo.control;


import com.example.fuzzymatch.todo.dto.ToDoDetailsResponseDto;
import com.example.fuzzymatch.todo.dto.mapper.ToDoMapper;
import com.example.fuzzymatch.todo.persistence.ToDo;
import com.example.fuzzymatch.todo.persistence.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ToDoControl {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    @PostConstruct
    @Transactional
    public void initData() {
        final ToDo cleanHouse = ToDo.builder().extRef(UUID.randomUUID().toString()).title("Clean House").build();
        final ToDo cleanCar = ToDo.builder().extRef(UUID.randomUUID().toString()).title("Clean Car").build();
        final ToDo doHomework = ToDo.builder().extRef(UUID.randomUUID().toString()).title("Do Homework").build();
        final ToDo goShopping = ToDo.builder().extRef(UUID.randomUUID().toString()).title("Go Shopping").build();
        final ToDo goToSleep = ToDo.builder().extRef(UUID.randomUUID().toString()).title("Go To Sleep").build();
        this.toDoRepository.saveAll(Arrays.asList(cleanHouse, cleanCar, doHomework, goShopping, goToSleep));
    }


    public List<ToDoDetailsResponseDto> findAll(final String partialTitle) {
        return this.toDoRepository.findAllMatching(partialTitle).stream().map(this.toDoMapper::toDoToToDoDetailsResponseDto)
                .collect(Collectors.toList());
    }

    public List<ToDoDetailsResponseDto> findAll() {
        return this.toDoRepository.findAll().stream().map(this.toDoMapper::toDoToToDoDetailsResponseDto)
                .collect(Collectors.toList());
    }

}
