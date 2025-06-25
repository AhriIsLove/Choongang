package com.oracle.oBootTodoApi01.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootTodoApi01.dto.TodoDTO;
import com.oracle.oBootTodoApi01.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/todo")
public class TodoController {
	private final TodoService todoService;
	
	@PostMapping("/register")
	//@RequestBody : Body로 넘겨받은
	public Map<String, Long> register(@RequestBody TodoDTO todoDTO){
		log.info("register S todoDTO : " + todoDTO);
		
		Long tno = todoService.register(todoDTO);
		
		log.info("register E tno : " + tno);
		return Map.of("TNO", tno);
	}
	
	@GetMapping("/{tno}")
	//@PathVariable : 주소로 넘겨 받음
	public TodoDTO get(@PathVariable(name = "tno") Long tno) {
		log.info("get S tno : " + tno);
		
		
		log.info("get E");
		return todoService.get(tno);
	}
	
}
