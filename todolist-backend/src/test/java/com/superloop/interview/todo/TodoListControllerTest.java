package com.superloop.interview.todo;

import org.junit.Before;
import org.junit.Test;

import com.superloop.interview.todo.controller.TodoListController;
import com.superloop.interview.todo.exception.BadRequestException;
import com.superloop.interview.todo.model.TodoItem;

public class TodoListControllerTest {
	private TodoListController todoListController;

	@Before
	public void setup() {
		todoListController = new TodoListController();
	}

	@Test(expected = BadRequestException.class)
	public void testDeleteItemWithInvalidId() {
		todoListController.deleteItem(-1L);
	}
	
	@Test(expected = BadRequestException.class)
	public void testUpdateItemWithInvalidItem() {
		TodoItem item = TestObjectMother.buildTodoItem();
		item.setDescription(getString(501));
		todoListController.updateItem(item);
	}
	
	private String getString(long stringLength){
		StringBuilder sb = new StringBuilder();
		for(long i=0; i< stringLength; i++){
			sb.append("a");
		}
		return sb.toString();
	}
	
	
}
