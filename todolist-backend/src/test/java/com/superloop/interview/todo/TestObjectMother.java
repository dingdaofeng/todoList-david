package com.superloop.interview.todo;

import com.superloop.interview.todo.model.TodoItem;

public class TestObjectMother {
  // ---------------------ItemObject-------------------------------
  public static TodoItem buildTodoItem() {
	TodoItem item = new TodoItem("David", "find job", "2019-09-15");
	return item;
  }
  
  public static TodoItem buildTodoItem(long id, String name, String description, String date) {
	TodoItem item = new TodoItem(name, description, date);
	item.setId(id);
	return item;
  }
}
