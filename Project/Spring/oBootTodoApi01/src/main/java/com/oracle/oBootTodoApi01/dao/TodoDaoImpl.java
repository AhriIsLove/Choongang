package com.oracle.oBootTodoApi01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootTodoApi01.dto.PageRequestDTO;
import com.oracle.oBootTodoApi01.dto.TodoDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TodoDaoImpl implements TodoDao {
	private final SqlSession session;
	
	@Override
	public int totalTodo() {
		int totTodoCount = 0;
		System.out.println("TodoDaoImpl totalTodo S");
		
		try {
			totTodoCount = session.selectOne("com.oracle.oBootTodoApi01.TodoMapper.todoTotal");
			System.out.println("TodoDaoImpl totalTodo 1-1 totTodoCount : " + totTodoCount);
		} catch (Exception e) {
			System.out.println("TodoDaoImpl totalTodo 1-2");
		}
		
		return totTodoCount;
	}

	@Override
	public List<TodoDTO> listTodo(PageRequestDTO pageRequestDTO) {
		int end = 0;
		List<TodoDTO> todoList = null;
		System.out.println("TodoDaoImpl listTodo S pageRequestDTO : " + pageRequestDTO);
		
		TodoDTO todoDTO = new TodoDTO();
		todoDTO.setStart(pageRequestDTO.getStart());
		todoDTO.setEnd(pageRequestDTO.getEnd());
		System.out.println("TodoDaoImpl listTodo 1 todoDTO : " + todoDTO);

		try {
			todoList = session.selectList("tkTodoListAll", todoDTO);
			System.out.println("TodoDaoImpl listTodo 2-1 todoList : " + todoList);
		} catch (Exception e) {
			System.out.println("TodoDaoImpl listTodo 2-2 e : " + e.getMessage());
		}
		
		return todoList;
	}

}
