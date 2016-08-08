package by.pvt.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.pvt.module3.entity.User;
import by.pvt.module3.managers.SqlManager;

public class UserDAO extends DAO {
	private static UserDAO instance;

	private UserDAO() {
		super();
	}

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public void addUser(User user) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_ADD_USER);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		initParams(user, ps);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void deleteUser(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_DELETE_USER);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void updateUser(User user) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_UPDATE_USER);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		initParams(user, ps);
		ps.setInt(6, user.getId());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public User getUser(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_USER_BY_ID);
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
		ps.setString(1, id.toString());
		ResultSet result = ps.executeQuery();
		User user = null;

		if (result.next()) {
			user = initFields(new User(), result);
		}
		poolInstance.freeConnection(connection);
		return user;
	}

	public ArrayList<User> getAllUsers() throws SQLException {
		PreparedStatement ps = null;
		ArrayList<User> list = new ArrayList<User>();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_ALL_USERS);
		Connection connection = poolInstance.getConnection();
		ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			list.add(initFields(new User(), result));
		}
		poolInstance.freeConnection(connection);
		return list;
	}

	public User getUserByLogin(String login) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_USER_BY_LOGIN);
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
		ps.setString(1, login);
		ResultSet result = ps.executeQuery();
		User user = null;

		if (result.next()) {
			user = initFields(new User(), result);
		}
		poolInstance.freeConnection(connection);
		return user;
	}

	private User initFields(User user, ResultSet result) throws SQLException {
		user.setId(Integer.parseInt(result.getString(User.ID)));
		user.setName(result.getString(User.NAME));
		user.setSurname(result.getString(User.SURNAME));
		user.setLogin(result.getString(User.LOGIN));
		user.setPassword(result.getString(User.PASSWORD));
		user.setRole(UserRoleDAO.getInstance().getUserRole(Integer.parseInt(result.getString(User.USER_ROLE_ID))));
		return user;
	}

	private void initParams(User user, PreparedStatement ps) throws SQLException {
		ps.setString(1, user.getName());
		ps.setString(2, user.getSurname());
		ps.setString(3, user.getLogin());
		ps.setString(4, user.getPassword());
		ps.setInt(5, user.getRole().getId());
	}
}
