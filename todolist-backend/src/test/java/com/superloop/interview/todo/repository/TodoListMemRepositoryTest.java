package com.superloop.interview.todo.repository;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.superloop.interview.todo.TestObjectMother;
import com.superloop.interview.todo.model.TodoItem;
import com.superloop.interview.todo.type.ItemStatus;

import junit.framework.TestCase;

public class TodoListMemRepositoryTest {
	 private TodoListMemRepository todoListRepository;


	  @Before
	  public void setup() {
		  todoListRepository = new TodoListMemRepository();
	  }

//	    List<TodoItem> findItemsByStatus(ItemStatus itemStatus);
//
//	    TodoItem findOneItemById(long id);
//	    
//	    void completeItem(long id);
//	    
//	    void createItem(TodoItem item);
//	    
//	    void updateItem(TodoItem item);
//
//	    void deleteItem(long id);
	  
	  @Test
	  public void testCreateItem() {
		  int olderSize = todoListRepository.itemMap.size();
		  todoListRepository.createItem(TestObjectMother.buildTodoItem());
		  TestCase.assertEquals(todoListRepository.itemMap.size(), olderSize+1);
		  
		  System.out.println(todoListRepository.itemMap);
	  }
	  
	  @Test
	  public void testFindOneItemById() {
		  TodoItem item = TestObjectMother.buildTodoItem();
		  long id = todoListRepository.createItem(item);
		  TodoItem findItem = todoListRepository.findOneItemById(id);
		  TestCase.assertNotNull(findItem);
		  TestCase.assertEquals(item.getName(), findItem.getName());
		  TestCase.assertEquals(item.getDescription(), findItem.getDescription());
		  TestCase.assertEquals(item.getDueDate(), findItem.getDueDate());
	  }
	  
	  @Test
	  public void testDeleteItem() {
		  long id = todoListRepository.createItem(TestObjectMother.buildTodoItem());

		  TestCase.assertNotNull(todoListRepository.findOneItemById(id));
		  todoListRepository.deleteItem(id);
		  TestCase.assertNull(todoListRepository.findOneItemById(id));
	  }
	  
	  @Test
	  public void testCompleteItem() {
		  long id = todoListRepository.createItem(TestObjectMother.buildTodoItem());
		  TodoItem findItem = todoListRepository.findOneItemById(id);
		  TestCase.assertEquals(ItemStatus.Pending, findItem.getStatus());
		  todoListRepository.completeItem(id);
		  TestCase.assertEquals(ItemStatus.Done, findItem.getStatus());
	  }
	  
	  @Test
	  public void testUpdateItem() {
		  TodoItem oldItem = TestObjectMother.buildTodoItem();
		  long id = todoListRepository.createItem(oldItem);
		  TodoItem findItem = todoListRepository.findOneItemById(id);
		  String updatedName = "updatedName";
		  String updatedDescription = "updatedDescription";
		  
		  TestCase.assertFalse(updatedName.equals(findItem.getName()));
		  TestCase.assertFalse(updatedDescription.equals(findItem.getDescription()));
		  
		  TodoItem updatedItem = TestObjectMother.buildTodoItem(id, updatedName, updatedDescription, new Date());
		  todoListRepository.updateItem(updatedItem);
		  findItem = todoListRepository.findOneItemById(id);
		  
		  TestCase.assertTrue(updatedName.equals(findItem.getName()));
		  TestCase.assertTrue(updatedDescription.equals(findItem.getDescription()));		  
	  }
	  
	  @Test
	  public void testFindItemsByStatus(){
		  TodoItem oldItem = TestObjectMother.buildTodoItem();
		  long id = todoListRepository.createItem(oldItem);
		  int oldPendingCount = todoListRepository.findItemsByStatus(ItemStatus.Pending).size();
		  int oldDoneCount = todoListRepository.findItemsByStatus(ItemStatus.Done).size();
		  
		  todoListRepository.completeItem(id);
		  
		  int newPendingCount = todoListRepository.findItemsByStatus(ItemStatus.Pending).size();
		  int newDoneCount = todoListRepository.findItemsByStatus(ItemStatus.Done).size();
		  
		  System.out.println(oldPendingCount);
		  System.out.println(oldDoneCount);
		  System.out.println(newPendingCount);
		  System.out.println(newDoneCount);
		  
		  TestCase.assertEquals(oldPendingCount - 1, newPendingCount);
		  TestCase.assertEquals(oldDoneCount + 1, newDoneCount);	  
	  }
}
