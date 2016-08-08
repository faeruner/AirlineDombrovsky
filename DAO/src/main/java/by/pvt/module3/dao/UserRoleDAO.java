package by.pvt.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.pvt.module3.entity.UserRole;
import by.pvt.module3.managers.SqlManager;

public class UserRoleDAO extends DAO {
	private static UserRoleDAO instance;

	private UserRoleDAO() {
		super();
	}

	public static UserRoleDAO getInstance() {
		if (instance == null) {
			instance = new UserRoleDAO();
		}
		return instance;
	}

	public void addUserRole(UserRole role) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_ADD_USER_ROLE);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, role.getName());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void deleteUserRole(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_DELETE_USER_ROLE);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void updateUserRole(UserRole role) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_UPDATE_USER_ROLE);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, role.getName());
		ps.setInt(2, role.getId());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public UserRole getUserRole(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_USER_ROLE_BY_ID);
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
		ps.setString(1, id.toString());
		ResultSet result = ps.executeQuery();
		UserRole role = null;

		if (result.next()) {
			role = new UserRole();
			role.setId(result.getInt(UserRole.ID));
			role.setName(result.getString(UserRole.NAME));
			poolInstance.freeConnection(connection);
			return role;

		} else {
			poolInstance.freeConnection(connection);
			return role;
		}
	}

	public ArrayList<UserRole> getAllUserRoles() throws SQLException {
		PreparedStatement ps = null;
		ArrayList<UserRole> list = new ArrayList<UserRole>();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_ALL_USER_ROLES);
		Connection connection = poolInstance.getConnection();
		ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			UserRole role = new UserRole();
			role.setId(Integer.parseInt(result.getString(UserRole.ID)));
			role.setName(result.getString(UserRole.NAME));
			list.add(role);
		}
		poolInstance.freeConnection(connection);
		return list;
	}
}
