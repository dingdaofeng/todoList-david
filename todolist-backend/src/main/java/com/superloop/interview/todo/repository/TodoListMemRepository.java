package com.superloop.interview.todo.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.superloop.interview.todo.model.TodoItem;

@Service
public class TodoListMemRepository implements TodoListRepository {

	protected Map<Long, TodoItem> itemMap = new ConcurrentHashMap<>();

	Logger log = LoggerFactory.getLogger(TodoListMemRepository.class);
	
	/**
	 * when find items, we sort the result by id, and if any pending item is overTime, mark it
	 */
	@Override
	public List<TodoItem> findItems() {
		List<TodoItem> result = itemMap.values().stream().map(x -> {
			if ((x.isDone() == false) && x.checkOverTime()) {
				x.setOverTime(true);
			} 
			return x;
		}).sorted(Comparator.comparingLong(TodoItem::getId)).collect(Collectors.toList());

		return result;
	}

	@Override
	public TodoItem findOneItemById(long id) {
		return itemMap.get(id);
	}

	@Override
	public void completeItem(long id) {
		TodoItem item = itemMap.get(id);
		if (item != null) {
			item.complete();
		}
	}

	@Override
	public long createItem(TodoItem item) {
		TodoItem newItem = new TodoItem(item.getName(), item.getDescription(), item.getDueDate());
		itemMap.put(newItem.getId(), newItem); 
		return newItem.getId();
	}

	@Override
	public void updateItem(TodoItem item) {
		TodoItem oldItem = itemMap.get(item.getId());
		if (oldItem == null) {
			log.error("can't find item:" + item.getId());
			return;
		}

		TodoItem refreshedItem = refreshItem(oldItem, item);
		itemMap.put(item.getId(), refreshedItem);
	}

	/**
	 * Only name, description, dueDate could be modified when invoke updateItem.
	 * 
	 * @param oldItem
	 * @param newItem
	 * @return
	 */
	private TodoItem refreshItem(TodoItem oldItem, TodoItem newItem) {
		oldItem.setName(newItem.getName());
		oldItem.setDescription(newItem.getDescription());
		oldItem.setDueDate(newItem.getDueDate());
		return oldItem;
	}

	@Override
	public void deleteItem(long id) {
		itemMap.remove(id);
	}

}
