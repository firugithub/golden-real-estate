package com.firoz.todo.repository.impl;

import com.firoz.todo.model.Todo;
import com.firoz.todo.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
abstract class AbstractRepositoryImpl implements AbstractRepository {

    @Override
    public <S extends Todo> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Todo> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Todo> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Todo todo) {

    }

    @Override
    public void deleteAll(Iterable<? extends Todo> iterable) {

    }

    @Override
    public void deleteAll() {

    }

}