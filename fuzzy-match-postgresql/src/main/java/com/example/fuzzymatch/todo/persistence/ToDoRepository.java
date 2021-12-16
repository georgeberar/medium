package com.example.fuzzymatch.todo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query(value = "SELECT * FROM t_todo todo WHERE ?1 % ANY(STRING_TO_ARRAY(todo.title,' '))", nativeQuery = true)
    List<ToDo> findAllMatching(String partialTitle);

    List<ToDo> findByTitleLike(String partialTitle);

    List<ToDo> findByTitleStartingWith(String partialTitle);

}
