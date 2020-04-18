package com.firoz.todo.repository;

import com.firoz.todo.model.Todo;

import java.util.List;

public interface TodoRepository {
	
	List<Todo> getTodoDetails();
	List<Todo> search(Todo todo);
}