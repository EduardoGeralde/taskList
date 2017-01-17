package com.eduardoportfolio.tasks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eduardoportfolio.tasks.ConnectionFactory;
import com.eduardoportfolio.tasks.model.User;

/**
 * @author Eduardo Geralde Neto
 * 
 * This JdbcUserDao create a connection on constructor, when instantiate, besides that, it checks if the 
 * user exists
 */

public class JdbcUserDao {
	private Connection connection;

	public JdbcUserDao() {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean userExists(User user) {

		if (user == null) {
			throw new IllegalArgumentException("User can not be null");
		}

		try {
			String sql = "select * from users where login = ? and password = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();

			boolean found = rs.next();
			rs.close();
			stmt.close();

			return found;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
