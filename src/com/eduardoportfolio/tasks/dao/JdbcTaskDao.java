package com.eduardoportfolio.tasks.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.eduardoportfolio.tasks.ConnectionFactory;
import com.eduardoportfolio.tasks.model.Task;

/**
 * @author Eduardo Geralde Neto
 * 
 * This JdbcTaskDao class create a connection when instantiated. It has our CRUD and other methods 
 * that deal with the behavior of our tasks.The only responsible to access, change, add and remove our 
 * data on DB.
 */

public class JdbcTaskDao {
	private final Connection connection;

	//Creating connection in the constructor
	public JdbcTaskDao() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Create a new task on DB
	public void create(Task task) {
		String sql = "insert into tasks (description, complete) values (?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, task.getDescription());
			stmt.setBoolean(2, task.getIsComplete());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Remove the task on DB by a given id
	public void remove(Task task) {

		if (task.getId() == null) {
			throw new IllegalStateException("Task ID can not be null");
		}

		String sql = "delete from tasks where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, task.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Update the task on DB by a given task data
	public void upDate(Task task) {
		String sql = "update tasks set description = ?, complete = ?, finalizedDay = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, task.getDescription());
			stmt.setBoolean(2, task.getIsComplete());
			stmt.setDate(3, task.getFinalizedDay() != null ? new Date(
					task.getFinalizedDay().getTimeInMillis()) : null);
			stmt.setLong(4, task.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//List all tasks that are in the DB
	public List<Task> getList() {
		
		try {
			List<Task> tasks = new ArrayList<Task>();
			PreparedStatement stmt = this.connection .prepareStatement("select * from tasks");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				tasks.add(fillTask(rs));
			}
			rs.close();
			stmt.close();

			return tasks;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Select a specific task by a given id
	public Task selectById(Long id) {

		if (id == null) {
			throw new IllegalStateException("Task ID can not be null");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tasks where id = ?");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return fillTask(rs);
			}

			rs.close();
			stmt.close();

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Turn the task completed, and sets the date with the current time by a given id
	public void endTask(Long id) {

		if (id == null) {
			throw new IllegalStateException("Task ID can not be null");
		}

		String sql = "update tasks set complete = ?, finalizedDay = ? where id = ?";
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Auxiliary method to fill the task object and return it
	private Task fillTask(ResultSet rs) throws SQLException {
		
		Task task = new Task();

		//Fill object task
		task.setId(rs.getLong("id"));
		task.setDescription(rs.getString("description"));
		task.setIsComplete(rs.getBoolean("complete"));

		//Fill the finalized date  of the task, making the conversion
		Date date = rs.getDate("finalizedDay");
		if (date != null) {
			Calendar finalizedDay = Calendar.getInstance();
			finalizedDay.setTime(date);
			task.setFinalizedDay(finalizedDay);
		}
		return task;
	}
}
