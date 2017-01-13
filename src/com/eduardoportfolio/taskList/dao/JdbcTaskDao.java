package com.eduardoportfolio.taskList.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.eduardoportfolio.taskList.ConnectionFactory;
import com.eduardoportfolio.taskList.model.Task;

public class JdbcTaskDao {
	private final Connection connection;

	public JdbcTaskDao() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void create(Task task) {
		String sql = "insert into tasks (description, complete) values (?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, task.getDescription());
			stmt.setBoolean(2, task.isComplete());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

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

	public void upDate(Task task) {
		String sql = "update tasks set description = ?, complete = ?, finalizedDay = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, task.getDescription());
			stmt.setBoolean(2, task.isComplete());
			stmt.setDate(3, task.getFinalizedDay() != null ? new Date(
					task.getFinalizedDay().getTimeInMillis()) : null);
			stmt.setLong(4, task.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Task> getList() {
		
		try {
			List<Task> tasks = new ArrayList<Task>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tasks");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				// Add task in the List
				tasks.add(fillTask(rs));
			}
			rs.close();
			stmt.close();

			return tasks;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

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

	private Task fillTask(ResultSet rs) throws SQLException {
		
		Task task = new Task();

		//Fill object task
		task.setId(rs.getLong("id"));
		task.setDescription(rs.getString("description"));
		task.setComplete(rs.getBoolean("complete"));

		//Fill the deadLine date  of the task, making the conversion
		Date date = rs.getDate("finalizedDay");
		if (date != null) {
			Calendar finalizedDate = Calendar.getInstance();
			finalizedDate.setTime(date);
			task.setFinalizedDay(finalizedDate);
		}
		return task;
	}
}
