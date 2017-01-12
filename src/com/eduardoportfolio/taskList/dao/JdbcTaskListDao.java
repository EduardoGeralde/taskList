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
import com.eduardoportfolio.taskList.model.TaskList;

public class JdbcTaskListDao {
	private final Connection connection;

	public JdbcTaskListDao() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void create(TaskList task) {
		String sql = "insert into taskList (description, complete) values (?,?)";
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

	public void remove(TaskList task) {

		if (task.getId() == null) {
			throw new IllegalStateException("Task ID can not be null");
		}

		String sql = "delete from taskList where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, task.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void upDate(TaskList task) {
		String sql = "update taskList set description = ?, complete = ?, deadLine = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, task.getDescription());
			stmt.setBoolean(2, task.isComplete());
			stmt.setDate(3, task.getDeadLine() != null ? new Date(
					task.getDeadLine().getTimeInMillis()) : null);
			stmt.setLong(4, task.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<TaskList> getList() {
		
		try {
			List<TaskList> tasks = new ArrayList<TaskList>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from taskList");

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

	public TaskList selectById(Long id) {

		if (id == null) {
			throw new IllegalStateException("Task ID can not be null");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from taskList where id = ?");
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

		String sql = "update taskList set complete = ?, deadLine = ? where id = ?";
		
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

	private TaskList fillTask(ResultSet rs) throws SQLException {
		
		TaskList task = new TaskList();

		//Fill object task
		task.setId(rs.getLong("id"));
		task.setDescription(rs.getString("description"));
		task.setComplete(rs.getBoolean("complete"));

		//Fill the deadLine date  of the task, making the conversion
		Date date = rs.getDate("deadLine");
		if (date != null) {
			Calendar deadLine = Calendar.getInstance();
			deadLine.setTime(date);
			task.setDeadLine(deadLine);
		}
		return task;
	}
}
