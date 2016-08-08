package by.pvt.module3.entity;

import java.util.Date;
import java.util.List;

public class Crew {
	public static final String ID = "id";
	public static final String CREATE_DATE = "create_date";
	public static final String READY = "ready";
	public static final String USER_ID = "users_id";
	public static final String CREW_ID = "crew_id";
	public static final String STAFF_ID = "staff_id";

	Integer id;
	Date createDate;
	Integer ready;
	User user;
	List<Staff> members;

	public List<Staff> getMembers() {
		return members;
	}

	public void setMembers(List<Staff> members) {
		this.members = members;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getReady() {
		return ready;
	}

	public void setReady(Integer ready) {
		this.ready = ready;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
