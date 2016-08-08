package by.pvt.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import by.pvt.module3.entity.Airport;
import by.pvt.module3.managers.SqlManager;

public class AirportDAO extends DAO {
	private static AirportDAO instance;

	private AirportDAO() {
		super();
	}

	public static AirportDAO getInstance() {
		if (instance == null) {
			instance = new AirportDAO();
		}
		return instance;
	}

	public void addAirport(Airport airport) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_ADD_AIRPORT);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, airport.getName());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void deleteAirport(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_DELETE_AIRPORT);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void updateAirport(Airport airport) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_UPDATE_AIRPORT);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, airport.getName());
		ps.setInt(2, airport.getId());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public Airport getAirport(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_AIRPORT_BY_ID);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, id.toString());
		ResultSet result = ps.executeQuery();

		Airport air = null;

		if (result.next()) {
			air = new Airport();

			air.setId(result.getInt(Airport.ID));
			air.setName(result.getString(Airport.NAME));

			poolInstance.freeConnection(connection);
			return air;

		} else {
			poolInstance.freeConnection(connection);
			return air;
		}
	}

	public ArrayList<Airport> getAllAirports() throws SQLException {
		PreparedStatement ps = null;
		ArrayList<Airport> list = new ArrayList<Airport>();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_ALL_AIRPORTS);
		Connection connection = poolInstance.getConnection();

		ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			Airport airport = new Airport();

			airport.setId(Integer.parseInt(result.getString(Airport.ID)));
			airport.setName(result.getString(Airport.NAME));

			list.add(airport);
		}
		poolInstance.freeConnection(connection);
		return list;
	}
}
