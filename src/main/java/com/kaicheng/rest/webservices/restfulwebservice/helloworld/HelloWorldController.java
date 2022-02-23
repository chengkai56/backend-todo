package com.kaicheng.rest.webservices.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
@CrossOrigin(origins = "https://frontend-my-todo.herokuapp.com")
public class HelloWorldController {
	
	//Get
	//URI - /hello-world
	// method - "Hello World"
//	@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	
	//hello-world-bean
	@GetMapping(path="/hello-world-bean")
	public helloWorldBean helloWorldBean() {
		return new helloWorldBean("Hello World Bean");
	}
	
	///hello-world/path-variable/kaicheng
	@GetMapping(path="/hello-world/path-variable/{name}")
	public helloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
//		throw new RuntimeException("Something went wrong");
		
		return new helloWorldBean(String.format("Hello World, %s", name));
	}
}
