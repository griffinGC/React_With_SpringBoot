package com.griffin.todolistwithreact.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.griffin.todolistwithreact.entity.Todo;
import com.griffin.todolistwithreact.exception.ResourceNotFoundException;
import com.griffin.todolistwithreact.repository.TodoRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TodoController {
	@Autowired
	TodoRepository todoRepository;
	
	// 모두 조회
	@GetMapping("/api/todos")
	public List<Todo> getTodos(){
		return todoRepository.findAll();
	}
	
	// 1명 조회
	@GetMapping("/api/todos/{id}")
	public Todo getTodo(@PathVariable Long id) {
		Optional<Todo> todo = todoRepository.findById(id);
		Todo todoObj = todo.orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
		return todoObj;
	}
	
	// 등록
	@PostMapping("/api/todos")
	public Todo createTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}
	
	// 1개 삭제
	@DeleteMapping("/api/todos/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable Long id){
		Todo delObject = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
		todoRepository.delete(delObject);
		return new ResponseEntity(delObject + " deleted ", HttpStatus.OK );
	}
	
	// checked 수정
	@PutMapping("/api/todos/{id}")
	public Todo updateCheckTodo(@PathVariable Long id, @RequestBody Todo changedTodo) {
		Todo updateObj = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
		updateObj.setChecked(changedTodo.getChecked());
		updateObj.setText(changedTodo.getText());
		Todo Updated = todoRepository.save(updateObj);
		return Updated;
	}
	
}

