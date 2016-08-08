package by.pvt.module3.connectpool;

import com.mysql.jdbc.Driver;
import by.pvt.module3.xml.ConfigParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.util.LinkedList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class DBConnectionPool {

	private static DBConnectionPool instance;
	private String URL;
	private String user;
	private String password;
	private String driverName;
	private static String CONFIG_XML = "config.xml";

	private LinkedList<Connection> deque;

	private DBConnectionPool() {

		try {
			ConfigParser configParser = new ConfigParser();
			configParser.parseConfig(CONFIG_XML);

			URL = configParser.getUrl();
			user = configParser.getLogin();
			password = configParser.getPassword().trim();
			driverName = configParser.getDriver();

			Driver driver = (Driver) Class.forName(driverName).newInstance();
			DriverManager.registerDriver(driver);

			deque = new LinkedList<Connection>();

		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (SQLException e) {
		} catch (IllegalAccessException e) {
		}
	}

	public static DBConnectionPool getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return new DBConnectionPool();
		}
	}

	public synchronized Connection getConnection() throws SQLException {
		if (!deque.isEmpty()) {
			while (!deque.isEmpty()) {
				Connection connection = deque.poll();
				if (connection.isValid(500)) {
					return connection;
				}
				return DriverManager.getConnection(URL, user, password);
			}
		}
		return DriverManager.getConnection(URL, user, password);
	}

	public synchronized void freeConnection(Connection connection) {
		try {
			if (!connection.isClosed()) {
				deque.add(connection);
			}
		} catch (SQLException e) {
			return;
		}
	}
}
