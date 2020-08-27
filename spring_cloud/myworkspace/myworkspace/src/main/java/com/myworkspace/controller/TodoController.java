package com.myworkspace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myworkspace.entity.TodoEntity;
import com.myworkspace.repository.TodoRepository;

@RestController
public class TodoController {
	
	@Autowired
	TodoRepository todoRepo;
	
	// todo ��� ��ȸ
	@RequestMapping(value="/todo", method=RequestMethod.GET)
	public List<TodoEntity> getTodo() throws Exception{
		return todoRepo.findAll(new Sort(Sort.Direction.DESC,"id"));
	}
	
	// todo �߰�/����
	@RequestMapping(value="/todo", method= {RequestMethod.POST, RequestMethod.PUT})
	public TodoEntity saveTodo(@RequestBody TodoEntity t) throws Exception{
		System.out.println(t.toString());
		return todoRepo.save(t);
	}
	
	// todo ���� ������ 
	@RequestMapping(value="/todo/save-all", method= {RequestMethod.POST, RequestMethod.PUT})
	public List<TodoEntity> saveTodoAll(@RequestBody List<TodoEntity> t) throws Exception{
		System.out.println(t.toString());
		return todoRepo.saveAll(t);
	}
	
	// todo ����
	@RequestMapping(value="/todo/{id}", method= {RequestMethod.DELETE})
	public void removeTodo(@PathVariable int id) throws Exception{
		System.out.println(id);
		todoRepo.deleteById(id);
	}	
}
