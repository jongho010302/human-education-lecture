package com.myworkspace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myworkspace.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

}
