package com.superloop.interview.todo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.superloop.interview.todo.exception.BadRequestException;
import com.superloop.interview.todo.model.TodoItem;
import com.superloop.interview.todo.repository.TodoListRepository;

/**
 * provide restful APIs for frontend
 * 
 * @author David Ding
 */
@RestController
@CrossOrigin
public class TodoListController {
	private final Logger log = LoggerFactory.getLogger(TodoListController.class);

	private final static int DESCRIPTION_MAX_LENGTH = 500;

	@Autowired
	private TodoListRepository todoListRepository;

	@PostMapping("/item/add")
	public ResponseEntity<Long> createItem(@RequestBody TodoItem todoItem) {
		checkItem(todoItem);
		Long id = todoListRepository.createItem(todoItem);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

	@PostMapping("/item/update")
	public ResponseEntity<String> updateItem(@RequestBody TodoItem todoItem) {
		checkId(todoItem.getId());
		checkItem(todoItem);
		todoListRepository.updateItem(todoItem);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/item/list")
	public ResponseEntity<Iterable<TodoItem>> listItems() {
		List<TodoItem> itemList = todoListRepository.findItems();
		return new ResponseEntity<>(itemList, HttpStatus.OK);
	}

	@GetMapping("/item/get/{id}")
	public ResponseEntity<TodoItem> getItem(@PathVariable("id") Long id) {
		checkId(id);
		return new ResponseEntity<>(todoListRepository.findOneItemById(id), HttpStatus.OK);
	}

	@PostMapping("/item/delete/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") Long id) {
		checkId(id);
		todoListRepository.deleteItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/item/complete/{id}")
	public ResponseEntity<String> completeItem(@PathVariable("id") Long id) {
		checkId(id);
		todoListRepository.completeItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void checkId(Long id) {
		if (id == null || id < 0) {
			throw new BadRequestException();
		}
	}

	/**
	 * check item obj "name" is required if "description" exist, the maxLength
	 * is 500.
	 * 
	 * @param todoItem
	 */
	private void checkItem(TodoItem todoItem) {
		if (todoItem == null || StringUtils.isEmpty(todoItem.getName())
				|| (todoItem.getDescription() != null && todoItem.getDescription().length() > DESCRIPTION_MAX_LENGTH)) {
			throw new BadRequestException();
		}
	}

}
