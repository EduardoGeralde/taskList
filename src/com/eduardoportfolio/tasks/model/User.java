package com.eduardoportfolio.tasks.model;

/**
 * @author Eduardo Geralde Neto
 * 
 * A simple Class, that  has two attributes, and will interact with our login area.
 */

public class User {
	private String login;
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
