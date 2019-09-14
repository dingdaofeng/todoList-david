package com.superloop.interview.todo.repository;

import java.util.List;

import com.superloop.interview.todo.model.TodoItem;
import com.superloop.interview.todo.type.ItemStatus;


public interface TodoListRepository {
    List<TodoItem> findItemsByStatus(ItemStatus itemStatus);

    TodoItem findOneItemById(long id);
    
    void completeItem(long id);
    
    long createItem(TodoItem item);
    
    void updateItem(TodoItem item);

    void deleteItem(long id);
}
