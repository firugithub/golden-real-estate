package com.firoz.todo.repository;

import com.firoz.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbstractRepository extends CrudRepository<Todo, Long>{

}
