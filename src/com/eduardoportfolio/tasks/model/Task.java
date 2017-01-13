package com.eduardoportfolio.tasks.model;

import java.util.Calendar;

public class Task {
	private Long id;
	private String description;
	private boolean complete;
	private Calendar finalizedDay;

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

	public Calendar getFinalizedDay() {
		return finalizedDay;
	}

	public void setFinalizedDay(Calendar finalizedDay) {
		this.finalizedDay = finalizedDay;
	}

}
