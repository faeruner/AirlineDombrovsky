package by.pvt.module3.entity;

public class UserRole {
	public static final String ID = "id";
	public static final String NAME = "name";

	private Integer id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
