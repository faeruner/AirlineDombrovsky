package by.pvt.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.pvt.module3.entity.Flight;
import by.pvt.module3.managers.SqlManager;

public class FlightDAO extends DAO {
	private static FlightDAO instance;

	private FlightDAO() {
		super();
	}

	public static FlightDAO getInstance() {
		if (instance == null) {
			instance = new FlightDAO();
		}
		return instance;
	}

	public void addFlight(Flight entity) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_ADD_FLIGHT);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		initParams(entity, ps);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void deleteFlight(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_DELETE_FLIGHT);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public void updateFlight(Flight entity) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_UPDATE_FLIGHT);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		initParams(entity, ps);
		ps.setInt(10, entity.getId());
		ps.executeUpdate();

		poolInstance.freeConnection(connection);
	}

	public Flight getFlight(Integer id) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_FLIGHT_BY_ID);
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
		ps.setString(1, id.toString());
		ResultSet result = ps.executeQuery();
		Flight flight = null;

		if (result.next()) {
			flight = initFields(new Flight(), result);
		}
		poolInstance.freeConnection(connection);
		return flight;
	}

	public ArrayList<Flight> getAllFlights() throws SQLException {
		PreparedStatement ps = null;
		ArrayList<Flight> list = new ArrayList<Flight>();
		String query = sqlManager.getProperty(SqlManager.SQL_GET_ALL_FLIGHTS);
		Connection connection = poolInstance.getConnection();
		ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			list.add(initFields(new Flight(), result));
		}
		poolInstance.freeConnection(connection);
		return list;
	}

	private Flight initFields(Flight entity, ResultSet result) throws SQLException {
		entity.setId(result.getInt(Flight.ID));

		entity.setCode(result.getString(Flight.CODE));
		entity.setDepDate(result.getDate(Flight.DEPARTURE_DATE));
		entity.setReturnDate(result.getDate(Flight.RETURN_DATE));
		entity.setCreateDate(result.getDate(Flight.CREATE_DATE));

		entity.setArrival(AirportDAO.getInstance().getAirport(result.getInt(Flight.AIRPORT_ARV_ID)));
		entity.setDeparture(AirportDAO.getInstance().getAirport(result.getInt(Flight.AIRPORT_DEP_ID)));
		entity.setAirline(AirlineDAO.getInstance().getAirline(result.getInt(Flight.AIRLINE_ID)));
		entity.setCrew(CrewDAO.getInstance().getCrew(result.getInt(Flight.CREW_ID)));
		entity.setUser(UserDAO.getInstance().getUser(result.getInt(Flight.USER_ID)));
		return entity;
	}

	private void initParams(Flight entity, PreparedStatement ps) throws SQLException {
		ps.setString(1, entity.getCode());
		ps.setDate(2, new java.sql.Date(entity.getDepDate().getTime()));
		ps.setDate(3, new java.sql.Date(entity.getReturnDate().getTime()));
		ps.setDate(4, new java.sql.Date(entity.getCreateDate().getTime()));

		ps.setInt(5, entity.getArrival().getId());
		ps.setInt(6, entity.getDeparture().getId());
		ps.setInt(7, entity.getAirline().getId());
		ps.setInt(8, entity.getCrew().getId());
		ps.setInt(9, entity.getUser().getId());
	}
}
