package com.eduardoportfolio.taskList.model;

import java.util.Calendar;

public class TaskList {
	private Long id;
	private String description;
	private boolean complete;
	private Calendar deadLine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public Calendar getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Calendar deadLine) {
		this.deadLine = deadLine;
	}

}
