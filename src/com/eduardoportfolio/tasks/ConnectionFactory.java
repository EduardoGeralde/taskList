package com.eduardoportfolio.tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Eduardo Geralde Neto
 * 
 * This ConnectionFactory is responsible to create new connections through the getConnection() method.
 * In this particular case, it returns a mySql connection.
 */

public class ConnectionFactory {

	public Connection getConnection() throws SQLException {
		System.out.println("connecting ...");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return DriverManager.getConnection("jdbc:mysql://localhost/jdbc",
				"root", "password");
	}

}
