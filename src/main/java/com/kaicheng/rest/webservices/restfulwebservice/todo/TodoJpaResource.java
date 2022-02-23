package com.kaicheng.rest.webservices.restfulwebservice.todo;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kaicheng.rest.webservices.restfulwebservice.todo.Todo;


@RestController
@CrossOrigin(origins = "https://frontend-my-todo.herokuapp.com")
public class TodoJpaResource {
	
	@Autowired
	private TodoHardcodedService todoService;

	@Autowired
	private TodoJpaRepository todoJpaRepository;

	
	@GetMapping("/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) throws InterruptedException {
		return todoJpaRepository.findByUsername(username);
//		Thread.sleep(3000);
//		return todoService.findAll();
	}
	
	@GetMapping("/jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) throws InterruptedException {
		return todoJpaRepository.findById(id).get();
//		Thread.sleep(3000);
//		return todoService.findById(id);
	}

	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		
		todoJpaRepository.deleteById(id);
		return ResponseEntity.noContent().build();

	}
	
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable String username, 
			@PathVariable long id, @RequestBody Todo todo){
		
		todo.setUsername(username);
		
		Todo todoUpdated = todoJpaRepository.save(todo);
		
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username, @RequestBody Todo todo){
		
		todo.setUsername(username);
		
		Todo createdTodo = todoJpaRepository.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
