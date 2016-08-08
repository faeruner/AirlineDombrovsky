package by.pvt.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.managers.SqlManager;

public class CrewDAO extends DAO {
	private static CrewDAO instance;

	private CrewDAO() {
		super();
	}

	public static CrewDAO getInstance() {
		if (instance == null) {
			instance = new CrewDAO();
		}
		return instance;
	}

	public void addCrew(Crew crew) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_ADD_CREW);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		initParams(crew, ps);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void deleteCrew(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();

		String query = sqlManager.getProperty(SqlManager.SQL_DELETE_CREW_MEMBERS);
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();

		query = sqlManager.getProperty(SqlManager.SQL_DELETE_CREW);
		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void updateCrew(Crew crew) throws SQLException {
		Connection connection = poolInstance.getConnection();

		String query = sqlManager.getProperty(SqlManager.SQL_UPDATE_CREW);
		PreparedStatement ps = connection.prepareStatement(query);
		initParams(crew, ps);
		ps.setInt(4, crew.getId());
		ps.executeUpdate();

		query = sqlManager.getProperty(SqlManager.SQL_DELETE_CREW_MEMBERS);
		ps = connection.prepareStatement(query);
		ps.setInt(1, crew.getId());
		ps.executeUpdate();

		query = sqlManager.getProperty(SqlManager.SQL_INSERT_CREW_MEMBER);
		ps = connection.prepareStatement(query);

		if (crew.getMembers() != null) {
			for (Staff staff : crew.getMembers()) {
				ps.setInt(1, crew.getId());
				ps.setInt(2, staff.getId());
				ps.executeUpdate();
			}
		}

		poolInstance.freeConnection(connection);
	}

	public Crew getCrew(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_CREW_BY_ID);
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
		ps.setString(1, id.toString());
		ResultSet result = ps.executeQuery();
		Crew crew = null;

		if (result.next()) {
			crew = initFields(new Crew(), result);
		}
		poolInstance.freeConnection(connection);
		return crew;
	}

	public ArrayList<Crew> getAllCrews() throws SQLException {
		PreparedStatement ps = null;
		ArrayList<Crew> list = new ArrayList<Crew>();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_ALL_CREWS);
		Connection connection = poolInstance.getConnection();
		ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			list.add(initFields(new Crew(), result));
		}
		poolInstance.freeConnection(connection);
		return list;
	}

	private Crew initFields(Crew entity, ResultSet result) throws SQLException {
		entity.setId(result.getInt(Crew.ID));

		entity.setReady(result.getInt(Crew.READY));
		entity.setCreateDate(result.getDate(Crew.CREATE_DATE));
		entity.setUser(UserDAO.getInstance().getUser(result.getInt(Crew.USER_ID)));

		List<Staff> members = new ArrayList<Staff>();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_CREW_MEMBERS);
		Connection connection = poolInstance.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, entity.getId());
		ResultSet res = ps.executeQuery();
		while (res.next()) {
			Staff staff = new Staff();
			staff.setId(Integer.parseInt(res.getString(Staff.ID)));
			staff.setName(res.getString(Staff.NAME));
			staff.setSurname(res.getString(Staff.SURNAME));
			staff.setMember(MemberTypeDAO.getInstance().getMemberType(res.getInt(Staff.MEMBER_TYPE_ID)));
			members.add(staff);
		}
		poolInstance.freeConnection(connection);
		entity.setMembers(members);
		return entity;
	}

	private void initParams(Crew entity, PreparedStatement ps) throws SQLException {
		ps.setDate(1, new java.sql.Date(entity.getCreateDate().getTime()));
		ps.setInt(2, entity.getReady());
		ps.setInt(3, entity.getUser().getId());
	}
}
