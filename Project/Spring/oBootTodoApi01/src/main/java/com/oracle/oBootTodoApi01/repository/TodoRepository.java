package com.oracle.oBootTodoApi01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.oBootTodoApi01.domain.Todo;

//JpaRepository<Todo, Long>를 상속받으면
//- PK가 Long타입인 Todo에 대하여
//- SELECT, INSERT, UPDATE, DELETE 제공
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
