package com.eduardoportfolio.tasks.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Eduardo Geralde Neto
 * 
 * Mapped Java Bean task model (POJO).
 */

//Becomes persistent in the database
@Entity
public class Task {
	
	@Id  //Primary Key
	@GeneratedValue // Populated by DataBase
	private Long id;
	@NotNull (message="Description can not be null ") 
	@Size(min=5, message="Description has to have more than 5 caracters")
	private String description;
	private boolean isComplete;
	@Temporal (TemporalType.DATE) //Could be TIME or TIMESTAMP
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private Calendar finalizedDate;

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

	public Calendar getFinalizedDate() {
		return finalizedDate;
	}

	public void setFinalizedDate(Calendar finalizedDate) {
		this.finalizedDate = finalizedDate;
	}

}
