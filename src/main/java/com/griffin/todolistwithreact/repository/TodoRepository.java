package com.griffin.todolistwithreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.griffin.todolistwithreact.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
