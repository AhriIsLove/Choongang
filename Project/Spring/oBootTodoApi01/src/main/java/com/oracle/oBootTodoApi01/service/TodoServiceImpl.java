package com.oracle.oBootTodoApi01.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oracle.oBootTodoApi01.dao.TodoDao;
import com.oracle.oBootTodoApi01.domain.Todo;
import com.oracle.oBootTodoApi01.dto.PageRequestDTO;
import com.oracle.oBootTodoApi01.dto.PageResponseDTO;
import com.oracle.oBootTodoApi01.dto.TodoDTO;
import com.oracle.oBootTodoApi01.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {
	private final ModelMapper modelMapper;
	private final TodoRepository todoRepository;
	private final TodoDao todoDao;
	
	@Override
	public Long register(TodoDTO todoDTO) {
		log.info("register 1 todoDTO : " + todoDTO);

		//DTO -> Entity
		// - 보안상 DTO로 받고 DB저장은 Entity로 하는 것을 권장
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		
		Todo saveTodo = todoRepository.save(todo);
		
		log.info("register 2 saveTodo : " + saveTodo);
		return saveTodo.getTno();
	}

	@Override
	public int todoTotal() {
		int totalCount = todoDao.totalTodo();
		return totalCount;
	}

	@Override
	public TodoDTO get(Long tno) {
		log.info("get 1");
		Optional<Todo> maybeTodo = todoRepository.findById(tno);
		
		//maybeTodo가 Todo로 저장
		// - 안되면 Throw
		Todo todo = maybeTodo.orElseThrow();

		TodoDTO todoDTO = modelMapper.map(todo, TodoDTO.class);
		return todoDTO;
		
//		return modelMapper.map(todoRepository.findById(tno).orElseThrow(), TodoDTO.class);
	}

	@Override
	public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
		List<TodoDTO> dtoList = todoDao.listTodo(pageRequestDTO);
		System.out.println("TodoServiceImpl list S dtoList : " + dtoList.size());
		
		int totalCount = todoDao.totalTodo();
	
		//Builder 사용
		PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
				.dtoList(dtoList)
				.pageRequestDTO(pageRequestDTO)
				.totalCount(totalCount)
				.build();
		
		return responseDTO;
	}

	@Override
	public void modify(TodoDTO todoDTO) {
		Optional<Todo> maybeTodo = todoRepository.findById(todoDTO.getTno());
		//todo로 cast시도하고 안되면 에러 Throw
		Todo todo = maybeTodo.orElseThrow();
		
		//change : UPDATE JPA 문법 
		todo.changeTitle(todoDTO.getTitle());
		todo.changeWriter(todoDTO.getWriter());
		todo.changeDueDate(todoDTO.getDue_date());
		todo.changeComplete(todoDTO.isComplete());
		
		//save : todo에 대한 COMMIT JPA 문법
		todoRepository.save(todo);
	}

	@Override
	public void remove(Long tno) {
		todoRepository.deleteById(tno);
	}

}
