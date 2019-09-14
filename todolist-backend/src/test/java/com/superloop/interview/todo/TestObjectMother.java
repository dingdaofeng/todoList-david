package com.superloop.interview.todo;

import java.util.Date;

import com.superloop.interview.todo.model.TodoItem;

public class TestObjectMother {
  // ---------------------ItemObject-------------------------------
  public static TodoItem buildTodoItem() {
	TodoItem item = new TodoItem("David", "find job", new Date());
	return item;
  }
  
  public static TodoItem buildTodoItem(long id, String name, String description, Date date) {
	TodoItem item = new TodoItem(name, description, date);
	item.setId(id);
	return item;
  }
}
