package com.firoz.todo.service.impl;

import com.firoz.todo.model.Todo;
import com.firoz.todo.repository.AbstractRepository;
import com.firoz.todo.repository.TodoRepository;
import com.firoz.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private AbstractRepository todoRepository;

    @Autowired
    private TodoRepository repository;


    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();
        todoRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> searchCriteria(Todo todo) {
        return repository.search(todo);
    }


}