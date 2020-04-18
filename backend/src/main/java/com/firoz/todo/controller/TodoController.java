package com.firoz.todo.controller;

import com.firoz.todo.model.Todo;
import com.firoz.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

  @Autowired
  TodoService todoService;

  private List<Todo> todos;

  public TodoController() {
    todos = new ArrayList<>();
    /*todos.add(new Todo("nicoll", "Stéphane Nicoll"));
    todos.add(new Todo("habuma", "Craig Wells"));
    todos.add(new Todo("maciejwalkowiak", "Maciej Walkowiak"));
    todos.add(new Todo("jitterted", "Ted M. Young"));
    todos.add(new Todo("mraible", "Matt Raible"));*/
  }


  @GetMapping(value = "/findAll")
  public ResponseEntity<List<Todo>>  listTodo() {
    return new ResponseEntity<>(todoService.findAll(), HttpStatus.FOUND);
  }

  @PostMapping(value = "/search")
  public ResponseEntity<List<Todo>>  searchTodo(@RequestBody Todo todo) {
    return new ResponseEntity<>(todoService.searchCriteria(todo), HttpStatus.FOUND);
  }

  @PostMapping(value = "/save")
  public ResponseEntity<Todo>  saveTodo(@RequestBody Todo todo) {
    return new ResponseEntity<>(todoService.save(todo), HttpStatus.FOUND);
  }


}
