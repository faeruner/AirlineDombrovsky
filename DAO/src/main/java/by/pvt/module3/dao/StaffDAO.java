package by.pvt.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.pvt.module3.entity.Staff;
import by.pvt.module3.managers.SqlManager;

public class StaffDAO extends DAO {
	private static StaffDAO instance;

	private StaffDAO() {
		super();
	}

	public static StaffDAO getInstance() {
		if (instance == null) {
			instance = new StaffDAO();
		}
		return instance;
	}

	public void addStaff(Staff staff) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_ADD_STAFF);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, staff.getName());
		ps.setString(2, staff.getSurname());
		ps.setInt(3, staff.getMember().getId());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void deleteStaff(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_DELETE_STAFF);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void updateStaff(Staff staff) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_UPDATE_STAFF);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, staff.getName());
		ps.setString(2, staff.getSurname());
		ps.setInt(3, staff.getMember().getId());
		ps.setInt(4, staff.getId());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public Staff getStaff(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_STAFF_BY_ID);
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
		ps.setString(1, id.toString());
		ResultSet result = ps.executeQuery();
		Staff staff = null;

		if (result.next()) {
			staff = new Staff();
			staff.setId(result.getInt(Staff.ID));
			staff.setName(result.getString(Staff.NAME));
			staff.setSurname(result.getString(Staff.SURNAME));
			staff.setMember(MemberTypeDAO.getInstance().getMemberType(result.getInt(Staff.MEMBER_TYPE_ID)));
			poolInstance.freeConnection(connection);
			return staff;
		} else {
			poolInstance.freeConnection(connection);
			return staff;
		}
	}

	public ArrayList<Staff> getAllStaff() throws SQLException {
		PreparedStatement ps = null;
		ArrayList<Staff> list = new ArrayList<Staff>();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_ALL_STAFF);
		Connection connection = poolInstance.getConnection();
		ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			Staff staff = new Staff();
			staff.setId(Integer.parseInt(result.getString(Staff.ID)));
			staff.setName(result.getString(Staff.NAME));
			staff.setSurname(result.getString(Staff.SURNAME));
			staff.setMember(MemberTypeDAO.getInstance().getMemberType(result.getInt(Staff.MEMBER_TYPE_ID)));
			list.add(staff);
		}
		poolInstance.freeConnection(connection);
		return list;
	}
}