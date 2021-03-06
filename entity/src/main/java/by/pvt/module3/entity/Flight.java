package by.pvt.module3.entity;

import java.io.Serializable;
import java.util.Date;

public class Flight implements Serializable {

	public static final String ID = "id";
	public static final String CODE = "code";
	public static final String DEPARTURE_DATE = "departure";
	public static final String RETURN_DATE = "return_date";
	public static final String CREATE_DATE = "create_date";
	public static final String AIRPORT_ARV_ID = "airport_arv_id";
	public static final String AIRPORT_DEP_ID = "airport_dep_id";
	public static final String AIRLINE_ID = "airline_id";
	public static final String CREW_ID = "crew_id";
	public static final String USER_ID = "users_id";

	private Integer id; // id
	private String code; // code
	private Date depDate; // departure
	private Date returnDate; // return_date
	private Date createDate; // create_date
	private Airport arrival; // airport_arv_id
	private Airport departure; // airport_dep_id
	private Airline airline; // airline_id
	private Crew crew; // crew_id
	private User user; // users_id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Airport getArrival() {
		return arrival;
	}

	public void setArrival(Airport arrival) {
		this.arrival = arrival;
	}

	public Airport getDeparture() {
		return departure;
	}

	public void setDeparture(Airport departure) {
		this.departure = departure;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Crew getCrew() {
		return crew;
	}

	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
