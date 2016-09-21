package by.pvt.module3.entity;

import java.io.Serializable;

public class User implements Serializable {
    public static final String ID = "id";
	public static final String NAME = "name";
	public static final String SURNAME = "surname";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String USER_ROLE_ID = "user_role_id";

	private Integer id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private UserRole role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
