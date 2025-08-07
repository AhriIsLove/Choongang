package com.oracle.oBootTodoApi01.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootTodoApi01.dao.TodoDao;
import com.oracle.oBootTodoApi01.domain.Todo;
import com.oracle.oBootTodoApi01.dto.PageRequestDTO;
import com.oracle.oBootTodoApi01.dto.PageResponseDTO;
import com.oracle.oBootTodoApi01.dto.TodoDTO;
import com.oracle.oBootTodoApi01.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

	//자동주입 대상은 final로 
    private final ModelMapper modelMapper;
	private final TodoRepository todoRepository;
	private final TodoDao        todoDao;      
	
	
	@Override
	public Long register(TodoDTO todoDTO) {
		log.info("register start todoDTO->"+todoDTO);
		Todo todo = modelMapper.map(todoDTO , Todo.class);
		
		Todo saveTodo = todoRepository.save(todo);
		
		return saveTodo.getTno();
	}

	@Override
	public int todoTotal() {
		int totalCount = todoDao.totalTodo();
		System.out.println("TodoServiceImpl todoTotal totalCount->"+totalCount);
		return totalCount;
	}

	@Override
	public TodoDTO get(Long tno) {
		Optional<Todo> maybeTodo = todoRepository.findById(tno);
		
		Todo todo = maybeTodo.orElseThrow();
		
		TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
		
		return dto;
	}

	@Override
	public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
	    List<TodoDTO> dtoList = todoDao.listTodo(pageRequestDTO);
	    System.out.println("list dtoList->"+dtoList);
	    // 전체 갯수 ---------------------------------------------
	    int totalCount = todoDao.totalTodo();	
	    
	    PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
	    			.dtoList(dtoList)
	    			.pageRequestDTO(pageRequestDTO)
	    			.totalCount(totalCount)
	    			.build()
	    			;

	    
		return responseDTO;
	}

	@Override
	public void modify(TodoDTO todoDTO) {
		Optional<Todo> maybeTodo = todoRepository.findById(todoDTO.getTno());
		Todo todo = maybeTodo.orElseThrow();
		todo.changeTitle(todoDTO.getTitle());
		todo.changeWriter(todoDTO.getWriter());
		todo.changeDueDate(todoDTO.getDue_date());
		todo.changeComplete(todoDTO.isComplete());
	    System.out.println("TodoServiceImpl modify todo->"+todo);

	    todoRepository.save(todo);
	}

	@Override
	public void remove(Long tno) {
		todoRepository.deleteById(tno);

		
	}

}
