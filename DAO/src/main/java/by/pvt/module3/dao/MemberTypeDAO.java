package by.pvt.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.pvt.module3.entity.MemberType;
import by.pvt.module3.managers.SqlManager;

public class MemberTypeDAO extends DAO {
	private static MemberTypeDAO instance;

	private MemberTypeDAO() {
		super();
	}

	public static MemberTypeDAO getInstance() {
		if (instance == null) {
			instance = new MemberTypeDAO();
		}
		return instance;
	}

	public void addMemberType(MemberType member) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_ADD_MEMBER_TYPE);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, member.getName());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void deleteMemberType(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_DELETE_MEMBER_TYPE);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void updateMemberType(MemberType airport) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_UPDATE_MEMBER_TYPE);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, airport.getName());
		ps.setInt(2, airport.getId());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public MemberType getMemberType(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_MEMBER_TYPE_BY_ID);
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
		ps.setString(1, id.toString());
		ResultSet result = ps.executeQuery();

		MemberType member = null;

		if (result.next()) {
			member = new MemberType();
			member.setId(result.getInt(MemberType.ID));
			member.setName(result.getString(MemberType.NAME));
			poolInstance.freeConnection(connection);
			return member;

		} else {
			poolInstance.freeConnection(connection);
			return member;
		}
	}

	public ArrayList<MemberType> getAllMemberTypes() throws SQLException {
		PreparedStatement ps = null;
		ArrayList<MemberType> list = new ArrayList<MemberType>();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_ALL_MEMBER_TYPES);
		Connection connection = poolInstance.getConnection();

		ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			MemberType member = new MemberType();
			member.setId(Integer.parseInt(result.getString(MemberType.ID)));
			member.setName(result.getString(MemberType.NAME));
			list.add(member);
		}
		poolInstance.freeConnection(connection);
		return list;
	}

}
