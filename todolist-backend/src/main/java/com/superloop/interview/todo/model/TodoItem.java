package com.superloop.interview.todo.model;

import java.text.ParseException;
import java.util.Date;

import com.superloop.interview.todo.util.DateUtil;
import com.superloop.interview.todo.util.IdFactory;

/**
 * Object Model
 * 
 * @author David Ding
 */

public class TodoItem {
	// unique id
	private long id;

	private String name;
	private String description;

	private String dueDate;

	// to mark if the todo task is completed
	private boolean done;
	// to mark if the todo task is overtime
	private boolean overTime;

	private Date creationTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isOverTime() {
		return overTime;
	}

	public void setOverTime(boolean overTime) {
		this.overTime = overTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TodoItem() {

	}

	public TodoItem(String name, String description, String dueDate) {
		this.id = IdFactory.getInstance().next();
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.done = false;
		this.creationTime = new Date();
	}

	public void complete() {
		this.done = true;
	}

	/**
	 * if current time reach dueDate, means over time;
	 * 
	 * @return
	 */
	public boolean checkOverTime() {
		boolean isOverTime = false;
		Date dueDateObj = null;
		try {
			dueDateObj = DateUtil.parse(dueDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dueDateObj != null) {
			isOverTime = new Date().after(dueDateObj);
		}
		return isOverTime;
	}

	@Override
	public String toString() {
		return "name:" + name + ", description:" + description + ", dueDate" + dueDate + ", done:" + done;
	}

}
