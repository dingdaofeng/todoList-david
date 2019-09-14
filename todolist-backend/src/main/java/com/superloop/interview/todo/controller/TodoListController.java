package com.superloop.interview.todo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.superloop.interview.todo.model.TodoItem;
import com.superloop.interview.todo.repository.TodoListRepository;
import com.superloop.interview.todo.type.ItemStatus;

/**
 * @author David Ding
 */
@RestController
public class TodoListController {
	private final Logger log = LoggerFactory.getLogger(TodoListController.class);

	@Autowired
	private TodoListRepository todoListRepository;

	@PostMapping("/item/add")
	public ResponseEntity<Long> createItem(@RequestBody TodoItem todoItem) {
		Long id = todoListRepository.createItem(todoItem);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@PostMapping("/item/update")
	public ResponseEntity<String> updateItem(@RequestBody TodoItem todoItem) {
		todoListRepository.updateItem(todoItem);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/item/list/{statusValue}")
	public ResponseEntity<Iterable<TodoItem>> listItems(@PathVariable("statusValue") Integer statusValue) {
		List<TodoItem> itemList = todoListRepository.findItemsByStatus(ItemStatus.fromValue(statusValue));
		return new ResponseEntity<>(itemList, HttpStatus.OK);
	}

	@GetMapping("/item/get/{id}")
	public ResponseEntity<TodoItem> getItem(@PathVariable("id") Long id) {
		return new ResponseEntity<>(todoListRepository.findOneItemById(id), HttpStatus.OK);
	}

	@PostMapping("/item/delete/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") Long id) {
		todoListRepository.deleteItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/item/complete/{id}")
	public ResponseEntity<String> completeItem(@PathVariable("id") Long id) {
		todoListRepository.completeItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
