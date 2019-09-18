package com.superloop.interview.todo.repository;

import org.junit.Before;
import org.junit.Test;

import com.superloop.interview.todo.TestObjectMother;
import com.superloop.interview.todo.exception.BadRequestException;
import com.superloop.interview.todo.model.TodoItem;

import junit.framework.TestCase;

public class TodoListMemRepositoryTest {
	private TodoListMemRepository todoListRepository;

	@Before
	public void setup() {
		todoListRepository = new TodoListMemRepository();
	}

	@Test
	public void testCreateItem() {
		int olderSize = todoListRepository.itemMap.size();
		todoListRepository.createItem(TestObjectMother.buildTodoItem());
		TestCase.assertEquals(todoListRepository.itemMap.size(), olderSize + 1);

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
		TestCase.assertEquals(false, findItem.isDone());
		todoListRepository.completeItem(id);
		TestCase.assertEquals(true, findItem.isDone());
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

		TodoItem updatedItem = TestObjectMother.buildTodoItem(id, updatedName, updatedDescription, "2019-09-06");
		todoListRepository.updateItem(updatedItem);
		findItem = todoListRepository.findOneItemById(id);

		TestCase.assertTrue(updatedName.equals(findItem.getName()));
		TestCase.assertTrue(updatedDescription.equals(findItem.getDescription()));
	}

	@Test
	public void testFindItems() {
		TodoItem oldItem = TestObjectMother.buildTodoItem();
		long id = todoListRepository.createItem(oldItem);
		int oldItemCount = todoListRepository.findItems().size();

		TodoItem addedItem = TestObjectMother.buildTodoItem();

		todoListRepository.createItem(addedItem);

		int newItemCount = todoListRepository.findItems().size();

		TestCase.assertEquals(oldItemCount + 1, newItemCount);
	}
}
