package by.pvt.module3.managers;

import java.util.ResourceBundle;

/**
 * 
 * Provides access to prepared statements, which conatins in .properties file.
 * 
 */
public class SqlManager {

	private static SqlManager instance;
	private static ResourceBundle bundle;

	public static final String BUNDLE_NAME = "sql";

	// #ADMINISTRATOR DAO
	public static final String SQL_SET_ORDER_STATUS = "SQL_SET_ORDER_STATUS";
	public static final String SQL_DELETE_FILM = "SQL_DELETE_FILM";
	public static final String SQL_UPDATE_ORDER_STATUS = "SQL_UPDATE_ORDER_STATUS";
	// public static final String SQL_GET_ALL_USERS = "SQL_GET_ALL_USERS";
	public static final String SQL_SET_ACCESS_LEVEL = "SQL_SET_ACCESS_LEVEL";

	// #AIRPORT DAO
	public static final String SQL_ADD_AIRPORT = "SQL_ADD_AIRPORT";
	public static final String SQL_GET_AIRPORT_BY_ID = "SQL_GET_AIRPORT_BY_ID";
	public static final String SQL_GET_ALL_AIRPORTS = "SQL_GET_ALL_AIRPORTS";
	public static final String SQL_UPDATE_AIRPORT = "SQL_UPDATE_AIRPORT";
	public static final String SQL_DELETE_AIRPORT = "SQL_DELETE_AIRPORT";

	// #AIRLINE DAO
	public static final String SQL_ADD_AIRLINE = "SQL_ADD_AIRLINE";
	public static final String SQL_GET_AIRLINE_BY_ID = "SQL_GET_AIRLINE_BY_ID";
	public static final String SQL_GET_ALL_AIRLINES = "SQL_GET_ALL_AIRLINES";
	public static final String SQL_UPDATE_AIRLINE = "SQL_UPDATE_AIRLINE";
	public static final String SQL_DELETE_AIRLINE = "SQL_DELETE_AIRLINE";

	// #USERROLE DAO
	public static final String SQL_ADD_USER_ROLE = "SQL_ADD_USER_ROLE";
	public static final String SQL_GET_USER_ROLE_BY_ID = "SQL_GET_USER_ROLE_BY_ID";
	public static final String SQL_GET_ALL_USER_ROLES = "SQL_GET_ALL_USER_ROLES";
	public static final String SQL_UPDATE_USER_ROLE = "SQL_UPDATE_USER_ROLE";
	public static final String SQL_DELETE_USER_ROLE = "SQL_DELETE_USER_ROLE";

	// #ORDERS DAO
	public static final String SQL_GET_ALL_ORDERS = "SQL_GET_ALL_ORDERS";
	public static final String SQL_GET_ORDERS_BY_UID = "SQL_GET_ORDERS_BY_UID";
	public static final String SQL_GET_ORDERS_BY_NAME = "SQL_GET_ORDERS_BY_NAME";
	public static final String SQL_GET_ORDERS_BY_STATUS = "SQL_GET_ORDERS_BY_STATUS";
	public static final String SQL_ADD_ORDER = "SQL_ADD_ORDER";

	// #USER DAO
	public static final String SQL_ADD_USER = "SQL_ADD_USER";
	public static final String SQL_GET_USER_BY_ID = "SQL_GET_USER_BY_ID";
	public static final String SQL_GET_USER_BY_LOGIN = "SQL_GET_USER_BY_LOGIN";
	public static final String SQL_GET_ALL_USERS = "SQL_GET_ALL_USERS";
	public static final String SQL_UPDATE_USER = "SQL_UPDATE_USER";
	public static final String SQL_DELETE_USER = "SQL_DELETE_USER";

	// #STAFF DAO
	public static final String SQL_ADD_STAFF = "SQL_ADD_STAFF";
	public static final String SQL_GET_STAFF_BY_ID = "SQL_GET_STAFF_BY_ID";
	public static final String SQL_GET_ALL_STAFF = "SQL_GET_ALL_STAFF";
	public static final String SQL_UPDATE_STAFF = "SQL_UPDATE_STAFF";
	public static final String SQL_DELETE_STAFF = "SQL_DELETE_STAFF";

	// #MEMBER_TYPE DAO
	public static final String SQL_ADD_MEMBER_TYPE = "SQL_ADD_MEMBER_TYPE";
	public static final String SQL_GET_MEMBER_TYPE_BY_ID = "SQL_GET_MEMBER_TYPE_BY_ID";
	public static final String SQL_GET_ALL_MEMBER_TYPES = "SQL_GET_ALL_MEMBER_TYPES";
	public static final String SQL_UPDATE_MEMBER_TYPE = "SQL_UPDATE_MEMBER_TYPE";
	public static final String SQL_DELETE_MEMBER_TYPE = "SQL_DELETE_MEMBER_TYPE";

	// #CREW DAO
	public static final String SQL_ADD_CREW = "SQL_ADD_CREW";
	public static final String SQL_GET_CREW_BY_ID = "SQL_GET_CREW_BY_ID";
	public static final String SQL_GET_ALL_CREWS = "SQL_GET_ALL_CREWS";
	public static final String SQL_UPDATE_CREW = "SQL_UPDATE_CREW";
	public static final String SQL_DELETE_CREW = "SQL_DELETE_CREW";
	public static final String SQL_GET_CREW_MEMBERS = "SQL_GET_CREW_MEMBERS";
	public static final String SQL_INSERT_CREW_MEMBER = "SQL_INSERT_CREW_MEMBER";
	public static final String SQL_DELETE_CREW_MEMBERS = "SQL_DELETE_CREW_MEMBERS";

	// #FLIGHT DAO
	public static final String SQL_ADD_FLIGHT = "SQL_ADD_FLIGHT";
	public static final String SQL_GET_FLIGHT_BY_ID = "SQL_GET_FLIGHT_BY_ID";
	public static final String SQL_GET_ALL_FLIGHTS = "SQL_GET_ALL_FLIGHTS";
	public static final String SQL_UPDATE_FLIGHT = "SQL_UPDATE_FLIGHT";
	public static final String SQL_DELETE_FLIGHT = "SQL_DELETE_FLIGHT";

	public static SqlManager getInstance() {
		if (instance == null) {
			instance = new SqlManager();
			bundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}

	public String getProperty(String key) {
		return bundle.getString(key);
	}

}
