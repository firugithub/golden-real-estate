package com.firoz.todo.controller;

import com.firoz.todo.model.Todo;
import com.firoz.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/todo")
public class TodoController {

  @Autowired
  TodoService todoService;

  public TodoController() {
  }


  @GetMapping(value = "/findAll")
  public ResponseEntity<List<Todo>>  listTodo() {
    return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
  }

  @PostMapping(value = "/search")
  public ResponseEntity<List<Todo>>  searchTodo(@RequestBody Todo todo) {
    return new ResponseEntity<>(todoService.searchCriteria(todo), HttpStatus.OK);
  }

  @PostMapping(value = "/save")
  public ResponseEntity<Todo>  saveTodo(@RequestBody Todo todo) {
    return new ResponseEntity<>(todoService.save(todo), HttpStatus.OK);
  }

  @DeleteMapping(value = "/delete")
  public ResponseEntity<String>  deleteTodo(@RequestBody Todo todo) {
    todoService.delete(todo);
    return new ResponseEntity<>("Success", HttpStatus.OK);
  }


}
