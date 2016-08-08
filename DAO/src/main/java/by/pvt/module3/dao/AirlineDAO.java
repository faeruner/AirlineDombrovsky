package by.pvt.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.pvt.module3.entity.Airline;
import by.pvt.module3.managers.SqlManager;

public class AirlineDAO extends DAO {
	private static AirlineDAO instance;

	private AirlineDAO() {
		super();
	}

	public static AirlineDAO getInstance() {
		if (instance == null) {
			instance = new AirlineDAO();
		}
		return instance;
	}

	public void addAirline(Airline airline) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_ADD_AIRLINE);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, airline.getName());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void deleteAirline(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_DELETE_AIRLINE);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void updateAirline(Airline airport) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_UPDATE_AIRLINE);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, airport.getName());
		ps.setInt(2, airport.getId());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public Airline getAirline(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_AIRLINE_BY_ID);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, id.toString());
		ResultSet result = ps.executeQuery();

		Airline air = null;

		if (result.next()) {
			air = new Airline();

			air.setId(result.getInt(Airline.ID));
			air.setName(result.getString(Airline.NAME));

			poolInstance.freeConnection(connection);
			return air;

		} else {
			poolInstance.freeConnection(connection);
			return air;
		}
	}

	public ArrayList<Airline> getAllAirlines() throws SQLException {
		PreparedStatement ps = null;
		ArrayList<Airline> list = new ArrayList<Airline>();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_ALL_AIRLINES);
		Connection connection = poolInstance.getConnection();

		ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			Airline airline = new Airline();

			airline.setId(Integer.parseInt(result.getString(Airline.ID)));
			airline.setName(result.getString(Airline.NAME));

			list.add(airline);
		}
		poolInstance.freeConnection(connection);
		return list;
	}
}
