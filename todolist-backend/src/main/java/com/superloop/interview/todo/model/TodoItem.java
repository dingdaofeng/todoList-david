package com.superloop.interview.todo.model;

import java.util.Date;

import com.superloop.interview.todo.type.ItemStatus;
import com.superloop.interview.todo.util.IdFactory;

/**
 * @author David Ding
 */

public class TodoItem {
    private long id;
    private String name;
    private String description;
    
    private Date dueDate;
    
    private ItemStatus status;
    
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
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
	
	public TodoItem(String name, String description, Date dueDate) {
		this.id = IdFactory.getInstance().next();
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = ItemStatus.Pending;
        this.creationTime = new Date();
    }
	
	public void complete(){
		this.status = ItemStatus.Done;
	}
	
	//if current time after dueDate, means over time;
	public boolean checkOverTime(){
		return new Date().after(dueDate);
	}
	
	@Override
	public String toString(){
		return "name:" + name + ", description:" + description + ", dueDate" + dueDate
				+ ", status:" + status;
	}

}
