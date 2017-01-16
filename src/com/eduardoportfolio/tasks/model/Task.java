package com.eduardoportfolio.tasks.model;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Task {
	
	private Long id;
	@NotNull (message="Description can not be null ") 
	@Size(min=5, message="Description has to have more than 5 caracters")
	private String description;
	private boolean isComplete;
	@DateTimeFormat (pattern="dd/MM/yyyy")
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

	public boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public Calendar getFinalizedDay() {
		return finalizedDay;
	}

	public void setFinalizedDay(Calendar finalizedDay) {
		this.finalizedDay = finalizedDay;
	}

}
