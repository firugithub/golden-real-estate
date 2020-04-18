package com.firoz.todo.service;

import java.util.List;

import com.firoz.todo.model.Todo;

public interface TodoService {
    List<Todo> findAll();

    Todo save(Todo todo);

    List<Todo> searchCriteria(Todo todo);

    void delete(Todo todo);
}