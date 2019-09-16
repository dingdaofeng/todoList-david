package com.superloop.interview.todo.repository;

import java.util.List;

import com.superloop.interview.todo.model.TodoItem;

public interface TodoListRepository {
    List<TodoItem> findItems();

    TodoItem findOneItemById(long id);
    
    void completeItem(long id);
    
    long createItem(TodoItem item);
    
    void updateItem(TodoItem item);

    void deleteItem(long id);
}
