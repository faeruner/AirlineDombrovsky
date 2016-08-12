package by.pvt.module3.filter;

public enum UserType {
	DISPATCHER(2), ADMINISTRATOR(1), GUEST(0);

	private Integer id;

	UserType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}